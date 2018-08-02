package in.vnl.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();

	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		try {
			
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}

		catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
        .authorizeRequests()
            .antMatchers(
                    "/",
                    "/js/**",
                    "/css/**",
                    "/img/**",
                    "/webjars/**").permitAll();
	http.authorizeRequests()
	.and().formLogin()
    .loginPage("/login").defaultSuccessUrl("/",true)
    .permitAll()
        .and().logout()                                    
        .permitAll();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	            .ignoring()
	            .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
	}
	
}
