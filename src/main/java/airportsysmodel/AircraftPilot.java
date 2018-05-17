package airportsysmodel;

public class AircraftPilot {
	private int seniority;
	private String costPerFlight;

	public AircraftPilot(int seniority_) {
		seniority = seniority_;
		setCostPerFlight();
	}
	
	private void setCostPerFlight() {
		switch(seniority) {
			case 0:
				costPerFlight = "400.00";
				break;
			case 1:
				costPerFlight = "600.00";
				break;
			case 2:
				costPerFlight = "800.00";
				break;
			default:
				costPerFlight = "empty";
				break;
		}
	}
	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public String getCostPerFlight() {
		return costPerFlight;
	}

}
