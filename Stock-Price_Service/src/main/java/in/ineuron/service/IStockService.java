package in.ineuron.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import in.ineuron.entity.StockFundamentals;
import in.ineuron.entity.StockPrice;

public interface IStockService {

	public Double findByCompany(String companyName);
	public String addCompanyStock(String companyName,Double companyPrice);
	public List<StockPrice> getListStocks();
	public StockPrice getStockById(Integer stockId);
	public StockFundamentals getStockFundamentals(Integer stockId);
	public String addStockFundamentals(StockFundamentals fundamentals,StockPrice stock);
	public String editStockPrice(StockPrice stock);
}
