package in.zamo.action;

import in.zamo.service.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LOGAC extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doAction(req,resp);
		//super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Service.SaveLog("start");
		doAction(req,resp);
		//Service.SaveLog("end");
		//super.doPost(req, resp);
	}
	
	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String key = (String) req.getAttribute("key");
		if(isEmpty(key))key = req.getParameter("key");
		
		saveLog(req,null);
		if(!isEmpty(key)&& key.equals("qcy861121")){
			String fileType = "text/plain";
	        // Find this file id in database to get file name, and file type

	        // You must tell the browser the file type you are going to send
	        // for example application/pdf, text/plain, text/html, image/jpg
	        resp.setContentType(fileType);

	        // Make sure to show the download dialog
	        resp.setHeader("Content-disposition","attachment; filename=log.txt");

	        // Assume file name is retrieved from database
	        // For example D:\\file\\test.pdf

	        File my_file = Service.getLogFile();

	        // This should send the file to browser
	        OutputStream out = resp.getOutputStream();
	        FileInputStream in = new FileInputStream(my_file);
	        byte[] buffer = new byte[4096];
	        int length;
	        while ((length = in.read(buffer)) > 0){
	           out.write(buffer, 0, length);
	        }
	        in.close();
	        out.flush();
		}else{
			String url="./404.html";	
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, resp);
		}

        
		

	}
	
	private void saveLog(HttpServletRequest req,String key){
		@SuppressWarnings("deprecation")
		String date =  Calendar.getInstance().getTime().toLocaleString();
		String ip = getRemoteAddress(req);
		String log = "Date: "+date+" ip: "+ip+" ___LogFile download";
		Service.SaveLog(log);
	}
	
	
	private boolean isEmpty(String str){
		return str == null||"".equals(str.trim());
	}
	
	
	public String getRemoteAddress(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }
}  


