package in.ineuron.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.ineuron.entity.StockFundamentals;
import in.ineuron.entity.StockPrice;
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
	
	@GetMapping("/listStock")
	public ResponseEntity<List<StockPrice>> getListStocks(){
		List<StockPrice> listStock = service.getListStocks();
		System.out.println(listStock);
		return new ResponseEntity<List<StockPrice>>(listStock,HttpStatus.OK);
	}
	
	@GetMapping("/getStockbyId/{stockId}")
	public ResponseEntity<?> getStockById(@PathVariable Integer stockId){
		StockPrice stock = service.getStockById(stockId);
		if(stock !=null) 
			return new ResponseEntity<StockPrice>(stock,HttpStatus.OK);
		else
			return new ResponseEntity<String>("Stock is not available",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getFundamentals/{stockId}")
	public ResponseEntity<StockFundamentals> getStockFundamentalsById(@PathVariable Integer stockId){
		StockFundamentals fundamentals = service.getStockFundamentals(stockId);
		System.out.println(fundamentals);
		return new ResponseEntity<StockFundamentals>(fundamentals,HttpStatus.OK);
	}
	
	@PostMapping("/addFundamentals")
	public ResponseEntity<String> addStockFundamentals(@RequestBody StockFundamentals fundamentals){
		System.out.println(fundamentals);
		StockPrice stock = new StockPrice();
		stock.setCompanyName(fundamentals.getStock().getCompanyName());
		stock.setCompanyPrice(fundamentals.getStock().getCompanyPrice());
		
		String response = service.addStockFundamentals(fundamentals, stock);
		
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@PostMapping("/editStockPrice")
	public ResponseEntity<String> editStockById(@RequestBody StockPrice stock){
		String response = service.editStockPrice(stock);
		
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}

}
