package au.com.battery
.rental.persistence.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="heartbeat")
public class Heartbeat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="rng_id")
	private Integer rngId;
	@Column(name="time_sent")
	private long timeSent;
	@Column(name="time_received")
	private long timeReceived;
	@Column(name="machine_id")
	private Integer machineId;
	
	public Integer getRngId() {
		return rngId;
	}
	public void setRngId(Integer rngId) {
		this.rngId = rngId;
	}
	public long getTimeSent() {
		return timeSent;
	}
	public void setTimeSent(long timeSent) {
		this.timeSent = timeSent;
	}
	public long getTimeReceived() {
		return timeReceived;
	}
	public void setTimeReceived(long timeReceived) {
		this.timeReceived = timeReceived;
	}
	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}	
	
	
}
