package poogleForms.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.PooledConnection;

import com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolDataSource;

public class DAO {
	private static DAO dao = new DAO();
	
	private String dbURL, password, userID;
	private SQLServerConnectionPoolDataSource ds;
	protected PooledConnection pc;
	
	public void setDAO(String dbURL, String password, String userID) throws SQLException{
		if (this.dbURL==null || this.password== null || this.userID==null) {
			dao.setDbURL(dbURL);
			dao.setPassword(password);
			dao.setUserID(userID);
			dao.setUpDB();
		}
	}
	
	public static DAO getDAO() {
		return dao;
	}

	private void setUpDB() throws SQLException {
		// TODO Auto-generated method stub
		if(pc == null){
			 ds = new SQLServerConnectionPoolDataSource();
			 ds.setURL(getDbURL());
			 ds.setUser(getUserID());
			 ds.setPassword(getPassword());
			 pc = ds.getPooledConnection();
		}
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public Connection getConnection() throws SQLException{
		return pc.getConnection();
	}
}
