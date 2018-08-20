package poogleForms.model.clients;

public enum ClientTypes {
	LEVEL1, LEVEL2;
	public static ClientTypes getClientType(String s){
		if(s.equals("level1"))
			return LEVEL1;
		else if(s.equals("level2"))
			return LEVEL2;
		else 
			return null;
	}
}
