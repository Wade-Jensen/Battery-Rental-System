package au.com.battery.rental.rest.model;

public class FullChargeResponse {

	private long timeSent;
	private long timeReceived;
	private Integer machineId;
	private Integer machineSlot;
	private Integer batteryId;
	
	
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
	public Integer getMachineSlot() {
		return machineSlot;
	}
	public void setMachineSlot(Integer machineSlot) {
		this.machineSlot = machineSlot;
	}
	public Integer getBatteryId() {
		return batteryId;
	}
	public void setBatteryId(Integer batteryId) {
		this.batteryId = batteryId;
	}
		
}
