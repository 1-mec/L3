package fr.univ.tours.jee.entrainement.td3;

import java.util.HashMap;
import java.util.Map;


public class Database {
	Map<String,Employee> map = new HashMap<>();

	public Map<String, Employee> getAllEmployees() {
		return map;
	}

	public void add(Employee emp) {
		map.put(emp.Username(), emp);
	}
	
	public Employee getEmploye(String str){
		return map.getOrDefault(map.get(str), null);
	}
	
	public void modifyEmployee(String str, Employee emp ) {
		map.replace(str, emp);
	}
	
	public void removeEmployee(String str) {
		map.remove(str);
	}

	public Database() {
		Employee emp = new Employee("toto","toto", "toto","toto", "toto");
		add(emp);
	}
	
	
	
	
}
