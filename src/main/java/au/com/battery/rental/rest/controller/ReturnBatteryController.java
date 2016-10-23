package au.com.battery.rental.rest.controller;

import java.util.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.battery.rental.persistence.dao.BatteryUserRepository;
import au.com.battery.rental.persistence.dao.RentalLogRepository;
import au.com.battery.rental.persistence.model.BatteryUser;
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
	
	@RequestMapping("api/returnbattery/machineId/{machineId}/machineSlot/{machineSlot}/cardId/{cardId}/time/{time}")
	public ReturnResponse returnBattery(@PathVariable Integer machineId, @PathVariable Integer machineSlot, @PathVariable String cardId, @PathVariable long time) {
		
		Date date = new Date();
		time = date.getTime();
		
		BatteryUser user = batteryUserRepo.findByCardId(cardId);
				
		RentalLog rentalLog = rentalLogRepo.findUserLastRentalLog(user, new PageRequest(0,1, new Sort(new Order(Direction.DESC, "timeRented") ) )).get(0);

		//user.getCredit();
		Double rentalRate = 0.05/1000*60*60; // 5 cents per hour
		Double credit = user.getCredit();
		credit = credit - rentalRate * ( time - rentalLog.getTimeRented().getTime());
		
		user.setCredit(credit);
		
		batteryUserService.save(user);
		
		ReturnResponse response = new ReturnResponse();
		response.setCredit(credit);
		response.setAllowReturn(true);
		
		return response;
	}
}
