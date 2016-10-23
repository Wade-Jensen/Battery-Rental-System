package au.com.battery.rental.persistence.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.persistence.model.RentalLog;

public interface RentalLogRepository extends JpaRepository<RentalLog, Long> {
	
	RentalLog findById(Integer id);
	
	@Query(
			"select log from RentalLog log"
				+ " where log.id = :id and"
				+ " log.finalCharge = :finalCharge"
				)
	RentalLog findSpecificRentalLog(@Param("id") Integer id, @Param("finalCharge") Double finalCharge);
	
	@Query(
			"select log from RentalLog log"
			+ " where log.batteryUser =:batteryUser"
			)
	List<RentalLog> findUserLastRentalLog(@Param("batteryUser") BatteryUser batteryUser, Pageable pageable );
}
