package in.ineuron.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.ineuron.config.IdGenerator;
import in.ineuron.dao.IStockUsersDAO;
import in.ineuron.entity.UserDetails;

@Service
public class StockUsersImpl implements IStockUsersService {
	
	@Autowired
	private IStockUsersDAO userDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public String registerUser(UserDetails user) {
		System.out.println(user + "from user controller");
		
		String uniqueUserId = generateUniqueUserId();
		user.setUserId(uniqueUserId);
		user.setPassword(encoder.encode(user.getPassword()));
		UserDetails userDetails = userDao.save(user);
		System.out.println(userDetails + "UserDetails after DB");
		if (userDetails !=null) {
			return "User Registered Successfully with User Id " +user.getUserId();
		} else {
			return "Not able to add User";
		}
			
	}
	
	private String generateUniqueUserId() {
		
		String userId;
		do {
			userId = IdGenerator.generateUserid();
		} while (userDao.existsById(userId));
		return userId;
	}

	@Override
	public Boolean authenticateUser(String userId, String password) {
		Optional<UserDetails> user = userDao.findById(userId);
		System.out.println("user from service layer "+ user.get());
		if (user.isPresent() && encoder.matches(password, user.get().getPassword())) {
			return true;
		} else {
			return false;
		}
		
	}

}
