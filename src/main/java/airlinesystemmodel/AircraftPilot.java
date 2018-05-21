package airlinesystemmodel;

import java.math.BigDecimal;

public class AircraftPilot {
	private int seniority;
	private BigDecimal costPerFlight;

	public AircraftPilot(int seniority_) {
		seniority = seniority_;
		setCostPerFlight();
	}
	
	private void setCostPerFlight() {
		switch(seniority) {
			case 0:
				costPerFlight = new BigDecimal("400.00");
				break;
			case 1:
				costPerFlight = new BigDecimal("600.00");
				break;
			case 2:
				costPerFlight = new BigDecimal("800.00");
				break;
			default:
				costPerFlight = new BigDecimal("0.0");
				break;
		}
	}
	
	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public BigDecimal getCostPerFlight() {
		return costPerFlight;
	}
}
