package poogleForms.DAO;

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
import poogleForms.model.form.TextTypeQuestion;

public class FormDAO extends DAO {
	static FormDAO formDAO = new FormDAO();
	
	private static final String queryForGetingQuestionWithID = "select prompt, type as questionType,formID, ID as questionID, options from questions where id = ?;";
	private static final String queryForGetingFormSpecificDetailsWithID = "select name as formName,username,ID as formID from forms where id = ?;";
	private static final String queryForGetingQuestionsWithFormID = "select prompt, type as questionType, formID, ID as questionID, options from questions where formID = ?";
	private static final String queryForGetingFormIDsWithUsername = "select ID as formID from forms where username=?;";
	
	protected FormDAO(){
		super();
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

}
