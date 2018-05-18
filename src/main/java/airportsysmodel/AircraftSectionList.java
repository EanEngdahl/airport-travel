package airportsysmodel;

import java.util.ArrayList;

public class AircraftSectionList extends ArrayList<AircraftSection> {
	private static final long serialVersionUID = 5534735606050325939L;
	enum sectionClassTypes {
		FIRST, BUSINESS, ECON_PLUS, ECON_BASIC;
	}

	public AircraftSectionList(char aircraftSize_, int seatsFilledPerSection[]) {
		switch(aircraftSize_) {
			case 's':
				//create 1 section with proper name
			case 'm':
				//create 3 sections with proper names
			case 'l':
				//create 4 sections with proper names
		}	
	}
	
	private void something() {
		switch(aircraftSize) {
			case 's':


	}
}
