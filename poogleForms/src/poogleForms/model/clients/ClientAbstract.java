package poogleForms.model.clients;

public abstract class ClientAbstract implements ClientInterface {
	LoginCredentials loginCredentials;
	String firstName, lastName;
	ClientTypes clientType;
	public LoginCredentials getLoginCredentials() {
		return loginCredentials;
	}
	public void setLoginCredentials(LoginCredentials loginCredentials) {
		this.loginCredentials = loginCredentials;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public ClientTypes getClientType() {
		return clientType;
	}
	public void setClientType(ClientTypes clientType) {
		this.clientType = clientType;
	}
}
