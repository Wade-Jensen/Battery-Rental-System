package au.com.battery.rental.rest.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.battery.rental.persistence.model.Heartbeat;
import au.com.battery.rental.persistence.service.HeartbeatService;

@RestController
public class HeartbeatController {
	
	@Autowired
	private HeartbeatService heartbeatService;
	
	@RequestMapping("/api/requestbattery/machineId/{machineId}/time/{time}/rngId/{rngId}/chargeCurrent0/{chargeCurrent0}/chargeCurrent1/{chargeCurrent1}/chargeCurrent2/{chargeCurrent2}/chargeCurrent3/{chargeCurrent3}")
	public Heartbeat heartbeat( @PathVariable Integer machineId, @PathVariable long time, @PathVariable Integer rngId, @PathVariable Double chargeCurrent0, @PathVariable Double chargeCurrent1, @PathVariable Double chargeCurrent2, @PathVariable Double chargeCurrent3 ) {
		
		Heartbeat heartbeat = new Heartbeat();
		heartbeat.setRngId(rngId);
		heartbeat.setMachineId(machineId);
		heartbeat.setTimeReceived(time);
		heartbeat.setTimeSent( new Date().getTime() );
		
		heartbeatService.save(heartbeat);
		
		return heartbeat;
	}
	
	
}
