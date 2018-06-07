package org.airlinesystem.model;

import java.math.BigDecimal;

public class Aircraft {

	/* will be size of s, m, l */
	private char aircraftSize;
	private AircraftSectionList sectionList;
	private int maxAircraftSeats;
	private int totalNumOfPassengers;

		
	public Aircraft(char aircraftSize_, int totalNumOfPassengers_, int seatsFilledPerSection_[], 
			BigDecimal costOfSeat_[], int maxAircraftSeats_) {
		aircraftSize = aircraftSize_;
		sectionList = new AircraftSectionList(aircraftSize_, seatsFilledPerSection_, costOfSeat_);
		maxAircraftSeats = maxAircraftSeats_;
		totalNumOfPassengers = totalNumOfPassengers_;
	}

	public char getAircraftSize() {
		return aircraftSize;
	}

	public void setAircraftSize(char aircraftSize) {
		this.aircraftSize = aircraftSize;
	}

	public AircraftSectionList getSectionList() {
		return sectionList;
	}

	public int getMaxAircraftSeats() {
		return maxAircraftSeats;
	}
	
	public void setMaxAircraftSeats(int maxAircraftSeats_) {
		maxAircraftSeats = maxAircraftSeats_;
	}
	
	public void setTotalNumOfPassengers(int totalNumOfPassengers_) {
		totalNumOfPassengers = totalNumOfPassengers_;
	}
	
	public int getTotalNumOfPassengers() {
		return totalNumOfPassengers;
	}
}
