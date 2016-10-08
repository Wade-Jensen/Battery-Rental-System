package au.com.battery.rental.persistence.model;


import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="battery")
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="soc")
    private Double soc;
    
    @Column(name="last_updated")
    private Timestamp lastUpdated;
    
    @Column(name="available")
    private Boolean available;
    
    @Column(name="date_purchased")
    private Timestamp datePurcahased;
    
    @Column(name="slot")
    private Integer slot;
    
    //Setup Foreign keys
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "machine_id")//Put this on the side of the relationship that owns the foreign key (This is where to put the data)
    private Machine machine;

    // constructor - sets the default values
    public Battery(){
    	
    }
    
    // getter and setters
   

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSoc() {
		return soc;
	}

	public void setSoc(Double soc) {
		this.soc = soc;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Boolean getavailable() {
		return available;
	}

	public void setavailable(Boolean available) {
		this.available = available;
	}

	public Timestamp getDatePurcahased() {
		return datePurcahased;
	}

	public void setDatePurcahased(Timestamp datePurcahased) {
		this.datePurcahased = datePurcahased;
	}


	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Integer getSlot() {
		return slot;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

    
    //

}