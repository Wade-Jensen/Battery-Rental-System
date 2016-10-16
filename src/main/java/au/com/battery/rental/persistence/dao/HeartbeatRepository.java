package au.com.battery.rental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.persistence.model.Heartbeat;

@Repository
public interface HeartbeatRepository extends JpaRepository<Heartbeat, Long> {
	
	Heartbeat findById(Integer id);

	@Override
	void delete(Heartbeat heartbeat);
}

