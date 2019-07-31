package xml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.model.User;
import xml.model.UserStatus;
import xml.model.UserType;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	List<User> findByUserStatusAndUserType(UserStatus userStatus, UserType userType);
	
}
