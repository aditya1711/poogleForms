package poogleForms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import poogleForms.model.clients.Client;
import poogleForms.model.clients.ClientTypes;
import poogleForms.model.clients.Level1Clients;
import poogleForms.model.clients.Level2Clients;
import poogleForms.model.clients.LoginCredentials;

public class ClientsDAO extends DAO {
	private static ClientsDAO clientsDAO = new ClientsDAO();
	
	private static final String queryForAllClientData = "select lc.username, lc.password, lc.clientType, c.firstName, c.lastName from loginCredentials as lc inner join client as c on c.username = lc.username;"; 
	
	private static final String queryForAllClientDataForOneUser = "select lc.username, lc.password, lc.clientType, c.firstName, c.lastName from (loginCredentials as lc inner join client as c on c.username = lc.username)" + 
																		"where lc.username = ? and lc.password = ?";
	
	private static final String queryToCheckForUsernamePasswordPair = "use poogleForms" +
																			"if exists(select username from loginCredentials where username= ? and password= ?)" +
																				"select 1;" +
																					"else" +
																						"select 0;";
	
	private FormDAO formDAO;
	private AnswersDAO answersDAO;
	
	public AnswersDAO getAnswersDAO() {
		return answersDAO;
	}

	public void setAnswersDAO(AnswersDAO answersDAO) {
		this.answersDAO = answersDAO;
	}

	public FormDAO getFormDAO() {
		return formDAO;
	}

	public void setFormDAO(FormDAO formDAO) {
		this.formDAO = formDAO;
	}

	private ClientsDAO(){
		
	}
	
	public static ClientsDAO getFormDAO(String dbURL, String password, String userID) throws SQLException{
		clientsDAO.setDAO(dbURL, password, userID);
		clientsDAO.setFormDAO(FormDAO.getFormDAO(dbURL, password, userID));
		clientsDAO.setAnswersDAO(AnswersDAO.getAnswersDAO(dbURL, password, userID));
		return clientsDAO;
	}
	
	public Client getClientByUsername(String username, String password){
		return addClientData(username, password);
	}
	
	private Client addClientData(String username, String password){
		Client c = new Level1Clients();
		PreparedStatement ps;
		try(Connection conn = getConnection();) {
			ps = conn.prepareStatement(queryForAllClientDataForOneUser);
			int i=1;
			ps.setString(i++, username);
			ps.setString(i++, password);
			ResultSet rs = ps.executeQuery();
			
			if(ClientTypes.getClientType(rs.getString("clientType")).equals(ClientTypes.LEVEL2)){
				c = new Level2Clients();
				addLevel2Data(c,username);
			}
			c.setLoginCredentials(new LoginCredentials(rs.getString("username"),rs.getString("password"), ClientTypes.getClientType(rs.getString("clientType"))));
			c.setFirstName(rs.getString("firstName"));
			c.setFirstName(rs.getString("lastName"));
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addLevel1Data(c, username);
		return c;
	}
	private void addLevel1Data(Client client, String username){
		Level1Clients l1c = (Level1Clients)(client);
		l1c.setAnswersIDs(answersDAO.getAnswerIDsWithUsername(username));
	}
	private void addLevel2Data(Client client, String username){
		Level2Clients l2c = (Level2Clients)(client);
		l2c.setFormsCreatedIDs(formDAO.getFormIDsWithUsername(username));
		
	}

	public boolean checkForUsernamePasswordPair(String username, String password){
		try(Connection conn = pc.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(queryToCheckForUsernamePasswordPair);
			int i=1;
			ps.setString(i++, username);
			ps.setString(i++, password);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(rs.getInt(1) == 1)
				return true;
			else
				return false;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
