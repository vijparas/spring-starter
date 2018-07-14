package in.vnl.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import in.vnl.spring.interceptor.RequestInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new RequestInterceptor());
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	
}
