package au.com.battery.rental.persistence.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.battery.rental.persistence.dao.RentalLogRepository;
import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.persistence.model.RentalLog;

@Service
public class RentalLogService {
	
	@Autowired
	private RentalLogRepository rentalLogRepo;
	
	public RentalLog createNew( BatteryUser batteryUser, Battery battery, Timestamp timestamp) {
		
		RentalLog rentalLog = new RentalLog();
		rentalLog.setBattery(battery);
		rentalLog.setBatteryUser(batteryUser);
		rentalLog.setInitialCharge(100.00);
		rentalLog.setMachine(null);
		rentalLog.setTimeRented(timestamp);
		rentalLog.setTimeReturned( timestamp );
		rentalLog.setFinalCharge(null);
		
		return rentalLog;
	}
	
	
}
