package au.com.battery.rental.persistence.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.battery.rental.persistence.dao.BatteryRepository;
import au.com.battery.rental.persistence.dao.MachineRepository;
import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.Machine;

@Service
public class BatteryService {
	
	@Autowired
	private BatteryRepository batteryRepo;
	
	@Autowired
	private MachineRepository machineRepo;
	
	private ArrayList<Battery> findByMachineId(Integer machineId) {
		return batteryRepo.findByMachineId(machineId);
	}
	
	public Battery findById(Integer batteryId) {
		return batteryRepo.findById(batteryId);
	}
	
	public void save(Battery battery) {
		batteryRepo.save(battery);
	}
	
	public Battery createNew(Integer machineId, Integer slotNumber) {
		
		Timestamp now = new Timestamp ( new Date().getTime() );
		
		Battery battery = new Battery();
		battery.setSlot(slotNumber);
		battery.setAvailable(false);
		battery.setDatePurchased( now );
		battery.setLastUpdated( now );
		battery.setSoc(0.00);
		
		Machine machine = machineRepo.findById(machineId);
		
		battery.setMachine( machine );
		
		batteryRepo.save(battery);
		
		return battery;
	}
}
