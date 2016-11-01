package au.com.battery.rental.rest.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.BatteryHistory;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.service.BatteryService;
import au.com.battery.rental.persistence.service.MachineService;

@RestController
public class FullChargeController {

	@Autowired
	private BatteryService batteryService;

	@Autowired
	private MachineService machineService;

	@RequestMapping(value = "api/fullcharge/machineId/{machineId}/time/{time}/machineSlot/{machineSlot}")
	public BatteryHistory fullCharge(@PathVariable Integer machineId, @PathVariable long time,
			@PathVariable Integer machineSlot) {

		Date receivedDate = new Date();

		BatteryHistory response = new BatteryHistory();

		Machine machine = machineService.findById(machineId);
		ArrayList<Battery> batteries = new ArrayList<Battery>(machine.getBatteries());
		Battery battery = null;

		if (batteries.size() == 0) {
			throw new RuntimeException("Battery has no slot in database");
		}

		response.setMachine(machine);
		
		battery = batteries.get(machineSlot);
		
		battery.setAvailable(true);
		battery.setSoc(100.00);
		battery.setLastUpdated(new Timestamp(time*1000));
		battery.setMachine( machine );
		
		batteryService.save(battery);
		
		response.setTimeSent(new Timestamp(time));
		response.setBattery(battery);
		
		response.setMachineSlot(machineSlot);
		response.setTimeReceived( new Timestamp(receivedDate.getTime()));
		// if we were going to calculate SOC we might do it here
		
		// prevent recursive machines in response JSON object
		response.getMachine().setBatteries(null);
		response.getBattery().setMachine(null);
		
		if (battery == null) {
			throw new RuntimeException("No available batteries");
		}
		System.out.println("Battery fully charged at machine " + machine.getId().toString() + " Location " + machine.getTextLocation() + " slot " + battery.getSlot().toString());
		return response;
	}
}
