package au.com.battery.rental.rest.model;

public class RentalResponse {
	
	private Boolean isUserBalancePositive;
	private Double balance;
	
	private Boolean releaseBattery;
	private Integer machineSlot;
	
	public Integer getMachineSlot() {
		return machineSlot;
	}
	public void setMachineSlot(Integer machineSlot) {
		this.machineSlot = machineSlot;
	}
	public Boolean getIsUserBalancePositive() {
		return isUserBalancePositive;
	}
	public void setIsUserBalancePositive(Boolean userBalancePositive) {
		this.isUserBalancePositive = userBalancePositive;
	}
	public Boolean getReleaseBattery() {
		return releaseBattery;
	}
	public void setReleaseBattery(Boolean releaseBattery) {
		this.releaseBattery = releaseBattery;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
