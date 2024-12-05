package in.ineuron.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.dao.IStockDAO;
import in.ineuron.dao.IStockFundamentalsDAO;
import in.ineuron.entity.StockFundamentals;
import in.ineuron.entity.StockPrice;
import in.ineuron.exception.StockNotFoundException;

@Service("service")
public class StockServiceImpl implements IStockService {
	
	@Autowired
	private IStockDAO dao;
	
	@Autowired
	private IStockFundamentalsDAO fundamentalsDao;

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

	@Override
	public List<StockPrice> getListStocks() {
		
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public StockPrice getStockById(Integer stockId) {
		Optional<StockPrice> optional = dao.findById(stockId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
		
	}

	@Override
	public StockFundamentals getStockFundamentals(Integer stockId) {
		Optional<StockFundamentals> optional = fundamentalsDao.findByCompanyStockId(stockId);
		if(optional.isPresent()){
			return optional.get();
		}else
		  throw new StockNotFoundException("Stock is not found.");
	}

	@Override
	public String addStockFundamentals(StockFundamentals fundamentals,StockPrice stock) {
		StockPrice saveStockPrice = dao.save(stock);
		fundamentals.setStock(saveStockPrice);
		StockFundamentals stockFundamentals = fundamentalsDao.save(fundamentals);
		if(stockFundamentals != null && saveStockPrice !=null)
			return "Stock Added Successfully";
		else
			return "Failed to Add stock.";
	}

	@Override
	public String editStockPrice(StockPrice stock) {
		StockPrice stock1 = dao.save(stock);
		if(stock1 != null)
			return "stock edited..";
		else
			return "not able to edit";
	}

}
