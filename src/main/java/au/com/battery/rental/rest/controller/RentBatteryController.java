package au.com.battery.rental.rest.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.model.RentalLog;
import au.com.battery.rental.persistence.service.BatteryService;
import au.com.battery.rental.persistence.service.BatteryUserService;
import au.com.battery.rental.persistence.service.MachineService;
import au.com.battery.rental.rest.model.RentalResponse;

@RestController
public class RentBatteryController {
	
	@Autowired
	private BatteryUserService batteryUserService;
	
	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private MachineService machineService;
	
	@RequestMapping("/requestbattery/machineId/{machineId}/cardId/{cardId}/machineSlot/{machineSlot}/timestamp/{timestamp}")
	public RentalResponse requestBattery(@PathVariable Integer machineId, @PathVariable String cardId, @PathVariable Integer machineSlot, @PathVariable long timestampMilli) {
		
		Timestamp timestamp = new Timestamp(timestampMilli);
		
		RentalResponse rentalResponse = new RentalResponse();
		
		BatteryUser batteryUser = batteryUserService.findByCardId(cardId);
		Boolean isPositive = ( batteryUser.getCredit() > 0 );
		
		// tell the response object whether the user has credit in their account
		rentalResponse.setIsUserBalancePositive( isPositive );
		
		Machine machine = machineService.findById(machineId);
		ArrayList<Battery> batteries = new ArrayList<Battery>( machine.getBatteries() );
		
		Battery battery = null;
		for (int i = 0; i< batteries.size(); i++) {
			if ( batteries.get(i).getSlot() == machineSlot ) {
				battery = batteries.get(i);
			}
		}
		if (battery == null) {
			throw new RuntimeException("Battery has no slot in database");
		}
		
		//if ( battery.getSoc() )
		
		//if (Rental)
			
		
		
		//RentalLog rentalLog = new RentalLog();
		//rentalLog.setBattery(battery);
		//rentalLog.setBatteryUser(batteryUser);
		//rentalLog.setInitialCharge(battery.getSoc());
		//rentalLog.setMachine(null);
		//rentalLog.setTimeRented(timestamp);
		//rentalLog.setTimeReturned( new Timestamp( new Date() ));
		//rentalLog.setFinalCharge(finalCharge);
		
		return new RentalResponse();
	}
	
}
