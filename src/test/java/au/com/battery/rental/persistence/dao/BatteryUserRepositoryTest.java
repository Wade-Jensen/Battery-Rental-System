package au.com.battery.rental.persistence.dao;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import au.com.battery.rental.persistence.dao.BatteryUserRepository;
import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class BatteryUserRepositoryTest {
	
	// Member variable values for assertions
	private String firstName = "David";
	private String lastName = "Lindhagen";
	private String cardId = "XXXX";
	private double credit = 4.3;
	private double credit1 = 1.2;
	
	private Integer batteryUserId;

	//Initialize Repo
	@Autowired
    private BatteryUserRepository batteryUserRepository;
	
	//Setup Before and after
	@Before
	public void setUp(){
		
		//Setup Battery User
		BatteryUser batteryUser = new BatteryUser();
		batteryUser.setCardId(cardId);
		batteryUser.setFirstName(firstName);
		batteryUser.setLastName(lastName);
		batteryUser.setCredit(credit);
		
		batteryUserRepository.saveAndFlush(batteryUser);
		
		batteryUserId = batteryUser.getId();
		
	}

	@After
	public void tearDown(){
		
		BatteryUser batteryUser = batteryUserRepository.findById(batteryUserId);
		if (batteryUser != null){
			batteryUserRepository.delete(batteryUser);
		}
	}
    

	@Test
	public void testBatteryUserWithCrud() {
		
		
		BatteryUser readBatteryUser = batteryUserRepository.findById(batteryUserId);
		
		//Read
		assertEquals(cardId,readBatteryUser.getCardId());
		assertEquals(firstName, readBatteryUser.getFirstName());
		assertEquals(lastName,readBatteryUser.getLastName());
		assertEquals(credit, readBatteryUser.getCredit(),1e-6);

		//Update
		readBatteryUser.setCredit(credit1);
		batteryUserRepository.saveAndFlush(readBatteryUser);
		
		//Test Update
		readBatteryUser = batteryUserRepository.findById(batteryUserId);
		
		assertEquals(lastName,readBatteryUser.getLastName());
		assertEquals(credit1, readBatteryUser.getCredit(),1e-6);
		
		//Test Find by CardID
		readBatteryUser = batteryUserRepository.findByCardId(cardId);
		
		assertEquals(lastName,readBatteryUser.getLastName());
		assertEquals(credit1, readBatteryUser.getCredit(),1e-6);
		
		//Delete
		batteryUserRepository.delete(readBatteryUser);
		
		//Test Delete
		readBatteryUser = batteryUserRepository.findById(batteryUserId);
		assertEquals(null,readBatteryUser);
		
	}
}
