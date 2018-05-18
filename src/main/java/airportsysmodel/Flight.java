package airportsysmodel;

import java.math.BigDecimal;

public class Flight {
	private char aircraftSize;
	private AircraftPilot pilot;
	private AircraftPilot coPilot;
	private Aircraft aircraftAssigned;
	private int seatsFilledPerSection[];
	
	public Flight(char aircraftSize_, int maxSeatsPerSection_[], 
			int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
			String source_, String dest_, int distanceTravelled_,
			AircraftPilot pilot_, AircraftPilot coPilot_) {

		aircraftSize = aircraftSize_;
		seatsFilledPerSection = seatsFilledPerSection_;
		pilot = pilot_;
		coPilot = coPilot_;
		aircraftAssigned = new Aircraft(aircraftSize, seatsFilledPerSection, 
				seatCostPerSection_, maxSeatsPerSection_);
		
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
	
	public Aircraft getAircraftAssigned() {
		return aircraftAssigned;
	}
}
