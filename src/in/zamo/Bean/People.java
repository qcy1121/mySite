package in.zamo.Bean;

import java.util.ArrayList;
import java.util.List;

public class People {
	private String key;
	private List<String> names;
	private boolean hasBaby=false;
	private boolean isMale= true;
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
		String[] nameArr = nameStr.split("\\|\\|");
		if(nameArr.length==2){
			String p = nameArr[1];
			if(p.contains("孕"))
			hasBaby = true;
			if(p.contains("女"))
			isMale = false;
		}
		nameArr = nameArr[0].split("__");
		names = new ArrayList<String>();
		for(String n : nameArr){
			if(n!="")names.add(n);			
		}
	} 
	
	public String toJson(){
		StringBuilder sb = new StringBuilder("{");
		sb.append("title:").append(names.get(0)).append(isMale?" 先生":" 女士");
		if(names.size()>1){
			sb.append(" 和 ").append(names.get(1)).append(" 女士");
		}
		sb.append(", hasBaby:").append(hasBaby);
		sb.append("}");
		return sb.toString();
		
		
	}
	
	public void Print(){
		
		System.out.println("key: "+key+"  "+toJson());
	
	}
}
