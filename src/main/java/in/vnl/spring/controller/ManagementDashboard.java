package in.vnl.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import in.vnl.spring.annotations.LoginRequired;
import in.vnl.spring.annotations.ManagedMethod;

@Controller
public class ManagementDashboard {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	@RequestMapping("/management")
	private String displayAvailableRoutes(Model model) {
		Map<RequestMappingInfo, HandlerMethod> requestMappings=requestMappingHandlerMapping.getHandlerMethods();
		List<Map<String,String>> managedMethods=new ArrayList<>();
		for(Map.Entry<RequestMappingInfo, HandlerMethod> entry:requestMappings.entrySet()) {
			HandlerMethod handlerMethod = (HandlerMethod) entry.getValue();
			
			if(handlerMethod.hasMethodAnnotation(ManagedMethod.class)) {
				Map<String,String> managedBeanProperties=new HashMap<>();
				
				if(handlerMethod.hasMethodAnnotation(LoginRequired.class)) {
					 managedBeanProperties.put("Login Required", "Yes");
					 //managedBeanProperties.put(key, value)
				}
			}
			
		}
		model.addAttribute( "endPoints", requestMappingHandlerMapping.getHandlerMethods().keySet());
		 
		 return "management/routes";
	}
}
