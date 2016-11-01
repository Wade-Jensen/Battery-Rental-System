package au.com.battery.rental.rest.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.battery.rental.persistence.dao.BatteryRepository;
import au.com.battery.rental.persistence.dao.BatteryUserRepository;
import au.com.battery.rental.persistence.dao.MachineRepository;
import au.com.battery.rental.persistence.dao.RentalLogRepository;
import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.model.RentalLog;
import au.com.battery.rental.persistence.service.BatteryUserService;
import au.com.battery.rental.rest.model.ReturnResponse;

@RestController
public class ReturnBatteryController {
	
	@Autowired 
	private BatteryUserRepository batteryUserRepo;
	
	@Autowired
	private RentalLogRepository rentalLogRepo;
	
	@Autowired
	private BatteryUserService batteryUserService;
	
	@Autowired
	private MachineRepository machineRepo;
	
	@Autowired
	private BatteryRepository batteryRepo;
	
	@RequestMapping("api/returnbattery/machineId/{machineId}/machineSlot/{machineSlot}/cardId/{cardId}/time/{time}")
	public ReturnResponse returnBattery(@PathVariable Integer machineId, @PathVariable Integer machineSlot, @PathVariable String cardId, @PathVariable long time) {
		
		//Date date = new Date();
		//time = date.getTime();
		
		BatteryUser user = batteryUserRepo.findByCardId(cardId);
				
		RentalLog rentalLog = rentalLogRepo.findUserLastRentalLog(user, new PageRequest(0,1, new Sort(new Order(Direction.DESC, "timeRented") ) )).get(0);
		rentalLog.setTimeReturned( new Timestamp( time*1000 ));
		rentalLog.setMachine(machineRepo.findById(machineId));
		rentalLogRepo.save(rentalLog);
		
		Double rentalRate = 0.05; // 5 cents per hour
		Double credit = user.getCredit();
		double hoursRented = ( (double) time - (double) rentalLog.getTimeRented().getTime()/1000)/3600;
		credit = credit - rentalRate * hoursRented;
		
		user.setCredit(credit);
		batteryUserService.save(user);
		
		Battery battery = rentalLog.getBattery();
		battery.setAvailable(false);
		battery.setMachine(machineRepo.findById(machineId));
		batteryRepo.saveAndFlush(battery);
		
		Machine machine = machineRepo.findById(machineId);
		machine.getBatteries().set(machineSlot, rentalLog.getBattery());
		
		ReturnResponse response = new ReturnResponse();
		response.setCredit(credit);
		response.setAllowReturn(true);
		
		System.out.println("Battery returned to machine " + machine.getId().toString() + " Location " + machine.getTextLocation() + " to slot " + rentalLog.getBattery().getSlot().toString());
		return response;
	}
}
