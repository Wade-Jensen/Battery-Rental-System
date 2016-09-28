package au.com.battery.rental.persistence.model;

import static org.junit.Assert.*;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class BatteryUserTest {
	
	// Member variable values for assertions
	private Integer id = 7;
	private String cardId = "33fd54a3b";
	private String firstName = "Professional";
	private String lastName = "Programmer";
	private Double credit = 5.40;
	private Integer loginId = 5;
	
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void batteryUserTestGetSet() {
		
		
		// create the battery object and set member variables
		BatteryUser batteryUser = new BatteryUser();
		batteryUser.setId(id);
		batteryUser.setCardId(cardId);
		batteryUser.setFirstName(firstName);
		batteryUser.setLastName(lastName);
		batteryUser.setCredit(credit);
		batteryUser.setLoginId(loginId);
		
		
		// assert member variable fields are as expected
		assertEquals(id, batteryUser.getId());
		assertEquals(cardId, batteryUser.getCardId());
		assertEquals(firstName, batteryUser.getFirstName());
		assertEquals(lastName, batteryUser.getLastName());
		assertEquals(credit, batteryUser.getCredit(),1e-6);
		assertEquals(loginId, batteryUser.getLoginId());
		
	}
	
}
