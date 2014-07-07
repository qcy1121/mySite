package in.zamo.service;

import in.zamo.Bean.People;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
	
	public static void SaveLog(String log){
		
		Write2File(path,log);
	}
	private static void Write2File(String path, String content) {
        try {
                File f = new File(path);
                if(!f.exists())f.createNewFile();
                    BufferedWriter output = new BufferedWriter(new FileWriter(f,true));
                    output.write(content+"\n");
                    output.close();
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	private static Map<String,People> users= null; 
	private static String path = "./log.log";
	public static void main(String[] args){
		Map<String ,People> map = getUsers();
		for(String k :map.keySet()){
			People p = map.get(k);
			SaveLog("key: " + p.getKey()+ p.toJson());
		}
	}

	public static File getLogFile() {
		return  new File(path);
	}

	public static String getMessage(People p) {
		String bs="_bs_",be="_be_",tt="_title_";
		String title = p.getTitle(bs,be),msg = getMsg();
		msg = msg.replace(tt, title);
		return msg;
		
	}
	
	private static String msg =null;
	private static String getMsg(){
		if(msg!=null)return msg;
		String ss = "_ss_",se="_se_",bs="_bs_",be="_be_",ls="_ls_",le="_le_",bl="_bl_",title="_title_";
		StringBuilder sb = new StringBuilder("");
		List<String> list = new ArrayList<String>();
		list.add("敬邀: "+title);
		list.add("");
		list.add(bl+"谨定于 公历"+bs+"2014年7月5号"+be+"（"+bs+"周六"+be+"）"+ bs+"中午11点"+be+
				" , 在 "+bs+ls+"丰收日联洋广场店（浦东新区 芳甸路300号联洋广场A区4楼近迎春路） "+le+be+" 举办喜宴。");
		list.add("");
		list.add(bl+"带着满心欢喜，敬备喜筵，恭请阁下光临!");
		list.add("");
		for(String s : list){
			sb.append(ss).append(s).append(se);
		}
		
		return sb.toString();
	}
	

}
