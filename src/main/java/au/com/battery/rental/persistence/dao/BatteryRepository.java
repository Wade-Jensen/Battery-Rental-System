package au.com.battery.rental.persistence.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.BatteryHistory;

public interface BatteryRepository extends JpaRepository<Battery, Long>{
		
	Battery findById(Integer id);
	
	ArrayList<Battery> findByMachineId(Integer machineId);
	
	@Override
	void delete(Battery battery);
}
