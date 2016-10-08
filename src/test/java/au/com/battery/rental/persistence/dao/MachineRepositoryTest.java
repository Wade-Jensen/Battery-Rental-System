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

import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class MachineRepositoryTest {
		

	@Autowired
	RentalLogRepository rentalLogRepository;
	
	// Member variable values for assertions
	private double lat = -27.477356;
	private double lon = 153.0284231; 
	private String textLocation = "QUT";
	private double lat1 = 1;
	private double lon1 = 1; 
	private String textLocation1 = "Moon";
	
	//private Integer batteryId;
	//private Integer batteryUserId;
	//private String cardId;
	//private Integer rentalLogId;
	
	private Integer machineId;

	//Initialize Repo
	@Autowired
    private MachineRepository machineRepository;
	
	//Setup Before and after
	@Before
	public void setUp(){
		
		//Setup Machine
		Machine machine = new Machine();
		machine.setTextLocation(textLocation);
		machine.setLon(lon);
		machine.setLat(lat);
		
		machineRepository.saveAndFlush(machine);
		
		machineId = machine.getId();
	}

	@After
	public void tearDown(){
		
		Machine machine = machineRepository.findById(machineId);
		if (machine != null) {
			machineRepository.delete(machine);
		}
	}
    

	@Test
	public void testBatteryWithCrud() {
		

		//Battery battery = batteryRepository.findById(batteryId);
		//Machine machine = machineRepository.findById(machineId);
		//BatteryUser batteryUser = batteryUserRepository.findByCardId(cardId);
		//RentalLog rentalLog = rentalLogRepository.findById(rentalLogId);

		Machine readMachine1 = machineRepository.findById(machineId);

		//Read
		assertEquals(textLocation, readMachine1.getTextLocation());
		assertEquals(lon, readMachine1.getLon(),1e-6);
		assertEquals(lat,readMachine1.getLat(),1e-6);
		
		//Update
		
		readMachine1.setLat(lat1);
		readMachine1.setLon(lon1);
		readMachine1.setTextLocation(textLocation1);
		
		machineRepository.saveAndFlush(readMachine1);
			
		//Test Update
		readMachine1 = machineRepository.findById(machineId);
		assertEquals(lon1,readMachine1.getLon(),1e-6);
		assertEquals(lat1,readMachine1.getLat(),1e-6);
		assertEquals(textLocation1, readMachine1.getTextLocation());
		
		//Delete
		machineRepository.delete(readMachine1);;
		readMachine1 = machineRepository.findById(machineId);
		
		//Test Delete

		assertEquals(null,readMachine1);
		
	}
}
