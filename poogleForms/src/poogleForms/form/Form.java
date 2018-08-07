package poogleForms.form;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Form {
	Map<Long, Question> map;
	ArrayList<Question> list;
	long formID;
	String formName;
	
	public Form(String name, Map<Long, Question> questionMap){
		formName = name;
		map = questionMap;
		for(Entry<Long,Question> e : map.entrySet()){
			list.add(e.getValue());
		}
		//TO-DO
		formID = 0;
	}
	
	public Form(String name, ArrayList<Question> list){
		formName = name;
		this.list = list;
		for(int i=0;i<list.size();i++){
			map.put(list.get(i).getID(), list.get(i));
		}
		//TO-DO
		formID = 0;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}
	
}
