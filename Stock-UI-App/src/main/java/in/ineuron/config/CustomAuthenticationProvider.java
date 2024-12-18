package in.ineuron.config;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import in.ineuron.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private final UserService userService;
	
	public CustomAuthenticationProvider(UserService userService) {
		this.userService=userService;
	}
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if(userService.authenticateUser(userId, password)) {
			return new UsernamePasswordAuthenticationToken(userId, password,new ArrayList<>());
		}else
			throw new UsernameNotFoundException("Invalid UserName or Password");
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
