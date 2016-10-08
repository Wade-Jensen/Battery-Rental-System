package au.com.battery.rental.persistence.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="lat")
    private double lat;
    
    @Column(name="lon")
    private double lon;
    
    @Column(name="text_location")
    private String textLocation;

    @Column(name="model_type")
    private String modelType;
    
    @Column(name="num_batteries")
    private Integer numBatteries;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "machine")
    private List<Battery> batteries;
    
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

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
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

	public List<Battery> getBatteries() {
		return batteries;
	}

	public void setBatteries(List<Battery> batteries) {
		this.batteries = batteries;
	}
    
    //

}