package airporttravel;

public class Aircraft {

	private String name;
	private String model;
	private String location;
	private String heading;
	private int capacity;
	private int range;
	private boolean inFlight;
	
	public Aircraft() {
		this("", "", "", 0, 0, false);
	}

	public Aircraft(String name_, String model_, String position_, int capacity_, int range_, boolean inFlight_) {
		name = name_;
		model = model_;
		capacity = capacity_;
		range = range_;
		inFlight = inFlight_;

		if (inFlight) {
			location = null;
			heading = position_;
		}
		else {
			location = position_;
			heading = null;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name_) {
		name = name_;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model_) {
		model = model_;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location_) {
		location = location_;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading_) {
		heading = heading_;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity_) {
		capacity = capacity_;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range_) {
		range = range_;
	}

	public boolean isInFlight() {
		return inFlight;
	}

	public void setInFlight(boolean inFlight_) {
		inFlight = inFlight_;
	}
	
}
