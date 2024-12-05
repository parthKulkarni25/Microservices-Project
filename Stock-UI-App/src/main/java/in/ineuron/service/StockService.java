package in.ineuron.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.ineuron.entity.StockPrice;
import in.ineuron.entity.StockFundamentalsDTO;

@Service
public class StockService {
	
	@Autowired
	private WebClient webClient;
	
	public String getTotalPrice(String companyName,Integer quantity) {
		String response = null;
		String REST_END_POINT = "http://localhost:1111/api/calc/calculate/{companyName}/{quantity}";
		try {
			response= webClient.get().uri(REST_END_POINT,companyName,quantity).
					retrieve().
					bodyToMono(String.class).
					block();
		} catch (Exception e) {
			response = "Company Not Found";
		}	
		return response;
	}
	
	public String addStock(String companyName,Double stockPrice) {
		String response = null;
		
		String REST_END_POINT = "http://localhost:2222/api/price/addStock/{companyName}/{stockPrice}";
		try {
			response = webClient.post().uri(REST_END_POINT,companyName,stockPrice)
					.retrieve()
					.bodyToMono(String.class)
					.block();
		} catch (Exception e) {
			e.printStackTrace();
			response = "Not Able to Add";
		}
		
		return response;
	}
	
	public List<StockPrice> getCompanyStocks(){
		String REST_END_POINT = "http://localhost:2222/api/price/listStock";
		try {
			return webClient.get().uri(REST_END_POINT)
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<List<StockPrice>>() {})
					.block();
					
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}
	
	public StockPrice getCompanybyId(Integer stockId) {
		String REST_END_POINT = "http://localhost:2222/api/price/getStockbyId/{stockId}";
		try {
			return webClient.get().uri(REST_END_POINT,stockId)
					.retrieve()
					.bodyToMono(StockPrice.class)
					.block();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public StockFundamentalsDTO getStockFundamentals(Integer stockId) {
		String REST_END_POINT = "http://localhost:2222/api/price/getFundamentals/{stockId}";
		try {
			return webClient.get().uri(REST_END_POINT,stockId)
					.retrieve()
					.bodyToMono(StockFundamentalsDTO.class)
					.block();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String addStockFundamentals(StockFundamentalsDTO fundamentals,StockPrice stock) {
		String REST_END_POINT = "http://localhost:2222/api/price/addFundamentals";
		try {
			return webClient.post().uri(REST_END_POINT)
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(fundamentals)
					.retrieve()
					.bodyToMono(String.class)
					.block();
		} catch (Exception e) {
			e.printStackTrace();
			return "Not able to add";
		}
	}
	
	
	public String changeStockPrice(StockPrice stock) {
		String REST_END_POINT = "http://localhost:2222/api/price/editStockPrice";
		try {
			return webClient.post().uri(REST_END_POINT)
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(stock)
					.retrieve()
					.bodyToMono(String.class)
					.block();
		} catch (Exception e) {
			e.printStackTrace();
			return "Unable to edit";
		}
	}
}
