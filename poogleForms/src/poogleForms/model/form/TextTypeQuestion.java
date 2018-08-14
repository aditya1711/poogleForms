package poogleForms.model.form;

public class TextTypeQuestion implements Question{

	

	public final TYPES_OF_QUESTION QUESTION_TYPE = TYPES_OF_QUESTION.TEXT_QUESTIONS;
	public long ID;
	
	public static long tempID=5000;
	
	public long formID;
	
	public String prompt;
	public static String handler = "TextTypeQuestionHandler.jsp";

	public TextTypeQuestion() {
		super();
	}
	
	public TextTypeQuestion(long formID, String prompt) {
		super();
		this.formID = formID;
		this.prompt = prompt;
	}
	
	public TextTypeQuestion(String prompt, long formID) {
		super();
		this.prompt = prompt;
		this.formID= formID;
		//to-do
		ID = --tempID;
	}

	public long getFormID() {
		return formID;
	}

	public void setFormID(long formID) {
		this.formID = formID;
	}

	private void setQuestionID(long questionID) {
		this.ID = questionID;
	}
	
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}
	
	@Override
	public long getID() {
		return ID;
	}

	@Override
	public String getPrompt() {
		// TODO Auto-generated method stub
		return prompt;
	}

	@Override
	public TYPES_OF_QUESTION getType() {
		// TODO Auto-generated method stub
		return QUESTION_TYPE;
	}

	@Override
	public String getHandler() {
		// TODO Auto-generated method stub
		return handler;
	}
	
	public String toString(){
		return "TEXT prompt: " + getPrompt() + "\n";
	}

}
