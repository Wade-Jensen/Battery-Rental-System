package au.com.battery.rental.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.battery.rental.persistence.dao.HeartbeatRepository;
import au.com.battery.rental.persistence.model.Heartbeat;

@Service
public class HeartbeatService {
	
	@Autowired
	private HeartbeatRepository heartbeatRepo;
	
	public void save(Heartbeat heartbeat) {
		heartbeatRepo.saveAndFlush(heartbeat);
	}
	
}
