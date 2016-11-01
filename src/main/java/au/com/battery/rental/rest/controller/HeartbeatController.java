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
import au.com.battery.rental.persistence.model.Heartbeat;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.service.BatteryHistoryService;
import au.com.battery.rental.persistence.service.BatteryService;
import au.com.battery.rental.persistence.service.HeartbeatService;
import au.com.battery.rental.persistence.service.MachineService;

@RestController
public class HeartbeatController {
	
	@Autowired
	private HeartbeatService heartbeatService;
	
	@Autowired
	private MachineService machineService;
	
	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private BatteryHistoryService batteryHistoryService;
	
	@RequestMapping("/api/requestbattery/machineId/{machineId}/time/{time}/rngId/{rngId}/chargeCurrent0/{chargeCurrent0}/chargeCurrent1/{chargeCurrent1}/chargeCurrent2/{chargeCurrent2}/chargeCurrent3/{chargeCurrent3}")
	public Heartbeat heartbeat( @PathVariable Integer machineId, @PathVariable long time, @PathVariable Integer rngId, @PathVariable Double chargeCurrent0, @PathVariable Double chargeCurrent1, @PathVariable Double chargeCurrent2, @PathVariable Double chargeCurrent3 ) {
		
		Machine machine = machineService.findById(machineId);
		
		if ( machine == null ) {
			machine = machineService.createNew(3);
		}
		
		long timeReceived = new Date().getTime();
		
		Heartbeat heartbeat = new Heartbeat();
		heartbeat.setRngId(rngId);
		heartbeat.setMachineId(machineId);
		heartbeat.setTimeReceived(time);
		heartbeat.setTimeSent( timeReceived );
		
		heartbeatService.save(heartbeat);
		
		
		ArrayList<Double> chargeCurrentArray = new ArrayList<Double>(4);
		chargeCurrentArray.add(0.00);
		chargeCurrentArray.add(0.00);
		chargeCurrentArray.add(0.00);
		chargeCurrentArray.add(0.00);
		
		chargeCurrentArray.set(0, chargeCurrent0);
		chargeCurrentArray.set(1, chargeCurrent1);
		chargeCurrentArray.set(2, chargeCurrent2);
		chargeCurrentArray.set(3, chargeCurrent3);
		
		for (int i = 0; i < machine.getNumBatteries(); i++ ) {
			
			BatteryHistory batteryHistory = new BatteryHistory();
			Integer slotNumber = i;
			
			// if a battery was missing for some reason, add it in
			Battery newBattery = null;
			try {
				newBattery =  machine.getBatteries().get(i);
				newBattery.setMachine(machine);
			}
			catch (Exception e) {
				
				continue;
				//newBattery = batteryService.createNew(machineId, slotNumber);
				
				//if (machine.getBatteries().get(slotNumber) == null) {
				//	machine.getBatteries().set(slotNumber, new Battery());
				//}
				//machine.getBatteries().get(slotNumber);
				//machineService.save(machine);
			}
			
			batteryHistory.setBattery( newBattery );
			batteryHistory.setMachine(machine);
			batteryHistory.setMachineSlot(slotNumber);
			batteryHistory.setTimeReceived( new Timestamp(timeReceived) );
			batteryHistory.setTimeSent( new Timestamp(time*1000));
			batteryHistory.setChargeCurrent(chargeCurrentArray.get(i));
			
			batteryHistoryService.save(batteryHistory);
		}
		
		return heartbeat;
	}
}
