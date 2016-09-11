package au.com.battery.rental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import au.com.battery.rental.persistence.model.Machine;

public interface MachineRepository extends JpaRepository<Machine, Long>{
	
	Machine findById(Integer id);
}
