package au.com.battery.rental.persistence.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="gps_location")
    private Double gpsLocation;
    
    @Column(name="text_location")
    private String textLocation;

    @Column(name="model_type")
    private String modelType;
    
    // constructor - sets the default values
    public Machine(){
    	
    }
    
    // getter and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getGpsLocation() {
		return gpsLocation;
	}

	public void setGpsLocation(Double gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public String getTextLocation() {
		return textLocation;
	}

	public void setTextLocation(String textLocation) {
		this.textLocation = textLocation;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
    
    //

}