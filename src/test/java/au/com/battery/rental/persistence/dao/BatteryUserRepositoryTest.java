package au.com.battery.rental.persistence.dao;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.persistence.model.User;
import au.com.battery.rental.persistence.dao.BatteryUserRepository;
import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class BatteryUserRepositoryTest {
	
	@Autowired
	private BatteryUserRepository batteryUserUserRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private double credit2 = 22.09;
	private String cardId2 = "0x2352453";
	private Integer userId;

	
	private String textLocation = "QUT";
	private String username = "myTestUsername";
	
	//Initialize Repo
	@Autowired
	private UserRepository userRepository;

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
		
		//Setup User
		User user = new User();
		user.setUsername(username);
		
		userRepository.saveAndFlush(user);
		userId = user.getId();
		
		// Setup test batteryUser object
		BatteryUser batteryUser = new BatteryUser();
		batteryUser.setCredit(credit);
		batteryUser.setCardId(cardId);
		batteryUser.setUserId(userId);
		
		batteryUserRepository.saveAndFlush(batteryUser);
		
		batteryUserId = batteryUser.getId();
		userId = user.getId();
	}

	@After
	public void tearDown(){
		
		BatteryUser batteryUser = batteryUserRepository.findById(batteryUserId);
		if (batteryUser != null) {
			batteryUserRepository.delete(batteryUser);
		}
		
		User user = userRepository.findById(userId);
		if (user != null) {
			userRepository.delete(user);
		}

		if (batteryUser != null){
			batteryUserRepository.delete(batteryUser);
		}
	}
    

	@Test
	public void testBatteryUserWithCrud() {
		
		BatteryUser batteryUser = batteryUserRepository.findById(batteryUserId);
		User user = userRepository.findById(userId);
		
		//Read
		assertEquals(credit,batteryUser.getCredit(),1e-6);
		assertEquals(cardId, batteryUser.getCardId());
		assertEquals(user.getId(), batteryUser.getUserId());
		
		//Update
		batteryUser.setCardId(cardId2);
		batteryUser.setCredit(credit2);
		batteryUser.setFirstName(firstName);
		batteryUser.setLastName(lastName);
		batteryUserRepository.saveAndFlush(batteryUser);
		
		batteryUserId = batteryUser.getId();
		
		//Test Update
		BatteryUser readBatteryUser = batteryUserRepository.findById(batteryUserId);
		assertEquals(credit2,readBatteryUser.getCredit(),1e-6);
		assertEquals(cardId2, readBatteryUser.getCardId());
		
		//Test Find by CardID
		readBatteryUser = batteryUserRepository.findByCardId(cardId2);
		
		assertEquals(lastName,readBatteryUser.getLastName());
		assertEquals(credit2, readBatteryUser.getCredit(),1e-6);
		
		//Delete
		batteryUserRepository.delete(readBatteryUser);
		
		//Test Delete
		readBatteryUser = batteryUserRepository.findById(batteryUserId);
		assertEquals(null,readBatteryUser);
		User readuser = userRepository.findById(userId);
		assertEquals(userId,readuser.getId());
	
}



}
