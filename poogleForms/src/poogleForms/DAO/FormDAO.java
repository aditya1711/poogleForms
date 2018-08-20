package poogleForms.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import poogleForms.model.form.Form;
import poogleForms.model.form.Question;

public class FormDAO extends DAO {
	static FormDAO formDAO = new FormDAO();

	Connection conn;
	
	private FormDAO(){
		
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
