package airportsysmodel;

public class Aircraft {

	/* will be size of s, m, l */
	private char aircraftSize;
	private AircraftSectionList sectionList;
	private int maxAircraftSeats;
	
	private static final int SMALL_PLANE_SEATS = 50;
	private static final int MEDIUM_PLANE_SEATS = 200;
	private static final int LARGE_PLANE_SEATS = 400;
		
	public Aircraft(char aircraftSize_, int seatsFilledPerSection_[]) {
		aircraftSize = aircraftSize_;
		sectionList = new AircraftSectionList(aircraftSize_, seatsFilledPerSection_);
		setMaxAircraftSeats();
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

	public void setSectionList(AircraftSectionList sectionList) {
		this.sectionList = sectionList;
	}

	public int getMaxAircraftSeats() {
		return maxAircraftSeats;
	}

	public void setMaxAircraftSeats() {
		switch(aircraftSize) {
			case 's':
				maxAircraftSeats = SMALL_PLANE_SEATS;
				break;
			case 'm':
				maxAircraftSeats = MEDIUM_PLANE_SEATS;
				break;
			case 'l':
			default:
				maxAircraftSeats = LARGE_PLANE_SEATS;
				break;
		}
	}
}
