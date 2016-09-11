package au.com.battery.rental.persistence.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rental_log")
public class RentalLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="time_rented")
    private Timestamp timeRented;

    @Column(name="time_returned")
    private Timestamp timeReturned;

    @Column(name="initial_charge")
    private Double initialCharge;
    
    @Column(name="final_charge")
    private Double finalCharge;
    

    //Setup Foreign keys
    
    @ManyToOne
    @JoinColumn(name = "user_id")//Put this on the side of the relationship that owns the foreign key (This is where to put the data)
    private BatteryUser batteryUser;
    
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
    
    @ManyToOne
    @JoinColumn(name = "battery_id")
    private Battery battery;
    
    // constructor - sets the default values
    public RentalLog(){
    	
    }

    // getter and setters
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTimeRented() {
		return timeRented;
	}

	public void setTimeRented(Timestamp timeRented) {
		this.timeRented = timeRented;
	}

	public Timestamp getTimeReturned() {
		return timeReturned;
	}

	public void setTimeReturned(Timestamp timeReturned) {
		this.timeReturned = timeReturned;
	}

	public Double getInitialCharge() {
		return initialCharge;
	}

	public void setInitialCharge(Double initialCharge) {
		this.initialCharge = initialCharge;
	}

	public Double getFinalCharge() {
		return finalCharge;
	}

	public void setFinalCharge(Double finalCharge) {
		this.finalCharge = finalCharge;
	}

	public BatteryUser getBatteryUser() {
		return batteryUser;
	}

	public void setBatteryUser(BatteryUser batteryUser) {
		this.batteryUser = batteryUser;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

    
    //

}