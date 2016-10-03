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

import au.com.battery.rental.persistence.dao.RentalLogRepository;
import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.persistence.model.RentalLog;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class RentalLogRepositoryTest {
	
	// Member variable values for assertions
			//MachineRepository
			private String textLocation = "QUT";
			private Integer machineId;
	
			//BatteryRepository
			private double soc = 20.45;
			private Integer batteryId;
			
			//BatteryUserRepository
			private String cardId = "XXXX";
			private Integer batteryUserId;
			
			//RentalLogRepository
			private Timestamp timeRented = new Timestamp( (long) 1475020221*1000);;
			private Timestamp timeRented2 = new Timestamp( (long) 68685*1000);;
			private Integer rentalLogId;
			

	//Initialize Repo
			//MachineRepository
			@Autowired
		    private MachineRepository machineRepository;
	
			//BatteryRepository
			@Autowired
		    private BatteryRepository batteryRepository;
			
			//BatteryUserRepository
			@Autowired
			private BatteryUserRepository batteryUserRepository;
			
			//RentalLogRepository
			@Autowired
			private RentalLogRepository rentalLogRepository;
			
	
	//Setup Before and after
	@Before
	public void setUp(){
		
		//Setup Objects
			//Setup Machine
			Machine machine = new Machine();
			machine.setTextLocation(textLocation);
			//setup Battery
			Battery battery = new Battery();
			battery.setSoc(soc);
			//setup BatteryUser
			BatteryUser batteryUser = new BatteryUser();
			batteryUser.setCardId(cardId);
			//Setup RentalLog
			RentalLog rentalLog = new RentalLog();
			rentalLog.setTimeRented(timeRented);
			rentalLog.setBattery(battery);
			rentalLog.setMachine(machine);
			rentalLog.setBatteryUser(batteryUser);
			
		rentalLogRepository.saveAndFlush(rentalLog);
		
		//record object Id
		batteryUserId = batteryUser.getId();
		batteryId = battery.getId();
		machineId = machine.getId();
		rentalLogId = rentalLog.getId();
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
		
		BatteryUser batteryUser = batteryUserRepository.findById(batteryUserId);
		if (batteryUser != null) {
			batteryUserRepository.delete(batteryUser);
		}
		
		RentalLog rentalLog = rentalLogRepository.findById(rentalLogId);
		if (rentalLog != null) {
			rentalLogRepository.delete(rentalLog);
		}
	}
    

	@Test
	public void testRentalLogWithCrud() {
		
		Battery readBattery = batteryRepository.findById(batteryId);
		Machine readMachine = machineRepository.findById(machineId);
		BatteryUser readBatteryUser = batteryUserRepository.findById(batteryUserId);
		RentalLog readRentalLog = rentalLogRepository.findById(rentalLogId);
	
		//Read
		assertEquals(soc,readBattery.getSoc(),1e-6);
		assertEquals(textLocation,readMachine.getTextLocation());
		assertEquals(cardId, readBatteryUser.getCardId());
		assertEquals(timeRented, readRentalLog.getTimeRented());
		
		//Update
		readRentalLog.setTimeRented(timeRented2);
		rentalLogRepository.saveAndFlush(readRentalLog);
		
		//Test Update
		readRentalLog = rentalLogRepository.findById(rentalLogId);
		assertEquals(timeRented2,readRentalLog.getTimeRented());
		
		//Delete
		rentalLogRepository.delete(readRentalLog);
		
		//Test Delete
		readBattery = batteryRepository.findById(batteryId);
		readMachine = machineRepository.findById(machineId);
		readBatteryUser = batteryUserRepository.findById(batteryUserId);
		readRentalLog = rentalLogRepository.findById(rentalLogId);
		
		assertEquals(null,readRentalLog);
		assertEquals(textLocation,readMachine.getTextLocation());
		assertEquals(soc,readBattery.getSoc(),1e-6);
		assertEquals(cardId, readBatteryUser.getCardId());
	}
}
