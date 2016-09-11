package au.com.battery.rental.persistence.model;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rental_log")
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double soc;
    
    private Timestamp lastUpdated;
    
    private Boolean avaliable;
    
    private Timestamp datePurcahased;
    
    private Timestamp dateReturned;
    
    //Setup Foreign keys
    
    @ManyToOne
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

	public Boolean getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(Boolean avaliable) {
		this.avaliable = avaliable;
	}

	public Timestamp getDatePurcahased() {
		return datePurcahased;
	}

	public void setDatePurcahased(Timestamp datePurcahased) {
		this.datePurcahased = datePurcahased;
	}

	public Timestamp getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Timestamp dateReturned) {
		this.dateReturned = dateReturned;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

    
    //

}