package in.vnl.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import in.vnl.spring.beans.DataSourcePojo;
import in.vnl.spring.beans.JmsConfigPojo;

@Configuration
@PropertySources({
	@PropertySource("classpath:datasource.properties"),
		
})
public class PropertySourceConfig {

	@Value("${vnl.datasource.username}")
	private String username;
	
	@Value("${vnl.datasource.password}")
	private String password;
	
	@Value("${vnl.datasource.url}")
	private String url;
	
	@Value("${vnl.jms.username}")
	private String jmsUsername;
	
	@Value("${vnl.jms.password}")
	private String jmsPassword;
	
	@Value("${vnl.jms.url}")
	private String jmsUrl;
	
	@Bean
	public DataSourcePojo properyConfig() {
		DataSourcePojo bean=new DataSourcePojo();
		bean .setUsername(username);
		bean.setPassword(password);
		bean.setUrl(url);
		return bean;
	}
	@Bean
	public JmsConfigPojo jmsCongBean() {
		JmsConfigPojo bean=new JmsConfigPojo();
		bean.setUsername(jmsUsername);
		bean.setPassword(jmsPassword);
		bean.setUrl(jmsUrl);
		return bean;
	}
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySourcePlaceHolderConfigurer=new PropertySourcesPlaceholderConfigurer();
		return propertySourcePlaceHolderConfigurer;
	}
}
