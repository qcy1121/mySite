package in.zamo.action;

import in.zamo.Bean.People;
import in.zamo.service.Service;

import java.io.IOException;
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
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doAction(req,resp);
		super.doPost(req, resp);
	}
	
	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String key = (String) req.getAttribute("key");
		if(isEmpty(key))key = req.getParameter("key");
		
		if(isEmpty(key)){
			
		}else{
			
		}
		String url="/pages/error.jsp";	
//		HttpSession session = request.getSession();
//		if(emId!=null && !"".equals(emId.trim())){
//			Employee em= service.getEm(emId);
//			request.setAttribute("emId", emId);
//			session.setAttribute("em"+emId, em);
//			url="/pages/yuangong.jsp";
//		}		
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
	private boolean isEmpty(String str){
		return str == null||"".equals(str.trim());
	}
	
	private People loadUser(String key){
		Map<String, People> map = Service.getUsers();
		return map.get(key);
	}

}
