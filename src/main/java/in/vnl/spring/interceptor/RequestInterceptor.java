package in.vnl.spring.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import in.vnl.spring.annotations.LoginRequired;
import in.vnl.spring.authorization.CheckAuthorization;
import in.vnl.spring.authorization.CheckAuthorizationImpl;
import in.vnl.spring.exceptions.auth.AuthenticationException;
import in.vnl.spring.exceptions.auth.AuthorizationException;

@Configuration
public class RequestInterceptor extends HandlerInterceptorAdapter {
	
	
	private CheckAuthorization checkAuthorization;
	public RequestInterceptor() {
		this.checkAuthorization=new CheckAuthorizationImpl();
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			
			if(handlerMethod.getMethod().isAnnotationPresent(LoginRequired.class)) {
				LoginRequired loginRequired=handlerMethod.getMethod().getAnnotation(LoginRequired.class);
				String[] roles=loginRequired.roles();
				checkAuthorization.checkAuthentication();
				if(roles.length>0) {
					checkAuthorization.checkAuthorization(roles);
				}
				
			}
		}
		catch(AuthenticationException exception) {
			response.sendRedirect("/");
		}
		catch(AuthorizationException exception) {
			response.sendRedirect("/");
		}
		catch(Exception exception) {
			
		}
		
		return super.preHandle(request, response, handler);
	}
	
	
	public void validateAuthorization() {
		
	}
	

	
}
