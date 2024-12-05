package in.ineuron.entity;


public class StockPrice {
	
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
		return "CompanyStock [stockId=" + stockId + ", companyName=" + companyName + ", companyPrice=" + companyPrice
				+ "]";
	}
	
	

}
