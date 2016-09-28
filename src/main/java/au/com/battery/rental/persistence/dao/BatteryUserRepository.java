package au.com.battery.rental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import au.com.battery.rental.persistence.model.BatteryUser;

public interface BatteryUserRepository extends JpaRepository<BatteryUser, Long>{
	
	BatteryUser findById(Integer batteryUserId);
}
