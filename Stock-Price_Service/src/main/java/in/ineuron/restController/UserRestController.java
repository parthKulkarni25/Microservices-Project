package in.ineuron.restController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.entity.UserDetails;
import in.ineuron.service.IStockUsersService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	private IStockUsersService service;
	
	@PostMapping("/register")
	public ResponseEntity<String> userRegister(@RequestBody UserDetails user){
		System.out.println(user);
		String msg = service.registerUser(user);
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<Boolean> authenticateUser(@RequestParam String userId,@RequestParam String password){
		System.out.println(userId);
		Boolean status = service.authenticateUser(userId, password);
		System.out.println("status is :"+status);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}

}
