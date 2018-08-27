package poogleForms.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import poogleForms.model.form.Answer;

public class AnswersDAO extends DAO{
	
	private static AnswersDAO answerDAO = new AnswersDAO();
	
	public static  ObjectMapper mapper = new ObjectMapper();
	
	private static final String queryForGetingAnswerIDsWithUsername = "select ID as answerID, answerJson from answers where json_value(answerJson, '$.username')  = ?;";
	private static final String queryForGetingAnswerWithID ="select ID as answerID, answerJson from answers where ID=?;";
	
	private static final String queryForInsertingAnswer  =  "begin try " + 
			"begin transaction " + 
			"insert into IDTable " + 
			"values ('3'); " +
			"insert into answers (ID,answerJson) " + 
			"values (SCOPE_IDENTITY(),?); " +
			"commit; " +
			"end try " +
			"begin catch " +
			"rollback; " +
			"end catch; " ;
	
	private AnswersDAO(){
		
	}
	public static AnswersDAO getAnswersDAO(String dbURL, String userID, String password) throws SQLException{
		answerDAO.setDAO(dbURL, userID, password);
		return answerDAO;
	}
	public HashSet<Long> getAnswerIDsWithUsername(String username){
		HashSet<Long> answerIDs = new HashSet<Long>();
		try(Connection conn = getConnection()){
			PreparedStatement ps = conn.prepareStatement(queryForGetingAnswerIDsWithUsername);
			int i=1;
			ps.setString(i++,username);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				answerIDs.add(rs.getLong("answerID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return answerIDs;
	}
	
	public Answer getAnswerWithID(Long ID){
		try(Connection conn = getConnection()){
			PreparedStatement ps = conn.prepareStatement(queryForGetingAnswerWithID);
			int i=1;
			ps.setLong(i++,ID);
			ResultSet rs= ps.executeQuery();
			rs.next();
			Answer answer = mapper.readValue(rs.getString("answerJson"), Answer.class);
			answer.setID(rs.getLong("answerID"));
			return answer;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addAnswerInDB(Answer ans){
		try(Connection conn = getConnection()){
			PreparedStatement ps = conn.prepareStatement(queryForInsertingAnswer);
			int i=1;
			ps.setString(i++, ans.toJSONString());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
