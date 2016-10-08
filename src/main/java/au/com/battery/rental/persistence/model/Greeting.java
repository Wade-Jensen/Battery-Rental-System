package au.com.battery.rental.persistence.model;

import java.util.Date;

public class Greeting {

	private String name;
	private Date date;
	private Integer numBatteries;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getNumBatteries() {
		return numBatteries;
	}
	public void setNumBatteries(Integer numBatteries) {
		this.numBatteries = numBatteries;
	}
}
