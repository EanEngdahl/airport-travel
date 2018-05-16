package airporttravel;

public class Aircraft {

	private String name;
	private String model;
	private int maxCapacity;
	private int range;
	private boolean inFlight;
	private String source;
	private String destination;

	public Aircraft() {
		this("", "", -1, -1, false, "","");
	}

	public Aircraft(String name_, String model_, int maxCapacity_, int range_,
			boolean inFlight_, String source_, String destination_) {
		name = name_;
		model = model_;
		maxCapacity = maxCapacity_;
		range = range_;
		inFlight = inFlight_;
		source = source_;
		destination = destination_;
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

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int capacity_) {
		maxCapacity = capacity_;
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
		this.inFlight = inFlight_;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source_) {
		this.source = source_;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination_) {
		this.destination = destination_;
	}	

}