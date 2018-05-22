package airlinesystemmodel;

import java.math.BigDecimal;
import java.util.stream.IntStream;

public class Aircraft {

	/* will be size of s, m, l */
	private char aircraftSize;
	private AircraftSectionList sectionList;
	private int maxAircraftSeats;
	private int totalNumOfPassengers;

		
	public Aircraft(char aircraftSize_, int seatsFilledPerSection_[], 
			BigDecimal costOfSeat_[], int maxSeatsPerSection_[]) {
		aircraftSize = aircraftSize_;
		sectionList = new AircraftSectionList(aircraftSize_, seatsFilledPerSection_, costOfSeat_);
		setMaxAircraftSeats(maxSeatsPerSection_);
		setTotalNumOfPassengers(seatsFilledPerSection_);
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

	/**
	 * TODO move logic
	 */
	public void setMaxAircraftSeats(int maxSeatsPerSection_[]) {
		maxAircraftSeats = IntStream.of(maxSeatsPerSection_).sum();
	}
	
	public void setTotalNumOfPassengers(int seatsFilledPerSection_[]) {
		totalNumOfPassengers = IntStream.of(seatsFilledPerSection_).sum();
	}
	
	public int getTotalNumOfPassengers() {
		return totalNumOfPassengers;
	}
}
