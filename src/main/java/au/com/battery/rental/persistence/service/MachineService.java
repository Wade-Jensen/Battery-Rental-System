package au.com.battery.rental.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.battery.rental.persistence.dao.MachineRepository;
import au.com.battery.rental.persistence.model.Machine;

@Service
public class MachineService {
	
	@Autowired
	private MachineRepository machineRepo;
	
	public Machine findById(Integer machineId) {
		return machineRepo.findById(machineId);
	}
	
}
