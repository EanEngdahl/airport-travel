/*
 * PilotBuilder class
 *		Creates a new pilot object given specific requirements
 */

package airlinesystemcontroller;

import java.math.BigDecimal;
import java.util.Properties;

import airlinesystemmodel.AircraftPilot;

public class PilotBuilder {
    
    private BigDecimal _juniorCost;
    private BigDecimal _midlevelCost;
    private BigDecimal _seniorCost;
    
	private static final char SENIOR = 'l';
    private static final char MIDLEVEL = 'm';
    private static final char JUNIOR = 's';

    
    public PilotBuilder(Properties modelProperties_) {
    	_juniorCost = new BigDecimal(modelProperties_.getProperty("JUNIOR_PILOT_PAY"));
    	_midlevelCost = new BigDecimal(modelProperties_.getProperty("MIDLEVEL_PILOT_PAY"));
    	_seniorCost = new BigDecimal(modelProperties_.getProperty("SENIOR_PILOT_PAY"));
    }
    
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
                returnPilot.setCostPerFlight(_midlevelCost);
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
