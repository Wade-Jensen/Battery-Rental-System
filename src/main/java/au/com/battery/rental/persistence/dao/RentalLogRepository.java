package au.com.battery.rental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import au.com.battery.rental.persistence.model.RentalLog;

public interface RentalLogRepository extends JpaRepository<RentalLog, Long>{
	
	RentalLog findById(Integer id);
	
	
	  //@Query("select u from RentalLog u where u.emailAddress = ?1")
	  //User findLast(String emailAddress);
}
