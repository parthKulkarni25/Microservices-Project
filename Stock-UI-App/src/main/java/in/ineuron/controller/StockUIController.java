package in.ineuron.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.ineuron.service.StockService;

@Controller
@RequestMapping("/view")
public class StockUIController {

	@Autowired
	private StockService service;
	
	@GetMapping("/home")
	public String loadPage() {
		
		return "index";
	}
	
	@GetMapping("/getTotalCost")
	public String getTotalCost(HttpServletRequest request,Model model) {
		
		String companyName = request.getParameter("companyName");
		String quantity = request.getParameter("quantity");
		System.out.println(companyName + quantity);
		String response = service.getTotalPrice(companyName, Integer.parseInt(quantity));
		model.addAttribute("msg",response);
		
		return "index";
	}
}
