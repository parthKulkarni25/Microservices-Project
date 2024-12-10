package in.ineuron.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfigApp {
	
	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/user/showLogin","/user/register","/user/registerSuccess").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
		.loginPage("/user/showLogin")
		.permitAll()
		.loginProcessingUrl("/login")
		.and().rememberMe()
		.and().logout();
		
		return http.build();
	}

}
