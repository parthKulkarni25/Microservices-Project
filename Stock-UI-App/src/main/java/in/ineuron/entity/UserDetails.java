package in.ineuron.entity;

import java.util.List;

public class UserDetails {
	
	private String userId;
	
	private String userEmail;
	private String userName;
	private String mobileNo;
	private String address;
	private String password;
	private List<StockPrice> stocks;
	
	
	public List<StockPrice> getStocks() {
		return stocks;
	}
	public void setStocks(List<StockPrice> stocks) {
		this.stocks = stocks;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userEmail=" + userEmail + ", userName=" + userName + ", mobileNo="
				+ mobileNo + ", address=" + address + ", password=" + password + ", stocks=" + stocks + "]";
	}
	
}
