package in.zamo.Bean;

import java.util.ArrayList;
import java.util.List;

public class People {
	private String key;
	private List<String> names;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public People(String key, List<String> names) {
		super();
		this.key = key;
		this.names = names;
	}
	
	public People(String key, String nameStr) {
		super();
		this.key = key;
		String[] nameArr = nameStr.split("__");
		names = new ArrayList<String>();
		for(String n : nameArr){
			if(n!="")names.add(n);			
		}
	} 
	
	public void Print(){
		String str = "";
		for(String n :names){
			str += " name: "+ n;
		};
		System.out.println("key: "+key+"  "+str);
	
	}
}
