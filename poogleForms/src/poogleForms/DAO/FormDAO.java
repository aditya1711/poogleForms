package poogleForms.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import poogleForms.model.form.Form;
import poogleForms.model.form.MultipleChoiceTypeQuestion;
import poogleForms.model.form.Question;
import poogleForms.model.form.TYPES_OF_QUESTION;
import poogleForms.model.form.TextTypeQuestion;

public class FormDAO extends DAO {
	static FormDAO formDAO = new FormDAO();

	private static final String queryForGetingQuestionWithID = "select prompt, type as questionType,formID, ID as questionID, options from questions where id = ?;";
	private static final String queryForGetingFormSpecificDetailsWithID = "select name as formName,username,ID as formID from forms where id = ?;";
	private static final String queryForGetingQuestionsWithFormID = "select prompt, type as questionType, formID, ID as questionID, options from questions where formID = ?";
	private static final String queryForGetingFormIDsWithUsername = "select ID as formID from forms where username=?;";

	private static final String queryForGetingAllFormIDs ="select ID as formID from forms; ";

	private static final String queryForInsertingQuestion = "begin try " + 
			"begin transaction " + 
			"insert into IDTable " + 
			"values ('1'); " +
			"insert into questions (prompt, type, formID, options, ID) " + 
			"values (?,(select questionType from questionTypes where questionType = ?),(select ID from forms where ID = ?),?, SCOPE_IDENTITY()); " +
			"commit; " +
			"end try " +
			"begin catch " +
			"rollback; " +
			"end catch; " ;
	private static final String queryForInsertingFormSpecificDetatils = "begin try " + 
			"begin transaction " + 
			"insert into IDTable " + 
			"values ('1'); " + 
			"declare @formID int " + 
			"set @formID = scope_identity() "+
			"insert into forms (name, username, ID) " +
			"values (?,(select username from client where username=?), @formID); " +
			"select @formID as formID " +
			"commit; " +
			"end try " +
			"begin catch " +
			"rollback; " +
			"end catch; " ;

	private static final String queryForUpdatingFormName ="update forms set name= ? where ID = ?";

	private static final String queryForDeletingQuestion = "delete from questions where ID = ? ";
	
	private static final String queryForDeletingForm = "alter table questions" +
			"nocheck constraint [FK__questions__formI__6754599E]; " +
			"delete from forms where ID =1001041; " +
			"alter table questions " +
			"check constraint [FK__questions__formI__6754599E];";

	private FormDAO(){

	}


	public static FormDAO getFormDAO(String dbURL, String userID, String password) throws SQLException{
		formDAO.setDAO(dbURL, userID, password);
		return formDAO;
	}

	public HashSet<Long> getFormIDsWithUsername(String username){
		HashSet<Long> formIDs = new HashSet<Long>();
		try(Connection conn = getConnection()){
			PreparedStatement ps = conn.prepareStatement(queryForGetingFormIDsWithUsername);
			int i=1;
			ps.setString(i++,username);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				formIDs.add(rs.getLong("formID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formIDs;
	}

	public HashSet<Long> getAllFormIDs(){
		HashSet<Long> formIDs = new HashSet<Long>();
		try(Connection conn = getConnection()){
			PreparedStatement ps = conn.prepareStatement(queryForGetingAllFormIDs);

			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				formIDs.add(rs.getLong("formID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formIDs;
	}


	public Form getForm(Long ID){
		Form form = new Form();
		try(Connection conn = getConnection();){
			PreparedStatement ps = conn.prepareStatement(queryForGetingFormSpecificDetailsWithID);
			int i=1;
			ps.setLong(i++,ID);
			ResultSet rs = ps.executeQuery();

			rs.next();
			form.setID(rs.getLong("formID"));
			form.setAdminID(rs.getString("username"));
			form.setName(rs.getString("formName"));
			form.setList(getQuestionsWithFormID(ID));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return form;
	}

	private ArrayList<Question> getQuestionsWithFormID(Long formID){
		ArrayList<Question> questionsList = new ArrayList<Question>();
		try(Connection conn = getConnection();){
			PreparedStatement ps = conn.prepareStatement(queryForGetingQuestionsWithFormID);
			int i=1;
			ps.setLong(i++,formID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Question q;
				if(rs.getString("questionType").equals("mcq")){
					q = new MultipleChoiceTypeQuestion();
					MultipleChoiceTypeQuestion mcq = (MultipleChoiceTypeQuestion)q;
					mcq.setOptions(new ArrayList<String>(Arrays.asList(rs.getString("options").split(";"))));

				}
				else if(rs.getString("questionType").equals("text type")){
					q = new TextTypeQuestion();
					TextTypeQuestion tq = (TextTypeQuestion)q;
				}
				else{
					q = new TextTypeQuestion();
				}
				q.setFormID(formID);
				q.setID(rs.getLong("questionID"));
				q.setPrompt(rs.getString("prompt"));
				questionsList.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionsList;
	}

	public Question getQuestion(Long ID){
		Question q = new TextTypeQuestion();
		try(Connection conn = getConnection();){
			PreparedStatement ps = conn.prepareStatement(queryForGetingQuestionWithID);
			int i=1;
			ps.setLong(i++,ID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(rs.getString("questionType").equals("mcq")){
				q = new MultipleChoiceTypeQuestion();
				MultipleChoiceTypeQuestion mcq = (MultipleChoiceTypeQuestion)q;
				mcq.setOptions(new ArrayList<String>(Arrays.asList(rs.getString("options").split(";"))));

			}
			else if(rs.getString("questionType").equals("text type")){
				q = new TextTypeQuestion();
				TextTypeQuestion tq = (TextTypeQuestion)q;
			}
			else{
				q = new TextTypeQuestion();
			}
			q.setFormID(rs.getLong("formID"));
			q.setID(rs.getLong("questionID"));
			q.setPrompt(rs.getString("prompt"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q;
	}

	private void addQuestionToBatch(Question q, PreparedStatement ps){
		int i=1;
		try {
			ps.setString(i++, q.getPrompt());
			ps.setString(i++, q.getType().getDBName());
			ps.setLong(i++, q.getFormID());

			String options = "";
			if(q.getType().equals(TYPES_OF_QUESTION.MULTIPLE_CHOICE_QUESTION)){
				System.out.println("mcq found in form: " + q.getFormID());
				MultipleChoiceTypeQuestion mcq =(MultipleChoiceTypeQuestion)q;

				for(int index = 0;index<mcq.getOptions().size();index++){
					options = options + mcq.getOptions().get(index) + ";";
				}
			}
			else if(q.getType().equals(TYPES_OF_QUESTION.TEXT_QUESTIONS)){
				options = "";
			}
			ps.setString(i++, options);
			ps.addBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addQuestionToDB(Question q){
		try(Connection conn = getConnection()){
			PreparedStatement ps = conn.prepareStatement(queryForInsertingQuestion);

			int i=1;
			ps.setString(i++, q.getPrompt());
			ps.setString(i++, q.getType().getDBName());
			ps.setLong(i++, q.getFormID());

			String options = "";
			if(q.getType().equals(TYPES_OF_QUESTION.MULTIPLE_CHOICE_QUESTION)){
				System.out.println("mcq found in form: " + q.getFormID());
				MultipleChoiceTypeQuestion mcq =(MultipleChoiceTypeQuestion)q;

				for(int index = 0;index<mcq.getOptions().size();index++){
					options = options + mcq.getOptions().get(index) + ";";
				}
			}
			else if(q.getType().equals(TYPES_OF_QUESTION.TEXT_QUESTIONS)){
				options = "";
			}
			ps.setString(i++, options);
			ps.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void addQuestionsToDB(ArrayList<Question> questions){
		try(Connection conn = getConnection()){
			PreparedStatement ps = conn.prepareStatement(queryForInsertingQuestion);
			for(int i=0;i<questions.size();i++){
				addQuestionToBatch(questions.get(i), ps);
			}
			ps.executeBatch();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Long addFormPrototypeToDB(Form f){
		try(Connection conn = getConnection()){
			PreparedStatement ps =conn.prepareStatement(queryForInsertingFormSpecificDetatils,Statement.RETURN_GENERATED_KEYS);

			int i=1;
			ps.setString(i++, f.getName());
			ps.setString(i++, f.getAdminUsername());


			ResultSet rs  = ps.executeQuery();

			Long formID = (long) 0;
			while(rs.next()){
				formID = rs.getLong("formID");
				System.out.println("Form ID entered: " + formID);
			}
			return formID;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Long addFormToDB(Form f){
		try(Connection conn = getConnection()){
			PreparedStatement ps =conn.prepareStatement(queryForInsertingFormSpecificDetatils,Statement.RETURN_GENERATED_KEYS);

			int i=1;
			ps.setString(i++, f.getName());
			ps.setString(i++, f.getAdminUsername());


			ResultSet rs  = ps.executeQuery();

			Long formID = (long) 0;
			while(rs.next()){
				formID = rs.getLong("formID");
				System.out.println("Form ID entered: " + formID);
			}


			for(int j=0;j<f.getList().size();j++){
				f.getList().get(j).setFormID(formID);
			}
			addQuestionsToDB(f.getList());
			return formID;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateFormName(Long formID, String formName){
		try(Connection conn = getConnection()){
			PreparedStatement ps =conn.prepareStatement(queryForUpdatingFormName);

			int i=1;
			ps.setString(i++, formName);
			ps.setLong(i++, formID);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteQuestion(Long questionID){
		try(Connection conn = getConnection()){
			PreparedStatement ps =conn.prepareStatement(queryForDeletingQuestion);

			int i=1;
			ps.setLong(i++, questionID);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteForm(Long formID){
		try(Connection conn = getConnection()){
			PreparedStatement ps =conn.prepareStatement(queryForDeletingForm);

			int i=1;
			ps.setLong(i++, formID);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
