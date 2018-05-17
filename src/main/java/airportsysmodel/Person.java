/**
 * 
 */
package airporttravel;

/**
 * 
 *
 */
public class Person {
	private String firstName;
	private String lastName;
	private boolean inFlight;
	private String location;

	public Person() {
		this("","", false, "");
	}

	public Person(String fn_, String ln_, boolean inFlight_,
			String location_) {
		firstName = fn_;
		lastName = ln_;
		inFlight = inFlight_;
		location = location_;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName_) {
		this.firstName = firstName_;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName_) {
		this.lastName = lastName_;
	}

	public boolean isInFlight() {
		return inFlight;
	}

	public void setInFlight(boolean inFlight_) {
		this.inFlight = inFlight_;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location_) {
		this.location = location_;
	}

	
}
