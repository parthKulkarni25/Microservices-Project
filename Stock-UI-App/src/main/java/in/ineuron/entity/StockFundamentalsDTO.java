package in.ineuron.entity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockFundamentalsDTO {
	
	private Integer cId;
	private String companyCEO;
	private Long marketCap;
	private Double peRatio;
	private Double pbRatio;
	
	@JsonProperty("stock")
	private StockPrice stockPrice;
	
	private Map<Integer, Double> revenue;
	private Map<Integer, Double> profit;
	private Map<Integer, Double> netWorth;
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	public Long getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(Long marketCap) {
		this.marketCap = marketCap;
	}
	public Double getPeRatio() {
		return peRatio;
	}
	public void setPeRatio(Double peRatio) {
		this.peRatio = peRatio;
	}
	public Double getPbRatio() {
		return pbRatio;
	}
	public void setPbRatio(Double pbRatio) {
		this.pbRatio = pbRatio;
	}
	public StockPrice getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(StockPrice stockPrice) {
		this.stockPrice = stockPrice;
	}
	public Map<Integer, Double> getRevenue() {
		return revenue;
	}
	public void setRevenue(Map<Integer, Double> revenue) {
		this.revenue = revenue;
	}
	public Map<Integer, Double> getProfit() {
		return profit;
	}
	public void setProfit(Map<Integer, Double> profit) {
		this.profit = profit;
	}
	public Map<Integer, Double> getNetWorth() {
		return netWorth;
	}
	public void setNetWorth(Map<Integer, Double> netWorth) {
		this.netWorth = netWorth;
	}
	@Override
	public String toString() {
		return "StockFundamentalsDTO [cId=" + cId + ", companyCEO=" + companyCEO + ", marketCap=" + marketCap
				+ ", peRatio=" + peRatio + ", pbRatio=" + pbRatio + ", stockPrice=" + stockPrice + ", revenue="
				+ revenue + ", profit=" + profit + ", netWorth=" + netWorth + "]";
	}
	
	

}
