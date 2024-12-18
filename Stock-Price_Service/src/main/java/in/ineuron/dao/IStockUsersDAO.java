package in.ineuron.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ineuron.entity.UserDetails;

public interface IStockUsersDAO extends JpaRepository<UserDetails, String> {
	
	boolean existsByUserId(Integer userId);

}
