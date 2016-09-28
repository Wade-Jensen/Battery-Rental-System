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

import au.com.battery.rental.persistence.dao.MachineRepository;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class RentalLogRepositoryTest {
	
	// Member variable values for assertions
	private Integer machineId;
	private String textLocation = "QUT";
	private Double lat = -27.477356;
	private Double lon = 153.0284231;
	private String textLocation2 = "UBC";
	private Double lat2 = 49.267001;
	private Double lon2 = -123.2548559;

	
	//Initialize Repo
	@Autowired
    private MachineRepository machineRepository;
	
	//Setup Before and after
	@Before
	public void setUp(){
		
		//Setup Machine
		Machine machine = new Machine();
		machine.setTextLocation(textLocation);
		machine.setLat(lat);
		machine.setLon(lon);
		
		machineRepository.saveAndFlush(machine);
		
		machineId = machine.getId();
	}

	@After
	public void tearDown(){
		Machine machine = machineRepository.findById(machineId);
		if (machine != null){
			machineRepository.delete(machine);
		}
		
	}
    

	@Test
	public void testMachineWithCrud() {
		
		Machine machine1 = machineRepository.findById(machineId);
	
		//Read
		assertEquals(lat,machine1.getLat(),1e-6);
		assertEquals(lon,machine1.getLon(),1e-6);
		assertEquals(textLocation, machine1.getTextLocation());
		
		//Update
		machine1.setTextLocation(textLocation2);
		machine1.setLat(lat2);
		machine1.setLon(lon2);
		
		machineRepository.saveAndFlush(machine1);
		
		//Test Update
		
		Machine readMachine = machineRepository.findById(machineId);
		
		assertEquals(lat2,readMachine.getLat(),1e-6);
		assertEquals(lon2,readMachine.getLon(),1e-6);
		assertEquals(textLocation2, readMachine.getTextLocation());
		
		//Delete
		machineRepository.delete(readMachine);
		
		//Test Delete
		Machine readMachine2 = machineRepository.findById(machineId);
		assertEquals(null,readMachine2);
		
	}

}
