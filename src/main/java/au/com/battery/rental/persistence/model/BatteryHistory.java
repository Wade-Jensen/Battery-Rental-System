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
@Table(name="battery_history")
public class BatteryHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="time_sent")
	private Timestamp timeSent;
	
	@Column(name="time_received")
	private Timestamp timeReceived;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	@Column(name="machine_slot")
	private Integer machineSlot;
	
	@ManyToOne
	@JoinColumn(name="battery_id")
	private Battery battery;
	
	@Column(name="charge_current")
	private Double chargeCurrent;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getTimeSent() {
		return timeSent;
	}
	public void setTimeSent(Timestamp timeSent) {
		this.timeSent = timeSent;
	}
	public Timestamp getTimeReceived() {
		return timeReceived;
	}
	public void setTimeReceived(Timestamp timeReceived) {
		this.timeReceived = timeReceived;
	}
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	public Integer getMachineSlot() {
		return machineSlot;
	}
	public void setMachineSlot(Integer machineSlot) {
		this.machineSlot = machineSlot;
	}
	
	public Battery getBattery() {
		return battery;
	}
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	public Double getChargeCurrent() {
		return chargeCurrent;
	}
	public void setChargeCurrent(Double chargeCurrent) {
		this.chargeCurrent = chargeCurrent;
	}	
}
