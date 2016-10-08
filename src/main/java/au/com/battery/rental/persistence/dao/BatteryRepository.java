package au.com.battery.rental.persistence.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import au.com.battery.rental.persistence.model.Battery;

public interface BatteryRepository extends JpaRepository<Battery, Long>{
		
	Battery findById(Integer id);
	
	ArrayList<Battery> findByMachineId(Integer machineId);
}
