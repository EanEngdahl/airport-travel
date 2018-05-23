/*
 * PilotBuilder class
 *		Creates a new pilot object given specific requirements
 */

package airlinesystemcontroller;

import java.math.BigDecimal;

import airlinesystemmodel.AircraftPilot;

public class PilotBuilder {
    
    private BigDecimal _juniorCost = new BigDecimal("400.00");
    private BigDecimal _midCost = new BigDecimal("600.00");
    private BigDecimal _seniorCost = new BigDecimal("800.00");
    
	private static final char SENIOR = 'l';
    private static final char MIDLEVEL = 'm';
    private static final char JUNIOR = 's';

    /*
     * Create new pilot based off size of plane they will be flying
     * 
     * @param aircraftSize_
     * 		character that represents the size of plane that will be
     * 		passed in and used to determine the pilot assigned
     * @return
     * 		a new pilot to be assigned to the flight
     */
    public AircraftPilot assignPilotToAircraft(char aircraftSize_) {
        AircraftPilot returnPilot;
        switch (aircraftSize_) {
            case SENIOR:
                returnPilot = new AircraftPilot();
                returnPilot.setSeniority(2);
                returnPilot.setCostPerFlight(_seniorCost);
                return returnPilot;
            case MIDLEVEL:
                returnPilot = new AircraftPilot();
                returnPilot.setSeniority(1);
                returnPilot.setCostPerFlight(_midCost);
                return returnPilot;
            case JUNIOR:
            default:
                returnPilot = new AircraftPilot();
                returnPilot.setSeniority(0);
                returnPilot.setCostPerFlight(_juniorCost);
                return returnPilot;
        }
    }
}
