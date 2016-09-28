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
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class BatteryTest {
	
	// Member variable values for assertions
	private double soc = 20.45;
	private Timestamp timestamp = new Timestamp( (long) 1475020221*1000);
	private Double lat = -34.5666;
	private Double lon = 136.7904;
	
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void batteryTestGetSet() {
		
		// create preliminary fk object
		Machine machine = new Machine();
		machine.setLat(lat);
		machine.setLon(lon);
		
		// create the battery object and set member variables
		Battery battery = new Battery();
		battery.setSoc(soc);
		battery.setLastUpdated(timestamp);
		battery.setMachine(machine);
		
		// assert member variable fields are as expected
		assertEquals(soc, battery.getSoc(), 1e-6);
		assertEquals(timestamp, battery.getLastUpdated());
		assertEquals(lat, battery.getMachine().getLat(), 1e-6);
		assertEquals(lon, battery.getMachine().getLon(), 1e-6);
		
	}
	
}
