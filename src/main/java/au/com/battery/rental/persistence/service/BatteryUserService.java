package au.com.battery.rental.persistence.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.battery.rental.persistence.dao.BatteryUserRepository;
import au.com.battery.rental.persistence.model.BatteryUser;

@Service
public class BatteryUserService {
	
	@Autowired
	private BatteryUserRepository batteryUserRepo;
	
	public BatteryUser findByCardId(String cardId) {
		return batteryUserRepo.findByCardId(cardId);
	}
	
	public void save(BatteryUser batteryUser) {
		batteryUserRepo.save(batteryUser);
	}

	public Boolean balanceIsPositive(Integer batteryUserId) {
		
		Boolean isBalancePositive = false;
		
		BatteryUser batteryUser = batteryUserRepo.findById(batteryUserId);
		if ( batteryUser.getCredit() > 0 ) {
			isBalancePositive = true;
		}
		
		return isBalancePositive;
	}
	
	public BatteryUser createNew(String cardId) {
		
		BatteryUser batteryUser = new BatteryUser();
		batteryUser.setCardId(cardId);
		batteryUser.setCredit(50.00);
		
		Random generator = new Random();
		
		Integer random = generator.nextInt(100);
		
		batteryUser.setFirstName("User" + random.toString());
		batteryUserRepo.saveAndFlush(batteryUser);
		
		return batteryUser;
	}
	
}
