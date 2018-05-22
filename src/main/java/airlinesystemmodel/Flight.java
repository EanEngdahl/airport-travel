package airlinesystemmodel;

import java.math.BigDecimal;

public class Flight {
	private char aircraftSize;
	private AircraftPilot pilot;
	private AircraftPilot coPilot;
	private Aircraft aircraftAssigned;
	private int seatsFilledPerSection[];
	private BigDecimal seatCostPerSection[];
	private int distanceTravelled;
	private Airport source;
	private Airport dest;
	
	public Flight(char aircraftSize_, int maxSeatsPerSection_[], 
			int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
			Airport source_, Airport dest_, int distanceTravelled_,
			AircraftPilot pilot_, AircraftPilot coPilot_) {

		aircraftSize = aircraftSize_;
		seatsFilledPerSection = seatsFilledPerSection_;
		seatCostPerSection = seatCostPerSection_;
		distanceTravelled = distanceTravelled_;
		source = source_;
		dest = dest_;
		pilot = pilot_;
		coPilot = coPilot_;
		aircraftAssigned = new Aircraft(aircraftSize, seatsFilledPerSection, 
				seatCostPerSection, maxSeatsPerSection_);
		
	}
	
	public char getAircraftSize() {
		return aircraftSize;
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

	public BigDecimal[] getSeatCostPerSection() {
		return seatCostPerSection;
	}
	
	
	/**
	 * TODO: NO LOGIC IN IDENTITY CLASSES
	 */
	public BigDecimal getSeatCostPerSectionAtIndex(int index) {
		return seatCostPerSection[index];
	}

	public int getSeatsFilledPerSectionAtIndex(int index) {
		return seatsFilledPerSection[index];
	}
	
	public int[] getSeatsFilledPerSection() {
		return seatsFilledPerSection;
	}

	public int getDistanceTravelled() {
		return distanceTravelled;
	}

	public Airport getSource() {
		return source;
	}

	public Airport getDest() {
		return dest;
	}
	
}
