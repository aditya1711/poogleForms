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
	
	private static final String queryForAllClientData = "use poogleForms; select lc.username, lc.password, lc.clientType, c.firstName, c.lastName from loginCredentials as lc inner join client as c on c.username = lc.username;"; 
	private static final String queryForAllLevel1Data = ""; 
	private static final String queryForAllLevel2Data = ""; 
	
	Connection conn;
	
	private ClientsDAO(){
		
	}
	
	public static ClientsDAO getFormDAO(String dbURL, String password, String userID) throws SQLException{
		clientsDAO.setDAO(dbURL, password, userID);
		return clientsDAO;
	}
	
	public Client getClientByUsername(String username){
		return addClientData();
		
	}
	
	private Client addClientData(){
		Client c = new Level1Clients();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(queryForAllClientData);
			ResultSet rs = ps.executeQuery();
		
			c.setLoginCredentials(new LoginCredentials(rs.getString("username"),rs.getString("password"), ClientTypes.getClientType(rs.getString("clientType"))));
			c.setFirstName(rs.getString("firstName"));
			c.setFirstName(rs.getString("lastName"));
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addLevel1Data(c);
		
		if(c.getLoginCredentials().getType().equals(ClientTypes.LEVEL2)){
			addLevel2Data(c);
		}
		
		return c;
	}
	private void addLevel1Data(Client client){
		Level1Clients l1c = (Level1Clients)(client);
		try {
			PreparedStatement ps = conn.prepareStatement(queryForAllLevel1Data);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				l1c.addAnswer(rs.getLong("answerID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addLevel2Data(Client client){
		Level2Clients l2c = (Level2Clients)(client);
		try {
			PreparedStatement ps = conn.prepareStatement(queryForAllLevel2Data);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				l2c.addAnswer(rs.getLong("formID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
