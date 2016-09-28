package au.com.battery.rental.persistence.dao;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import au.com.battery.rental.persistence.dao.BatteryRepository;
import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class BatteryRepositoryTest {
	
	// Member variable values for assertions
	private double soc = 20.45;
	private Timestamp timestamp = new Timestamp( (long) 1475020221*1000);
	private Timestamp timestamp2 = new Timestamp( (long) 1);
	private Integer batteryId;
	private Integer machineId;
	private String textLocation = "QUT";

	//Initialize Repo
	@Autowired
    private BatteryRepository batteryRepository;
	
	//Initialize Repo
	@Autowired
    private MachineRepository machineRepository;
	
	//Setup Before and after
	@Before
	public void setUp(){
		
		//Setup Machine
		Machine machine = new Machine();
		machine.setTextLocation(textLocation);
		
		// Setup test battery object
		Battery battery = new Battery();
		battery.setSoc(soc);
		battery.setLastUpdated(timestamp);
		battery.setMachine(machine);
		
		batteryRepository.saveAndFlush(battery);
		
		batteryId = battery.getId();
		machineId = machine.getId();
	}

	@After
	public void tearDown(){
		
		Battery battery = batteryRepository.findById(batteryId);
		if (battery != null) {
			batteryRepository.delete(battery);
		}
		
		Machine machine = machineRepository.findById(machineId);
		if (machine != null) {
			machineRepository.delete(machine);
		}
	}
    

	@Test
	public void testBatteryWithCrud() {
		
		Battery battery1 = batteryRepository.findById(batteryId);
		Machine machine1 = machineRepository.findById(machineId);
	
		//Read
		assertEquals(soc,battery1.getSoc(),1e-6);
		assertEquals(timestamp, battery1.getLastUpdated());
		assertEquals(textLocation, machine1.getTextLocation());
		
		//Update
		battery1.setLastUpdated(timestamp2);
		battery1.setSoc(soc*2);
		batteryRepository.saveAndFlush(battery1);
		
		//Test Update
		Battery readBattery = batteryRepository.findById(batteryId);
		assertEquals(soc*2,readBattery.getSoc(),1e-6);
		assertEquals(timestamp2, readBattery.getLastUpdated());
		
		//Delete
		batteryRepository.delete(readBattery);
		Machine readmachine = machineRepository.findById(machineId);
		
		//Test Delete
		readBattery = batteryRepository.findById(batteryId);
		assertEquals(null,readBattery);
		assertEquals(textLocation,readmachine.getTextLocation());
		
	}
}
