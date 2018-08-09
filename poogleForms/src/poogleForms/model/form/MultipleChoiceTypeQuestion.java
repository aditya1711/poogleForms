package poogleForms.model.form;

import poogleForms.model.form.MultipleChoiceTypeQuestion;
import poogleForms.model.form.Question;
import poogleForms.model.form.TYPES_OF_QUESTION;

public class MultipleChoiceTypeQuestion implements Question {

	public final TYPES_OF_QUESTION QUESTION_TYPE = TYPES_OF_QUESTION.MULTIPLE_CHOICES;
	public long ID;
	
	public static long idTemp= 5000;
	
	public long formID;

	public String prompt;
	public static String handler = "MCQHandler.jsp";
	
	public String[] options;
	
	public MultipleChoiceTypeQuestion(String prompt, String[] options, long formID) {
		super();
		this.prompt = prompt;
		this.options = options;
		this.formID = formID;
		//to-do
		ID= ++idTemp;
		
	}
	public long getFormID() {
		return formID;
	}

	public void setFormID(long formID) {
		this.formID = formID;
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
		MultipleChoiceTypeQuestion.handler = handler;
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

}
