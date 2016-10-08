package au.com.battery.rental.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;

import au.com.battery.rental.persistence.dao.BatteryUserRepository;
import au.com.battery.rental.persistence.model.BatteryUser;

public class BatteryUserService {
	
	@Autowired
	private BatteryUserRepository batteryUserRepo;
	
	public BatteryUser findByCardId(Integer cardId) {
		return batteryUserRepo.findByCardId(cardId);
	}
	
	
	public Boolean balanceIsPositive(Integer batteryUserId) {
		
		Boolean isBalancePositive = false;
		
		BatteryUser batteryUser = batteryUserRepo.findById(batteryUserId);
		if ( batteryUser.getCredit() > 0 ) {
			isBalancePositive = true;
		}
		
		return isBalancePositive;
	}
	
}
