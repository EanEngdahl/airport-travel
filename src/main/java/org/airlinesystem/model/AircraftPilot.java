package org.airlinesystem.model;

import java.math.BigDecimal;

public class AircraftPilot {

    private BigDecimal costPerFlight;
    private AircraftPilotSeniority seniority;

    public AircraftPilot() {}

    public AircraftPilotSeniority getSeniority() {
        return seniority;
    }

    public void setSeniority(AircraftPilotSeniority seniority_) {
        seniority = seniority_;
    }

    public final void setCostPerFlight(BigDecimal costPerFlight_) {
        costPerFlight = costPerFlight_;
    }

    public BigDecimal getCostPerFlight() {
        return costPerFlight;
    }
}
