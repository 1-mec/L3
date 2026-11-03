package fr.univ.jee.tp_note;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DataBase {

	Map<Integer,Journal> map = new HashMap<Integer, Journal>();
	
	public Journal findByUser(String user) {
		 for (Journal Journal : map.values()) {
	            if (Journal.login().equals(user)) {
	                return Journal;
	            }
	        }
		return null;
	}
	
	public Map<Integer,Journal> findAll(){
		return map;
	}
	
	public void insert(Journal c) {
		map.put(count()+1 , c);
	}

	public void delete(Journal c) {
		map.remove(count()+1,c);
	}
	
	public void update(Journal c) {
			map.replace(count()+1, c);
	}
	
	public DataBase() {
		insert(new Journal("toto","test",LocalDateTime.now(),true));
	}
	
	public int count() {
		Iterator<Map.Entry<Integer, Journal>> it = map.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			it.next();
			i++;
		}
		return i;
		
	}
}
