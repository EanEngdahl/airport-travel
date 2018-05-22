package airlinesystemmodel;

public class PilotDispatcher {
	
	public AircraftPilot assignPilotToAircraft(char aircraftSize_) {
		AircraftPilot returnPilot;
		switch(aircraftSize_) {
			case 's':
				returnPilot = new AircraftPilot(0);
				return returnPilot;
			case 'm':
				returnPilot = new AircraftPilot(1);
				return returnPilot;
			case 'l':
			default:
				returnPilot = new AircraftPilot(2);
				return returnPilot;
		}
	}
}
