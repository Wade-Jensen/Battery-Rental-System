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
	
	public Machine createNew(Integer numSlots) {
		
		Machine machine = new Machine();
		machine.setNumBatteries(numSlots);
		machine.setTextLocation("QUT Gardens Point");
		machine.setLat(-27.476994);
		machine.setLon(153.027715);
		machine.setModelType("Prototype");
		
		Timestamp currentTime = new Timestamp( new Date().getTime() );
		
		Battery battery0 = new Battery();
		battery0.setAvailable(true);
		battery0.setDatePurchased( currentTime );
		battery0.setLastUpdated( currentTime );
		battery0.setMachine(machine);
		battery0.setSlot(1);
		battery0.setSoc(100.00);
		
		Battery battery1 = new Battery();
		battery1.setAvailable(true);
		battery1.setDatePurchased( currentTime );
		battery1.setLastUpdated( currentTime );
		battery1.setMachine(machine);
		battery1.setSlot(2);
		battery1.setSoc(100.00);
		
		Battery battery2 = new Battery();
		battery2.setAvailable(true);
		battery2.setDatePurchased( currentTime );
		battery2.setLastUpdated( currentTime );
		battery2.setMachine(machine);
		battery2.setSlot(3);
		battery2.setSoc(100.00);
		
		List<Battery> batteries = new ArrayList<Battery>();
		batteries.add(battery0);
		batteries.add(battery1);
		batteries.add(battery2);
		
		machine.setBatteries(batteries);
		
		machineRepo.saveAndFlush(machine);
		
		machine.getBatteries().get(0).setMachine(null);
		machine.getBatteries().get(1).setMachine(null);
		machine.getBatteries().get(2).setMachine(null);
		
		return machine;
		
	}
	
}
