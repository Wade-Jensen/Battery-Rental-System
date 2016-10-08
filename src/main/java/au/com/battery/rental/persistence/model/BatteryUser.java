package au.com.battery.rental.persistence.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="battery_user")
public class BatteryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(unique=true, name="card_id")
    private String cardId;
    
    private String firstName;

    private String lastName;

    private Double credit;
    
    @Column(name="user_id")
    private Integer userId;

	// constructor - sets the default values
    public BatteryUser(){
    	
    }
    
    // getter and setters
    
    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer loginId) {
		this.userId = loginId;
	}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}  

}