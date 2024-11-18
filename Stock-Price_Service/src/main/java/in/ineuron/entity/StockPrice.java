package in.ineuron.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class StockPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stockId;
	private String companyName;
	private Double companyPrice;
	public Integer getStockId() {
		return stockId;
	}
	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Double getCompanyPrice() {
		return companyPrice;
	}
	public void setCompanyPrice(Double companyPrice) {
		this.companyPrice = companyPrice;
	}
	@Override
	public String toString() {
		return "StockPrice [stockId=" + stockId + ", companyName=" + companyName + ", companyPrice=" + companyPrice
				+ "]";
	}
	
	
}
