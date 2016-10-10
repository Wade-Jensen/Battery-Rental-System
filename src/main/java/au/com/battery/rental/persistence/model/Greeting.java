package au.com.battery.rental.persistence.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Greeting {

	private String name;
	private long date;
	private Integer numBatteries;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long l) {
		this.date = l;
	}
	public Integer getNumBatteries() {
		return numBatteries;
	}
	public void setNumBatteries(Integer numBatteries) {
		this.numBatteries = numBatteries;
	}
}
