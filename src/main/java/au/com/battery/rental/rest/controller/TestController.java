package au.com.battery.rental.rest.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.battery.rental.persistence.model.Greeting;


public class TestController {
	
	@RequestMapping("/test/name/{nameStr}/time/{timestampMilli}/capacity/{numBatteries}")
	public Greeting requestBattery(@PathVariable String name, @PathVariable long time, @PathVariable Integer numBatteries) {
		
		Greeting greeting = new Greeting();
		greeting.setName(name);
		greeting.setDate( new Date(time) );
		greeting.setNumBatteries(numBatteries);
		
		return greeting;
	}
	
	
}
