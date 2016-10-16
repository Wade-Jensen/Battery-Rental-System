package au.com.battery.rental.rest.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.service.BatteryService;
import au.com.battery.rental.persistence.service.MachineService;
import au.com.battery.rental.rest.model.FullChargeResponse;

public class FullChargeController {
	
	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private MachineService machineService;
	
	@RequestMapping(value="/api/fullcharge/machineId/{machineId}/time/{time}/machineSlot/{machineSlot}")
	public FullChargeResponse fullCharge( @PathVariable Integer machineId, @PathVariable long time, @PathVariable Integer machineSlot) {
	
		Date receivedDate = new Date();
		
		FullChargeResponse response = new FullChargeResponse();
		
		Machine machine = machineService.findById(machineId);
		ArrayList<Battery> batteries = new ArrayList<Battery> ( machine.getBatteries() );
		Battery battery = null;
		
		if ( batteries.size() == 0) {
			throw new RuntimeException("Battery has no slot in database");
		}
		
		// iterate over batteries to find the one in the correct slot
		for (int i = 0; i< batteries.size(); i++) {
			if ( batteries.get(i).getSlot() == machineSlot ) {
				battery = batteries.get(i);
				
				response.setTimeSent(time);
				response.setBatteryId( battery.getId() );
				response.setMachineId( machine.getId() );
				response.setMachineSlot(i);
				response.setTimeReceived(receivedDate.getTime());
			}
		}
		
		if (battery == null) {
			throw new RuntimeException("No available batteries");
		}
		
		return new FullChargeResponse();
	}
}
