package in.ineuron.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.client.IFeignClient;

@RestController
@RequestMapping("/api/calc")
public class StockCalcRestController {
	
	@Autowired
	private IFeignClient client;
	
	@GetMapping("/calculate/{companyName}/{quantity}")
	public ResponseEntity<?> calculateStockPrice(@PathVariable String companyName,@PathVariable Integer quantity){
		
		ResponseEntity<?> responseEntity = null;
		Double totalPrice = null;
		System.out.println(companyName + quantity);
		
		try {
			responseEntity = client.stockPrice(companyName);
			System.out.println(responseEntity);
			int statusCode = responseEntity.getStatusCode().value();
			if (statusCode ==200) {
				Double companyPrice = (Double) responseEntity.getBody();
				totalPrice = quantity*companyPrice;
				String response = "Total cost :: " + totalPrice;
				responseEntity = new ResponseEntity<String>(response,HttpStatus.OK);
			}	
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("Company Not Found",HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity;
	}

}
