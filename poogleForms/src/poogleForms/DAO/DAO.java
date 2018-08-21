package poogleForms.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.PooledConnection;

import com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolDataSource;

public class DAO {
	
	private String dbURL, password, userID;
	private SQLServerConnectionPoolDataSource ds;
	public PooledConnection pc;
	
	protected DAO(){
		
	}
	
	public void setDAO(String dbURL, String userID, String password) throws SQLException{
		if (this.dbURL==null || this.password== null || this.userID==null) {
			System.out.println("setting DAO");
			setDbURL(dbURL);
			setPassword(password);
			setUserID(userID);
			setUpDB();
		}
	}

	private void setUpDB() throws SQLException {
		// TODO Auto-generated method stub
		if(pc == null){
			System.out.println("PC is null");
			 ds = new SQLServerConnectionPoolDataSource();
			 ds.setURL(getDbURL());
			 ds.setUser(getUserID());
			 ds.setPassword(getPassword());
			 
			 pc = ds.getPooledConnection();
			 if(pc==null){
				 System.out.println("PC IS STILL NULL");
			 }
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
		if(pc==null){
			System.out.println("PC IS NULL IN getConnection()");
		}
		return pc.getConnection();
	}
}
