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
import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.model.RentalLog;
import au.com.battery.rental.spring.ConfigTest;
import au.com.battery.rental.spring.PersistenceJPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class MachineRepositoryTest {
	
	// Member variable values for assertions
	
		//Battery
	private Integer batteryId;
	private Double soc = 2.5;
		//Battery User
	private Integer batteryUserId;
	private String cardId = "XXXX";
		//Machine
	private Integer machineId;
	private String textLocation = "QUT";
		//RentalLog
	private Integer rentalLogId;
	private Double initialCharge1 = 5.34;
	
		
	//Initialize Repo
	@Autowired
    private MachineRepository machineRepository;
	@Autowired
    private BatteryUserRepository batteryUserRepository;
	@Autowired
    private BatteryRepository batteryRepository;
	@Autowired
    private RentalLogRepository rentalLogRepository;
	
	//---------------Setup Before and after---------------
	@Before
	public void setUp(){
		
	//Setup Machine
		Machine machine = new Machine();
		machine.setTextLocation(textLocation);
	//Setup Battery
		Battery battery = new Battery();
		battery.setSoc(soc);
	//Setup BatteryUser
		BatteryUser batteryUser = new BatteryUser();
		batteryUser.setCardId(cardId);
	//Setup RentalLog
		RentalLog rentalLog = new RentalLog();
		rentalLog.setBattery(battery);
		rentalLog.setMachine(machine);
		rentalLog.setBatteryUser(batteryUser);
		rentalLog.setInitialCharge(initialCharge1);
		
	machineId = machine.getId();
	batteryId = battery.getId();
	batteryUserId = batteryUser.getId();
	rentalLogId = rentalLog.getId();
	}

	@After
	public void tearDown(){
		Machine machine = machineRepository.findById(machineId);
		if (machine != null){
			machineRepository.delete(machine);
		}
		Battery battery = batteryRepository.findById(batteryId);
		if (battery != null){
			batteryRepository.delete(battery);
		}
		BatteryUser batteryUser = batteryUserRepository.findByCardId(cardId);
		if (batteryUser != null){
			batteryUserRepository.delete(batteryUser);
		}
		RentalLog rentalLog = rentalLogRepository.findById(rentalLogId);
		if (rentalLog != null){
			rentalLogRepository.delete(rentalLog);
		}
	}
    
	@Test
	public void testMachineWithCrud() {
		
		Battery battery = batteryRepository.findByCardId(cardId);
		Machine machine = machineRepository.findById(machineId);
		BatteryUser batteryUser = batteryUserRepository.findByCardId(cardId);
		RentalLog rentalLog = rentalLogRepository.findById(rentalLogId);
	
		//Read
		assertEquals(battery,rentalLog.getBattery());
		assertEquals(machine,rentalLog.getMachine());
		assertEquals(batteryUser,rentalLog.getBattery());
		assertEquals(initialCharge1, rentalLog.getInitialCharge());
		
		//Update
		
		rentalLog.setInitialCharge(initialCharge1*2);

		rentalLogRepository.saveAndFlush(rentalLog);
		
		//Test Update
		
		RentalLog readRentalLog = rentalLogRepository.findById(rentalLogId);
		
		assertEquals(initialCharge1*2,readRentalLog.getInitialCharge(),1e-6);
		
		//Delete
		rentalLogRepository.delete(readRentalLog);
		
		//Test Delete
		RentalLog readRentalLog2 = rentalLogRepository.findById(rentalLogId);
		assertEquals(null,readRentalLog2);
		
	}

}
