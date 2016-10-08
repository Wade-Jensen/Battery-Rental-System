package au.com.battery.rental.rest.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.battery.rental.persistence.model.Greeting;

@RestController
public class TestController {
	
	@RequestMapping(value="api/test/name", method=RequestMethod.GET ) //{nameStr}/time/{timestampMilli}/capacity/{numBatteries}")
	public Greeting requestBattery() { //@PathVariable String name, @PathVariable long time, @PathVariable Integer numBatteries) {
		
		Greeting greeting = new Greeting();
		greeting.setName("WADE");
		
		//Greeting greeting = new Greeting();
		//greeting.setName(name);
		//greeting.setDate( new Date(time).getTime() );
		//greeting.setNumBatteries(numBatteries);
		
		return greeting;
	}
	
	
}
