package in.ineuron.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ineuron.entity.StockPrice;
import java.util.List;


public interface IStockDAO extends JpaRepository<StockPrice, Integer> {

	public StockPrice findByCompanyName(String companyName);
}
