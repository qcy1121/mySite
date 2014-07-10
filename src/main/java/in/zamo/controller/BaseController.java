package in.zamo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BaseController {

	@RequestMapping(value="/rest/{key}", method = RequestMethod.GET)
	public String wedding(@PathVariable String key,ModelMap model){
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - welcome()");
		 
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return "test";
	}
}
