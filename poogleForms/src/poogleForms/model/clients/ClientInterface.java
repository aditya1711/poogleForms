package poogleForms.model.clients;

public interface ClientInterface {
	public LoginCredentials getLoginCredentials();
	public void setLoginCredentials(LoginCredentials lc);

	public ClientTypes getClientType();
	public void setClientType(ClientTypes ct);
}
