package airportsysmodel;

import java.util.ArrayList;
import java.math.BigDecimal;

public class AircraftSectionList extends ArrayList<AircraftSection> {
	private static final long serialVersionUID = 5534735606050325939L;
	private enum sectionClassTypes {
		FIRST, BUSINESS, ECON_PLUS, ECON_BASIC;
	}

	public AircraftSectionList(char aircraftSize_, int seatsFilledPerSection_[], BigDecimal costOfSeat_[]) {
		createSections(aircraftSize_, seatsFilledPerSection_, costOfSeat_);
	}
	
	public void createSections(char aircraftSize_, int seatsFilledPerSection_[], BigDecimal costOfSeat_[]) {
		String _classSectionName;
		
		switch(aircraftSize_) {
		case 's':
			//create 1 section with proper name
			_classSectionName = sectionClassTypes.ECON_BASIC.toString();
			add(new AircraftSection(seatsFilledPerSection_[0], _classSectionName, costOfSeat_[0]));
			break;
		case 'm':
			//create 3 sections with proper names
			_classSectionName = sectionClassTypes.ECON_BASIC.toString();
			add(new AircraftSection(seatsFilledPerSection_[0], _classSectionName, costOfSeat_[0]));
			_classSectionName = sectionClassTypes.BUSINESS.toString();
			add(new AircraftSection(seatsFilledPerSection_[2], _classSectionName, costOfSeat_[1]));
			_classSectionName = sectionClassTypes.FIRST.toString();
			add(new AircraftSection(seatsFilledPerSection_[3], _classSectionName, costOfSeat_[3]));
			break;
		case 'l':
		default:
			//create 4 sections with proper names
			_classSectionName = sectionClassTypes.ECON_BASIC.toString();
			add(new AircraftSection(seatsFilledPerSection_[0], _classSectionName, costOfSeat_[0]));
			_classSectionName = sectionClassTypes.ECON_PLUS.toString();
			add(new AircraftSection(seatsFilledPerSection_[1], _classSectionName, costOfSeat_[1]));
			_classSectionName = sectionClassTypes.BUSINESS.toString();
			add(new AircraftSection(seatsFilledPerSection_[2], _classSectionName, costOfSeat_[2]));
			_classSectionName = sectionClassTypes.FIRST.toString();
			add(new AircraftSection(seatsFilledPerSection_[3], _classSectionName, costOfSeat_[3]));
			break;
		}	
	}
}
