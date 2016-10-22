package au.com.battery.rental.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.battery.rental.persistence.dao.BatteryHistoryRepository;
import au.com.battery.rental.persistence.model.BatteryHistory;

@Service
public class BatteryHistoryService {
	
	@Autowired
	private BatteryHistoryRepository batteryHistoryRepo;
	
	public void save(BatteryHistory batteryHistory) {
		batteryHistoryRepo.save(batteryHistory);
	}
}
