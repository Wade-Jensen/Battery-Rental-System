package au.com.battery.rental.persistence.model;

import static org.junit.Assert.*;

import java.sql.Timestamp;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.model.RentalLog;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class RentalLogTest {
	
	// Member variable values for assertions
	
	
	//Variables for FK - Machine
	private Double lat = -34.5666;
	private Double lon = 136.7904;
	
	//Variables for FK - Battery
	private double soc = 20.45;
	private Timestamp timestamp = new Timestamp( (long) 1475020221*1000);
	
	//Variables for FK - BatteryUser
	private String firstName = "Sam";
	private String lastName = "Samson";
	
	//Variable for Primary Table
	private Timestamp rentaltime = new Timestamp( (long)  1475020221*1000);
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void batteryTestGetSet() {
		
		// create preliminary fk object - Machine
		Machine machine = new Machine();
		machine.setLat(lat);
		machine.setLon(lon);
		
		// create preliminary fk object - Battery
		Battery battery = new Battery();
		battery.setSoc(soc);
		battery.setLastUpdated(timestamp);
		battery.setMachine(machine);
		
		//create preliminary FK object - Battery User
		BatteryUser batteryUser = new BatteryUser();
		batteryUser.setFirstName(firstName);
		batteryUser.setLastName(lastName);
	
		// create the Rental Log object and set member variables
		RentalLog rentallog = new RentalLog();
		rentallog.setBattery(battery);
		rentallog.setMachine(machine);
		rentallog.setBatteryUser(batteryUser);
		rentallog.setTimeRented(rentaltime);
		
		// assert member variable fields are as expected
		assertEquals(battery, rentallog.getBattery());
		assertEquals(batteryUser, rentallog.getBatteryUser());
		assertEquals(machine, rentallog.getMachine());
		assertEquals(rentaltime,rentallog.getTimeRented());
		
	}
	
}
