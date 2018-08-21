package poogleForms.model.form;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;

import poogleForms.DAO.FormDAO;
import poogleForms.model.misc.IDAble;
import poogleForms.model.misc.JSONConvertible;

public class Answer implements IDAble<Long>, JSONConvertible {
	
	public Long questionID;
	private Question belongedQuestion;
	public String username;
	
	public Long ID;
	ArrayList<String> answers;
	
	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	
	@Override
	public String toJSONString() throws JsonProcessingException {
		// TODO Auto-generated method stub
		return ow.writeValueAsString(this);
	}

	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void setID(Long t) {
		// TODO Auto-generated method stub
		ID=t;
	}
	
	public String toString(){
		return "QuestionID: " + getQuestionID() + "\n"
				+ "Question: " + belongedQuestion.getPrompt() + "\n"
						+ "Answer: " + answers.toString() + "\n";
	}
	
}
