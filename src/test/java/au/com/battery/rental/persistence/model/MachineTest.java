package au.com.battery.rental.persistence.model;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class MachineTest {
	
	// Member variable values for assertions
	private Integer id = 7;
	private Double lat = -27.477289;
	private Double lon = 153.027661;
	private String textLocation = "QUT";
	
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void MachineTestGetSet() {
		
		// create the Machine object and set member variables
		Machine machine = new Machine();
		machine.setId(id);
		machine.setLat(lat);
		machine.setLon(lon);
		machine.setTextLocation(textLocation);
		
		
		// assert member variable fields are as expected
		assertEquals(id, machine.getId());
		assertEquals(lat, machine.getLat(), 1e-6);
		assertEquals(lon, machine.getLon(), 1e-6);
		assertEquals(textLocation, machine.getTextLocation());

		
	}
	
}
