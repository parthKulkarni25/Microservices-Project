package in.ineuron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {
	
	@Autowired
	private UserService service;
	
	
	
	
	
	

}
