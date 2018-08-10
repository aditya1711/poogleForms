package poogleForms.model.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Form {
	Map<Long, Question> map;
	ArrayList<Question> list;
	long formID;
	
	String name;
	
	String adminUsername;
	
	public String getName() {
		return name;
	}

	public void setName(String formName) {
		this.name = formName;
	}
	
	public Map<Long, Question> getMap() {
		return map;
	}

	public void setMap(Map<Long, Question> map) {
		this.map = map;
	}

	public ArrayList<Question> getList() {
		return list;
	}

	public void setList(ArrayList<Question> list) {
		this.list = list;
	}

	public long getFormID() {
		return formID;
	}

	public void setFormID(long formID) {
		this.formID = formID;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminID(String username) {
		this.adminUsername = username;
	}
	
	public Form(String name, Map<Long, Question> questionMap, String adminUsername){
		this.name = name;
		map = questionMap;
		list = new ArrayList<Question>();
		for(Entry<Long,Question> e : map.entrySet()){
			list.add(e.getValue());
		}
		this.adminUsername = adminUsername;
		//TO-DO
		formID = 0;
	}
	
	public Form(String name, ArrayList<Question> list, String adminUsername){
		this.name = name;
		this.list = list;
		map = new HashMap<Long, Question>();
		for(int i=0;i<list.size();i++){
			map.put(list.get(i).getID(), list.get(i));
		}
		this.adminUsername = adminUsername;
		//TO-DO
		formID = 0;
	}
	
}
