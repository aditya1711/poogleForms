package poogleForms.model.clients;

import java.util.HashSet;
import java.util.Iterator;

public class Level1Clients extends ClientAbstract {
	
	private HashSet<Long> answerIDs;
	

	public HashSet<Long> getAnswersIDs() {
		return answerIDs;
	}
	public void setAnswersIDs(HashSet<Long> answerIDs) {
		this.answerIDs = answerIDs;
	}
	
	public void addAnsweredForm(HashSet<Long> answerIDs){
		Iterator<Long> it = answerIDs.iterator();
		while(it.hasNext()){
			this.answerIDs.add(it.next());
		}
	}
	
	public void addAnswer(Long answerID){
		answerIDs.add(answerID);
	}
	
	public Answer getAnswer(Long answerID){
		return DAO.getDAO().getAnswer(answerID);
	}
}
