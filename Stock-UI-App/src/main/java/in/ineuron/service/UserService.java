package in.ineuron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import in.ineuron.entity.UserDetails;

@Service
public class UserService {
	
	@Autowired
	private WebClient webclient;
	
	public String userRegister(UserDetails user) {
		String REST_END_POINT = "http://localhost:2222/api/user/register";
		try {
			return webclient.post().uri(REST_END_POINT)
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(user)
					.retrieve()
					.bodyToMono(String.class)
					.block();
		} catch (Exception e) {
			e.printStackTrace();
			return "Unable to add user";
		}
	}
	
	public boolean authenticateUser(String userId,String password) {
		//String REST_END_POINT = "http://localhost:2222/api/user/authenticate";
		WebClient webClient = WebClient.builder().baseUrl("http://localhost:2222").build();
		
		return webClient.post().uri(uriBuilder ->  uriBuilder
				.path("/api/user/authenticate")
				.queryParam("userId",userId)
				.queryParam("password", password)
				.build())
				.retrieve()
				.bodyToMono(Boolean.class)
				.block();
	}

	

	

}
