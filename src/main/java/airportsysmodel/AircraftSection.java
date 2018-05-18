package airportsysmodel;

import java.math.BigDecimal;

public class AircraftSection {
	private int maxNumOfSeats;
	private int filledSeats;
	private BigDecimal costOfSeat;
	private String classSectionName;
	
	private static final int SMALL_MAX_SEATS = 50;
	private static final int MEDIUM_MAX_BASIC_SEATS = 100;
	private static final int MEDIUM_MAX_BUSINESS_SEATS = 70;
	private static final int MEDIUM_MAX_FIRST_SEATS = 30;
	private static final int LARGE_MAX_BASIC_SEATS = 150;
	private static final int LARGE_MAX_PLUS_SEATS = 100;
	private static final int LARGE_MAX_BUSINESS_SEATS = 100;
	private static final int LARGE_MAX_FIRST_SEATS = 50;
	
	public AircraftSection(int filledSeats_, String classSectionName_,
			char aircraftSize_) {
		filledSeats = filledSeats_;
		classSectionName = classSectionName_;
		setMaxNumOfSeatsAndSeatCost(aircraftSize_);
		
	}
	
	private void setCostOfSeat(char aircraftSize_) {
		int _aircraftSizeCostModifier;
		int _baseCost = 500;

		if(aircraftSize_ == 's') {
			_aircraftSizeCostModifier = 1;
		} else if (aircraftSize_ == 'm') {
			_aircraftSizeCostModifier = 2;
		} else {
			_aircraftSizeCostModifier = 3;
		}

		costOfSeat = new BigDecimal(_baseCost * (_aircraftSizeCostModifier * maxNumOfSeats));
		
	}
	
	private void setMaxNumOfSeatsAndSeatCost(char aircraftSize_) {
		if (aircraftSize_ == 's') {
			maxNumOfSeats = SMALL_MAX_SEATS;
		}
		else if (aircraftSize_ == 'm') {
			switch(classSectionName) {
				case "econ_basic":
					maxNumOfSeats = MEDIUM_MAX_BASIC_SEATS;
					setCostOfSeat(aircraftSize_);
					break;
				case "business":
					maxNumOfSeats = MEDIUM_MAX_BUSINESS_SEATS;
					setCostOfSeat(aircraftSize_);
					break;
				case "first":
					maxNumOfSeats = MEDIUM_MAX_FIRST_SEATS;
					setCostOfSeat(aircraftSize_);
					break;
			}
		}
		else {
			switch(classSectionName) {
				case "econ_basic":
					maxNumOfSeats = LARGE_MAX_BASIC_SEATS;
					setCostOfSeat(aircraftSize_);
					break;
				case "econ_plus":
					maxNumOfSeats = LARGE_MAX_PLUS_SEATS;
					setCostOfSeat(aircraftSize_);
					break;
				case "business":
					maxNumOfSeats = LARGE_MAX_BUSINESS_SEATS;
					setCostOfSeat(aircraftSize_);
					break;
				case "first":
					maxNumOfSeats = LARGE_MAX_FIRST_SEATS;
					setCostOfSeat(aircraftSize_);
					break;
			}
		}
	}
}
