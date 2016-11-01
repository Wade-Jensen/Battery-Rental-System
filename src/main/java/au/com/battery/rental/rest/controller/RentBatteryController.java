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
import au.com.battery.rental.persistence.service.RentalLogService;
import au.com.battery.rental.rest.model.RentalResponse;

@RestController
public class RentBatteryController {
	
	@Autowired
	private BatteryUserService batteryUserService;
	
	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private MachineService machineService;
	
	@Autowired
	private RentalLogService rentalLogService;
	
	@RequestMapping("api/requestbattery/machineId/{machineId}/machineSlot/{machineSlot}/cardId/{cardId}/time/{time}")  // /
	public RentalResponse requestBattery(@PathVariable Integer machineId, @PathVariable String cardId, @PathVariable long time) { // , @PathVariable Integer machineSlot
		
		Timestamp timestamp = new Timestamp(time*1000);
		
		RentalResponse rentalResponse = new RentalResponse();
		// default result is false for release battery
		rentalResponse.setReleaseBattery(false);
		
		BatteryUser batteryUser = batteryUserService.findByCardId(cardId);
		if (batteryUser == null) {
			batteryUser = batteryUserService.createNew(cardId);
		}
		Boolean isPositive = ( batteryUser.getCredit() > 0 );
		
		// tell the response object whether the user has credit in their account
		rentalResponse.setIsUserBalancePositive( isPositive );
		rentalResponse.setBalance(batteryUser.getCredit());
		
		Machine machine = machineService.findById(machineId);
		if ( machine == null ){
			machineService.createNew(3);
		}
		ArrayList<Battery> batteries = new ArrayList<Battery>( machine.getBatteries() );
		
		Battery battery = null;
		
		if ( batteries.size() == 0) {
			throw new RuntimeException("Battery has no slot in database");
		}
		
		// iterate over batteries to and release the first that is charged
		for (int i = 0; i< batteries.size(); i++) {
			if ( batteries.get(i).getSoc() == 100 ) {
				battery = batteries.get(i);
				rentalResponse.setReleaseBattery(true);
				rentalResponse.setMachineSlot(i);
				battery.setAvailable(false);
				machine.getBatteries().set(i, null);
				batteryService.save(battery);
				machineService.save(machine);
				break;
			}
		}
		if (battery == null) {
			rentalResponse.setReleaseBattery(false);
		}
		
		if ( rentalResponse.getReleaseBattery() == true ) {
			RentalLog rentalLog = rentalLogService.createNew( batteryUser, battery, timestamp );
			rentalLog.setMachine(machine);
			rentalLogService.save(rentalLog);
		}
		if (battery != null) {
			System.out.println("Renting battery from machine " + machine.getId().toString() + " Location " + machine.getTextLocation() + " from slot " + battery.getSlot().toString());
		}
		return rentalResponse;
	}
	
}
