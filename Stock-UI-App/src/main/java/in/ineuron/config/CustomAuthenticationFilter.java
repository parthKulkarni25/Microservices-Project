package in.ineuron.config;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.ineuron.service.UserService;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	private final UserService service;
	
	
	public CustomAuthenticationFilter(UserService service) {
		this.service=service;
		setUsernameParameter("userId");
		setPasswordParameter("password");
	}
	
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
			throws AuthenticationException{
		String userId = obtainUsername(request);
		String password = obtainPassword(request);
		System.out.println(userId + password);
		
		if(userId == null || password ==null) {
			throw new BadCredentialsException("Invalid Credentials");
		}
		
		boolean authenticated = service.authenticateUser(userId, password);
		if(authenticated) {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					userId,
					password,
					new ArrayList<>());
			return this.getAuthenticationManager().authenticate(authRequest);
		} else {
			throw new BadCredentialsException("Invalid Credentials");
		}
	}
	
	
}
