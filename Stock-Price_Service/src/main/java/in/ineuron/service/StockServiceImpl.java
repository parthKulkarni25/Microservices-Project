package in.ineuron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.dao.IStockDAO;
import in.ineuron.entity.StockPrice;
import in.ineuron.exception.StockNotFoundException;

@Service("service")
public class StockServiceImpl implements IStockService {
	
	@Autowired
	private IStockDAO dao;

	@Override
	public Double findByCompany(String companyName) {
		
		StockPrice stockPrice = dao.findByCompanyName(companyName);
		
		if (stockPrice==null) {
			throw new StockNotFoundException("Company Stock not Available..");
		}
		
		return stockPrice.getCompanyPrice();
	}

	@Override
	public String addCompanyStock(String companyName, Double companyPrice) {
		StockPrice stockPrice = new StockPrice();
		stockPrice.setCompanyName(companyName);
		stockPrice.setCompanyPrice(companyPrice);
		
		stockPrice =dao.save(stockPrice);
		if(stockPrice !=null)
			return "Stock Added Successfully";
		else
			return "Failed to Add stock.";
	}

}
