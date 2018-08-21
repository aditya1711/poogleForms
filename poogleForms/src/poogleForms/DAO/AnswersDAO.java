package poogleForms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class AnswersDAO extends DAO{
	private static AnswersDAO answerDAO = new AnswersDAO();
	
	private static final String queryForgetingAnswerIDsWithUsername = "";
	
	private AnswersDAO(){
		
	}
	public static AnswersDAO getAnswersDAO(String dbURL, String password, String userID) throws SQLException{
		answerDAO.setDAO(dbURL, password, userID);
		return answerDAO;
	}
	public HashSet<Long> getAnswerIDsWithUsername(String username){
		HashSet<Long> answerIDs = new HashSet<Long>();
		try(Connection conn = getConnection()){
			PreparedStatement ps = conn.prepareStatement(queryForgetingAnswerIDsWithUsername);
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
}
