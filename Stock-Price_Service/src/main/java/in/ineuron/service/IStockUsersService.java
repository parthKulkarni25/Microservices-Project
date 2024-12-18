package in.ineuron.service;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import in.ineuron.entity.UserDetails;

public interface IStockUsersService {
	
	public String registerUser(UserDetails user);
	public Boolean authenticateUser(String userId,String password);
	

}
