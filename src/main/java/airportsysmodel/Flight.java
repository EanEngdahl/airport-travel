package airportsysmodel;

public class Flight {
	private char aircraftSize;
	private AircraftPilot pilot;
	private AircraftPilot coPilot;
	private Aircraft aircraftAssigned;
	private int seatsFilledPerSection[];
	
	public Flight(char aircraftSize_, int seatsFilledPerSection_[], 
			AircraftPilot pilot_, AircraftPilot coPilot_) {
		aircraftSize = aircraftSize_;
		seatsFilledPerSection = seatsFilledPerSection_;
		pilot = pilot_;
		coPilot = coPilot_;
		aircraftAssigned = new Aircraft(aircraftSize, seatsFilledPerSection);
		
	}
	
	public char getAircraftSize() {
		return aircraftSize;
	}
	
	public void setAircraftSize(char airplaneSize) {
		this.aircraftSize = airplaneSize;
	}
	
	public AircraftPilot getPilot() {
		return pilot;
	}
	
	public AircraftPilot getCoPilot() {
		return coPilot;
	}
}
