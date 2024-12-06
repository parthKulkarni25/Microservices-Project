package in.ineuron.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ineuron.entity.StockPrice;
import in.ineuron.entity.StockFundamentalsDTO;
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
	
	@PostMapping("/addStock")
	public String addStock() {
		
		return "addStock";
	}
	
	@PostMapping("/addCompanyStock")
	public String addCompanyStock(@RequestParam String companyName,
			                      @RequestParam String stockPrice,
			                      @RequestParam String companyCEO,
			                      @RequestParam String marketCap,
			                      @RequestParam String peRatio,
			                      @RequestParam String pbRatio,
			                      @RequestParam List<Integer> revenueKeys,
			                      @RequestParam List<Double> revenueValues,
			                      @RequestParam List<Integer> profitKeys,
			                      @RequestParam List<Double> profitValues,
			                      @RequestParam List<Integer> netWorthKeys,
			                      @RequestParam List<Double> netWorthValues,
			                      Model model) {
		
		StockPrice stock = new StockPrice();
		stock.setCompanyName(companyName);
		stock.setCompanyPrice(Double.parseDouble(stockPrice));
		System.out.println(stock);
		
		Map<Integer, Double> revenue = new HashMap<>();
		for(int i=0;i<revenueKeys.size();i++) {
			revenue.put(revenueKeys.get(i),revenueValues.get(i));
		}
		
		Map<Integer, Double> profit = new HashMap<>();
		for (int i = 0; i <profitKeys.size(); i++) {
			profit.put(profitKeys.get(i), profitValues.get(i));
		}
		
		Map<Integer, Double> netWorth = new HashMap<>();
		for (int i = 0; i < netWorthKeys.size(); i++) {
			netWorth.put(netWorthKeys.get(i), netWorthValues.get(i));
		}
		
		StockFundamentalsDTO fundamentals = new StockFundamentalsDTO();
		fundamentals.setCompanyCEO(companyCEO);
		fundamentals.setMarketCap(Long.parseLong(marketCap));
		fundamentals.setPeRatio(Double.parseDouble(peRatio));
		fundamentals.setPbRatio(Double.parseDouble(pbRatio));
		fundamentals.setStockPrice(stock);
		fundamentals.setRevenue(revenue);
		fundamentals.setProfit(profit);
		fundamentals.setNetWorth(netWorth);
		
		System.out.println(fundamentals);
		String msg = service.addStockFundamentals(fundamentals,stock);
		model.addAttribute("msg", msg);
			
		return "index";
	}
	
	@PostMapping("/listStock")
	public String getAllStock(Model model) {
		List<StockPrice> listStocks = service.getCompanyStocks();
		System.out.println(listStocks);
		model.addAttribute("listStocks", listStocks);
		return "list-stock";
	}
	
	@GetMapping("/viewStockFundamentals")
	public String viewStockFundamentals(@RequestParam("stockId") Integer stockId,Model model) {
		StockFundamentalsDTO fundamentals = service.getStockFundamentals(stockId);
		System.out.println(fundamentals);
		model.addAttribute("fundamentals",fundamentals);
		return "view-stock";
	}
	
	@GetMapping("/editStockPrice")
	public String editStockPrice(@RequestParam("stockId") Integer stockId,Model model) {
		StockPrice stock = service.getCompanybyId(stockId);
		model.addAttribute("stock", stock);
		return "edit-stock";
	}
	
	@PostMapping("/editStockPrice")
	public String changeStockPrice(@ModelAttribute StockPrice stock,Model model) {
		String msg = service.changeStockPrice(stock);
		model.addAttribute("msg", msg);
		return "index";
	}
	
}
