package airlinesystemcontroller;

import java.math.BigDecimal;

import airlinesystemmodel.AircraftPilot;

public class PilotDispatcher {
    
    private BigDecimal _juniorCost = new BigDecimal("400.00");
    private BigDecimal _midCost = new BigDecimal("600.00");
    private BigDecimal _seniorCost = new BigDecimal("800.00");
    
	private static final char SENIOR = 'l';
    private static final char MIDLEVEL = 'm';
    private static final char JUNIOR = 's';

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
