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
	
	long adminID;
	
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

	public long getAdminID() {
		return adminID;
	}

	public void setAdminID(long adminID) {
		this.adminID = adminID;
	}
	
	public Form(String name, Map<Long, Question> questionMap, long adminID){
		this.name = name;
		map = questionMap;
		list = new ArrayList<Question>();
		for(Entry<Long,Question> e : map.entrySet()){
			list.add(e.getValue());
		}
		this.adminID = adminID;
		//TO-DO
		formID = 0;
	}
	
	public Form(String name, ArrayList<Question> list, long adminID){
		this.name = name;
		this.list = list;
		map = new HashMap<Long, Question>();
		for(int i=0;i<list.size();i++){
			map.put(list.get(i).getID(), list.get(i));
		}
		this.adminID = adminID;
		//TO-DO
		formID = 0;
	}
	
}
