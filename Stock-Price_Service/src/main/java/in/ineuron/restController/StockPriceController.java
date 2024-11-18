package in.ineuron.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.ineuron.service.IStockService;

@RestController
@RequestMapping("/api/price")
public class StockPriceController {
	
	@Autowired
	private IStockService service;
	
	@GetMapping("/stockPrice/{companyName}")
	public ResponseEntity<Double> getStockPrice(@PathVariable String companyName){
		
		Double stockPrice = service.findByCompany(companyName);
		System.out.println(companyName + "  " +stockPrice);
		return new ResponseEntity<Double>(stockPrice,HttpStatus.OK);
	}
	
	@PostMapping("/addStock/{companyName}/{stockPrice}")
	public ResponseEntity<String> addStock(@PathVariable String companyName,@PathVariable Double stockPrice){
		String result = service.addCompanyStock(companyName, stockPrice);
		System.out.println(result);
		return new ResponseEntity<String>(result,HttpStatus.OK); 
	}

}
