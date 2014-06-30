package in.zamo.service;

import in.zamo.Bean.People;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Service {

	public static Map<String, People> getUsers(){
		if(users!=null)return users;
		String path = Tool.path;
		Properties pro = new Properties();
		Map<String,People> map = new HashMap<String,People>();

		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			pro.load(is);
			Set<Object> set = pro.keySet();
			for(Object o :set){
				String key = o.toString();
				People p = new People(key,pro.getProperty(key));
				map.put(key, p);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		users = map;
		return users;
	}
	
	private static Map<String,People> users= null; 
	
	public static void main(String[] args){
		Map<String ,People> map = getUsers();
		for(String k :map.keySet()){
			People p = map.get(k);
			p.Print();
		}
	}
	

}
