package au.com.battery.rental.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.service.MachineService;

@RestController
public class InitialiseMachine {

	@Autowired
	private MachineService machineService;
	
	@RequestMapping(value="api/startmachine/numSlots/{numSlots}", method=RequestMethod.GET )
	public Machine initialiseMachine( @PathVariable Integer numSlots) {
		
		Machine machine = machineService.createNew(numSlots);
		//machine.setBatteries(null);
		return machine;
	}
}

