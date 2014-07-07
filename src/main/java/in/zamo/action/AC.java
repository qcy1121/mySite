package in.zamo.action;

import in.zamo.Bean.People;
import in.zamo.service.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AC extends HttpServlet {

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
		Service.SaveLog("start");
		doAction(req,resp);
		Service.SaveLog("end");
		//super.doPost(req, resp);
	}
	
	private void doAction(HttpServletRequest req, HttpServletResponse resp){
		String key = (String) req.getAttribute("key");
		if(isEmpty(key))key = req.getParameter("key");
		String url="./404.html";	
		if(!isEmpty(key)){
			People p = loadUser(key);
			if(p!=null){
				url = "./wedding.jsp";
				//req.setAttribute("info", p.toJson());
				req.setAttribute("info",Service.getMessage(p));
			}
		}

		saveLog(req,key);
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		try {
			//resp.sendRedirect(url);
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	private void saveLog(HttpServletRequest req,String key){
		@SuppressWarnings("deprecation")
		String date =  Calendar.getInstance().getTime().toLocaleString();
		String ip = getRemoteAddress(req);
		String log = "Date: "+date+" ip: "+ip+" key:"+key;
		Service.SaveLog(log);
	}
	
	
	private boolean isEmpty(String str){
		return str == null||"".equals(str.trim());
	}
	
	private People loadUser(String key){
		Map<String, People> map = Service.getUsers();
		return map.get(key);
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
  
//    public String getMACAddress(String ip) {  
//        String str = "";  
//        String macAddress = "";  
//        try {  
//            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
//            InputStreamReader ir = new InputStreamReader(p.getInputStream());  
//            LineNumberReader input = new LineNumberReader(ir);  
//            for (int i = 1; i < 100; i++) {  
//                str = input.readLine();  
//                if (str != null) {  
//                    if (str.indexOf("MAC Address") > 1) {  
//                        macAddress = str.substring(  
//                                str.indexOf("MAC Address") + 14, str.length());  
//                        break;  
//                    }  
//                }  
//            }  
//        } catch (IOException e) {  
//            e.printStackTrace(System.out);  
//        }  
//        return macAddress;  
//    }  
}  


