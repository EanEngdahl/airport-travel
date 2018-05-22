package airlinesystemmodel;

import java.math.BigDecimal;

public class AircraftPilot {
    private int seniority;
    private BigDecimal costPerFlight;





    /**
     *
     */
    public AircraftPilot() {    }





    /**
     * 
     */
    public int getSeniority() {
        return seniority;
    }





    /**
     * 
     */
    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }





    /**
     * 
     */
    public final void setCostPerFlight(BigDecimal costPerFlight_) {
        costPerFlight = costPerFlight_;
    }





    /**
     * 
     */
    public BigDecimal getCostPerFlight() {
        return costPerFlight;
    }
}
