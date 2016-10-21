package au.com.battery.rental.rest.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Live {
	
	@RequestMapping(value="/api/alive")
	public long alive() {
	
		Date time = new Date();
		
		return time.getTime();
	
	}
}
