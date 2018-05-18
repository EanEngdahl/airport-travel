package airportsysmodel;

import java.util.ArrayList;

public class AircraftSectionList extends ArrayList<AircraftSection> {
	private static final long serialVersionUID = 5534735606050325939L;
	private enum sectionClassTypes {
		FIRST, BUSINESS, ECON_PLUS, ECON_BASIC;
	}

	public AircraftSectionList(char aircraftSize_, int seatsFilledPerSection_[]) {
		createSections(aircraftSize_, seatsFilledPerSection_);
	}
	
	public void createSections(char aircraftSize_, int seatsFilledPerSection_[]) {
		String _classSectionName;
		
		switch(aircraftSize_) {
		case 's':
			//create 1 section with proper name
			_classSectionName = sectionClassTypes.ECON_BASIC.toString();
			add(new AircraftSection(seatsFilledPerSection_[0], _classSectionName, aircraftSize_));
			break;
		case 'm':
			//create 3 sections with proper names
			_classSectionName = sectionClassTypes.ECON_BASIC.toString();
			add(new AircraftSection(seatsFilledPerSection_[0], _classSectionName, aircraftSize_));
			_classSectionName = sectionClassTypes.BUSINESS.toString();
			add(new AircraftSection(seatsFilledPerSection_[2], _classSectionName, aircraftSize_));
			_classSectionName = sectionClassTypes.FIRST.toString();
			add(new AircraftSection(seatsFilledPerSection_[3], _classSectionName, aircraftSize_));
			break;
		case 'l':
		default:
			//create 4 sections with proper names
			_classSectionName = sectionClassTypes.ECON_BASIC.toString();
			add(new AircraftSection(seatsFilledPerSection_[0], _classSectionName, aircraftSize_));
			_classSectionName = sectionClassTypes.ECON_PLUS.toString();
			add(new AircraftSection(seatsFilledPerSection_[1], _classSectionName, aircraftSize_));
			_classSectionName = sectionClassTypes.BUSINESS.toString();
			add(new AircraftSection(seatsFilledPerSection_[2], _classSectionName, aircraftSize_));
			_classSectionName = sectionClassTypes.FIRST.toString();
			add(new AircraftSection(seatsFilledPerSection_[3], _classSectionName, aircraftSize_));
			break;
		}	
	}
}
