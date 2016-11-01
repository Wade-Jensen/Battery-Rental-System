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
	
	@RequestMapping(value="api/startmachine/numSlots/{numSlots}/textlocation/{textLocation}", method=RequestMethod.GET )
	public Machine initialiseMachine( @PathVariable Integer numSlots, @PathVariable String textLocation) {
		
		Machine machine = new Machine();
		if ( machineService.findByTextLocation(textLocation).size() != 0) {
			machine = machineService.findByTextLocation(textLocation).get(0);
		}
		else {
			machine = machineService.createNew(numSlots, textLocation);
		}
		try {
			for (int i = 0; i<machine.getBatteries().size(); i++) {
				machine.getBatteries().get(i).setMachine(null);
			}
		} catch (Exception e) {
			System.out.println("Registering machine " + machine.getId().toString() + " Location " + machine.getTextLocation());
			return machine;
		}
		
		//machine.setBatteries(null);
		
		System.out.println("Registering machine " + machine.getId().toString() + " Location " + machine.getTextLocation());
		return machine;
	}
}

