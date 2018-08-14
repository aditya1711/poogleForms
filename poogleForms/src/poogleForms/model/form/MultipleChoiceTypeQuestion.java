package poogleForms.model.form;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	public ArrayList<String> options = new ArrayList<String>();
	
	public MultipleChoiceTypeQuestion() {
		super();
		//to-do
		ID= ++idTemp;
	}
	
	public MultipleChoiceTypeQuestion(long formID, String prompt) {
		super();
		this.formID = formID;
		this.prompt = prompt;
		//to-do
		ID= ++idTemp;
	}
	
	public MultipleChoiceTypeQuestion(String prompt, ArrayList<String> options, long formID) {
		super();
		this.prompt = prompt;
		this.options = options;
		this.formID = formID;
		//to-do
		ID= ++idTemp;
		
	}
	
	public MultipleChoiceTypeQuestion(String prompt, String[] options, long formID) {
		super();
		this.prompt = prompt;
		this.options = new ArrayList<String>(Arrays.asList(options));
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
	
	
	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
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
	
	public void addOption(String option){
		options.add(option);
	}
	
	public String toString(){
		return "MCQ prompt: " + getPrompt() + " options: " + options.toString() + "\n";
	}
}
