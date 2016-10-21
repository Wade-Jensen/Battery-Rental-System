package au.com.battery.rental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import au.com.battery.rental.persistence.model.BatteryHistory;

public interface BatteryHistoryRepository extends JpaRepository<BatteryHistory, Long> {
	
	BatteryHistory findById(Integer batteryHistoryId);
	
	@Override
	void delete(BatteryHistory batteryHistory);
	
}
