package in.zamo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Tool {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		String[] names = getUsers();
		Properties pro = new Properties();
		for(String n :names){
			String key = getKeys(n);
			pro.put(key,n);
		}
		write(pro);
	}
	
	private static void write(Properties pro) throws FileNotFoundException, IOException{
		//String p = Thread.currentThread().getContextClassLoader().getResource("").toString()+File.separator+path;
		String p = path;
		pro.store(new FileOutputStream(p),"");
	}
	public static String path = "users.properties";
	private static String[] getUsers(){
		String[] names ={
			"荣卫建__王岳"	,
			"朱文斌",
			"马桂霞__||女孕",
			"徐新龙",
			"张则前",
			"牛丽瑞||女",
			"孙建明",
			"张爱臣",
			"刘继生",
			"秦彦卿",
			"腾先超",
			"武法行__姜杨丽||孕",
			"芦桂明",
			"戚小斌",
			"生先水",
			//"李素超"
			
				
		};
		return names;
	}
	
	private static String getKeys(String name){
		String[] keyArr = decode(name).toString().split("@");
		return keyArr[keyArr.length-1];
	}
	/**  
	    * 编码  
	    * @param bstr  
	    * @return String  
	    */    
	   public static String encode(byte[] bstr){    
	   return new sun.misc.BASE64Encoder().encode(bstr);    
	   }    
	   
	   /**  
	    * 解码  
	    * @param str  
	    * @return string  
	    */    
	   public static byte[] decode(String str){    
	   byte[] bt = null;    
	   try {    
	       sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
	       bt = decoder.decodeBuffer( str );    
	   } catch (IOException e) {    
	       e.printStackTrace();    
	   }    
	   System.out.println(bt);
	       return bt;    
	   }  
}
