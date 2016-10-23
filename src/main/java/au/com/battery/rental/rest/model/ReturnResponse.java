package au.com.battery.rental.rest.model;

public class ReturnResponse {
	
	private Boolean allowReturn;
	
	private Double credit;

	public Boolean getAllowReturn() {
		return allowReturn;
	}

	public void setAllowReturn(Boolean allowReturn) {
		this.allowReturn = allowReturn;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}	
	
}
