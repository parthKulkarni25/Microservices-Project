package in.ineuron.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.ineuron.entity.StockFundamentals;

public interface IStockFundamentalsDAO extends JpaRepository<StockFundamentals, Long> {
	
	@Query("SELECT sf FROM StockFundamentals sf WHERE sf.stockPrice.stockId = :stockId")
	Optional<StockFundamentals> findByCompanyStockId(@Param("stockId") Integer stockId);

}
