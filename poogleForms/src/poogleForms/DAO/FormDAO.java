package poogleForms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import poogleForms.model.form.Form;
import poogleForms.model.form.Question;

public class FormDAO extends DAO {
	static FormDAO formDAO = new FormDAO();
	
	private static final String queryForGetingQuestionWithID = "select prompt, type,formID,id,options from questions where id = ?;";
	private static final String queryForGetingFormSpecificDetailsWithID = "select name,username,ID from forms where id = ?;";
	private static final String queryForgetingFormIDsWithUsername = "";
	private FormDAO(){
		
	}
	
	public HashSet<Long> getFormIDsWithUsername(String username){
		HashSet<Long> formIDs = new HashSet<Long>();
		try(Connection conn = getConnection()){
			PreparedStatement ps = conn.prepareStatement(queryForgetingFormIDsWithUsername);
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
	
	public static FormDAO getFormDAO(String dbURL, String password, String userID) throws SQLException{
		formDAO.setDAO(dbURL, password, userID);
		return formDAO;
	}
	
	public Form getForm(Long ID){
		return null;
	}
	public Question getQuestion(Long ID){
		return null;
	}
}
