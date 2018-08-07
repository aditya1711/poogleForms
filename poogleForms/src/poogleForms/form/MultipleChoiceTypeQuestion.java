package poogleForms.form;

public class MultipleChoiceTypeQuestion implements Question {

	public final TYPES_OF_QUESTION QUESTION_TYPE = TYPES_OF_QUESTION.MULTIPLE_CHOICES;
	public long questionID;
	
	public long formID;

	public String prompt;
	public String handler;
	
	public String[] options;
	
	public MultipleChoiceTypeQuestion(String prompt, String[] options, long formID) {
		super();
		this.prompt = prompt;
		this.options = options;
		this.formID = formID;
		//to-do
		questionID= 0;
		
	}
	public long getFormID() {
		return formID;
	}

	public void setFormID(long formID) {
		this.formID = formID;
	}
	public long getID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}
	
	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public void setHandler(String handler) {
		this.handler = handler;
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

}
