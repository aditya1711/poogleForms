package poogleForms.model.form;

public interface Question {
	public long getID();
	public long getFormID();
	public String getPrompt();
	public TYPES_OF_QUESTION getType();
	public String getHandler();
}
