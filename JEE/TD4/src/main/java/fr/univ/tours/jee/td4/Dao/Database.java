package fr.univ.tours.jee.td4.Dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Database {
	
	Map<Integer,Cours> map = new HashMap<Integer, Cours>();
	
	public Cours findByID(int id) {
		 for (Cours cours : map.values()) {
	            if (cours.Cours_id() == id) {
	                return cours;
	            }
	        }
		return null;
	}
	
	public Map<Integer,Cours> findAll(){
		return map;
	}
	
	public void insert(Cours c) {
		
		map.put(c.Cours_id() , c);
	}

	public void delete(Cours c) {
		map.remove(c.Cours_id(),c);
	}
	
	public void update(Cours c) {
			map.replace(c.Cours_id(), c);
	}
	
	public Database() {
	}
	
	public int count() {
		Iterator<Map.Entry<Integer, Cours>> it = map.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			it.next();
			i++;
		}
		return i;
		
	}
	
}
