package airportsysmodel;

public class Flight {
	private char airplaneSize;
	private AircraftPilot pilot;
	private AircraftPilot coPilot;
	
	public char getAirplaneSize() {
		return airplaneSize;
	}
	
	public void setAirplaneSize(char airplaneSize) {
		this.airplaneSize = airplaneSize;
	}
	
	public AircraftPilot getPilot() {
		return pilot;
	}
	
	public AircraftPilot getCoPilot() {
		return coPilot;
	}
}
