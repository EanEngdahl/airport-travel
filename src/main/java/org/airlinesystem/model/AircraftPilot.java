package org.airlinesystem.model;

import java.math.BigDecimal;

public class AircraftPilot {
    private int seniority;
    private BigDecimal costPerFlight;

    public AircraftPilot() {}

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority_) {
        seniority = seniority_;
    }

    public final void setCostPerFlight(BigDecimal costPerFlight_) {
        costPerFlight = costPerFlight_;
    }

    public BigDecimal getCostPerFlight() {
        return costPerFlight;
    }
}
