package in.ineuron.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import in.ineuron.service.UserService;

@EnableWebSecurity
@Configuration
public class SecurityConfigApp {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private  CustomAuthenticationProvider customAuthProvider;
	
	
	@Autowired
	public void configAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(customAuthProvider);
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
		
	@Bean
	public SecurityFilterChain config(HttpSecurity http,AuthenticationManager auth) throws Exception {
		
		CustomAuthenticationFilter customFilter = new CustomAuthenticationFilter(service);
		customFilter.setAuthenticationManager(auth);
		customFilter.setFilterProcessesUrl("/auth/login");
		
		http.authorizeRequests().antMatchers("/user/showLogin","/user/register","/user/registerSuccess").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
		.formLogin()
		.loginPage("/user/showLogin")
		.permitAll()
		.successHandler((request,response,authentication) ->{
			response.sendRedirect("/view/home");
		})
		.failureHandler((request,response,authentication) ->{
			response.sendRedirect("/user/showLogin?error");
		})
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutSuccessUrl("/user/showLogin?logout=true");
		
		return http.build();
	}
	
	

}
