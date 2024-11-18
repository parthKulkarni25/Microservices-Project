package in.ineuron.service;

import in.ineuron.entity.StockPrice;

public interface IStockService {

	public Double findByCompany(String companyName);
	public String addCompanyStock(String companyName,Double companyPrice);
}
