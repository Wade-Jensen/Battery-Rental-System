package au.com.battery.rental.persistence.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.battery.rental.persistence.dao.MachineRepository;
import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.Machine;

@Service
public class MachineService {
	
	@Autowired
	private MachineRepository machineRepo;
	
	public Machine findById(Integer machineId) {
		return machineRepo.findById(machineId);
	}
	
	public Machine createNew(Integer numSlots, String textLocation) {
		
		Machine machine = new Machine();
		machine.setNumBatteries(numSlots);
		machine.setTextLocation(textLocation);
		
		machine.setModelType("Prototype");
		
		Timestamp currentTime = new Timestamp( new Date().getTime() );
		
		List<Battery> batteries = new ArrayList<Battery>();
		
		if( textLocation.compareTo("QUT GP") == 0) {
			
			machine.setLat(-27.476994);
			machine.setLon(153.027715);
			
			for (int i=0; i<numSlots; i++) {
				Battery battery = new Battery();
				battery.setAvailable(false);
				battery.setDatePurchased( currentTime );
				battery.setLastUpdated( currentTime );
				battery.setMachine(machine);
				battery.setSlot(i);
				battery.setSoc(0.00);
				
				batteries.add(battery);
			}
		}
		else if ( textLocation.compareTo("QUT KG") == 0 ) {
			 
			machine.setLat(-27.451660);
			machine.setLon(153.014774);
			
			for (int i=0; i<numSlots-1; i++) {
				Battery battery = new Battery();
				battery.setAvailable(false);
				battery.setDatePurchased( currentTime );
				battery.setLastUpdated( currentTime );
				battery.setMachine(machine);
				battery.setSlot(i);
				battery.setSoc(0.00);
				
				batteries.add(battery);
			}
		}
		else {
			for (int i=0; i<numSlots; i++) {
				Battery battery = new Battery();
				battery.setAvailable(false);
				battery.setDatePurchased( currentTime );
				battery.setLastUpdated( currentTime );
				battery.setMachine(machine);
				battery.setSlot(i);
				battery.setSoc(0.00);
				
				batteries.add(battery);
			}
		}
		
		machine.setBatteries(batteries);
		machineRepo.saveAndFlush(machine);
		
		// prevent recursive JSON object
		for (int i=0; i<numSlots; i++) {
			machine.getBatteries().get(i).setMachine(null);
		}
		
		return machine;
		
	}
	
	public Machine createNew(Integer numSlots) {
		Machine machine = new Machine();
		machine.setNumBatteries(numSlots);
		machine.setTextLocation("Unknown");
		machine.setLat(-27.476994);
		machine.setLon(153.027715);
		machine.setModelType("Prototype");
		
		Timestamp currentTime = new Timestamp( new Date().getTime() );
		
		List<Battery> batteries = new ArrayList<Battery>();
		
		for (int i=0; i<numSlots; i++) {
			Battery battery = new Battery();
			battery.setAvailable(false);
			battery.setDatePurchased( currentTime );
			battery.setLastUpdated( currentTime );
			battery.setMachine(machine);
			battery.setSlot(i);
			battery.setSoc(0.00);
			
			batteries.add(battery);
		}
		
		machine.setBatteries(batteries);
		machineRepo.saveAndFlush(machine);
		
		// prevent recursive JSON object
		for (int i=0; i<numSlots; i++) {
			machine.getBatteries().get(i).setMachine(null);
		}
		
		return machine;
	}

	public void save(Machine machine) {
		machineRepo.save(machine);
	}
	
}
