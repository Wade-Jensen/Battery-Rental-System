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

	@RequestMapping(value = "/api/fullcharge/machineId/{machineId}/time/{time}/machineSlot/{machineSlot}")
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

		// iterate over batteries to find the one in the correct slot
		for (int i = 0; i < batteries.size(); i++) {
			if (batteries.get(i).getSlot() == machineSlot) {
				battery = batteries.get(i);
				
				battery.setAvailable(true);
				battery.setSoc(100.00);
				
				batteryService.save(battery);
				
				response.setTimeSent(new Timestamp(time));
				response.setBattery(battery);
				response.setMachine(machine);
				response.setMachineSlot(i);
				response.setTimeReceived( new Timestamp(receivedDate.getTime()));
			}
		}

		if (battery == null) {
			throw new RuntimeException("No available batteries");
		}

		return response;
	}
}
