package poogleForms.model.clients;

public class LoginCredentials {
	private String username, password;
	private ClientTypes type;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ClientTypes getType() {
		return type;
	}
	public void setType(ClientTypes type) {
		this.type = type;
	}
	
}
