package in.ineuron.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import in.ineuron.entity.UserDetails;

@RequestMapping("/user")
@Controller
public class UserLoginController {
	
	@GetMapping("/showLogin")
	public String getLoginPage() {
		
		return "show-login";
	}
	
	@GetMapping("/register")
	public String registerUser() {
		
		return "user-register";
	}
	
	@PostMapping("/registerSuccess")
	public String successRegister(@ModelAttribute UserDetails user,Model model) {
		System.out.println(user);
		String msg = "login successfully";
		model.addAttribute("msg", msg);
		return "success";
	}
	
	

}
