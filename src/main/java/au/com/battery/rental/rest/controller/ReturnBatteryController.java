package au.com.battery.rental.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ReturnBatteryController {
		
	@RequestMapping("/api/returnbattery/machineId/{machineId}/machineSlot/{machineSlot}/cardId/{cardId}/time/{time}")
	public void returnBattery() {
		return;
	}
}
