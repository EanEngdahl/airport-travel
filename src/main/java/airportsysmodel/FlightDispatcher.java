package airportsysmodel;

public class FlightDispatcher {
	private static final int SHORT_RANGE_BOUND = 500;
	private static final int MEDIUM_RANGE_BOUND = 1000;
	private String source;
	private String destination;
	private int distanceTravelled;
	private int seatsFilledPerSection[];
	private int maxSeatsPerSection[];

	public FlightDispatcher(String source_, String dest_, int seatsFilledPerSection_[],
			int maxSeatsPerSection_[]) {
		source = source_;
		destination = dest_;
		seatsFilledPerSection = seatsFilledPerSection_;
		maxSeatsPerSection = maxSeatsPerSection_;
	}
}
