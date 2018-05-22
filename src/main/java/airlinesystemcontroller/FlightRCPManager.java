package airlinesystemcontroller;

import java.math.BigDecimal;

import airlinesystemmodel.Flight;

public class FlightRCPManager {
	private static final BigDecimal FUELCOST = new BigDecimal("15");

	private BigDecimal revenue;
	private BigDecimal cost;
	private BigDecimal profit;
	
	public FlightRCPManager() {}
	
	public FlightRCPManager(Flight flightToCalculate_) {
		findProfit(flightToCalculate_);
	}
	
	public BigDecimal findRevenue(Flight flightToCalculate_) {
		revenue = new BigDecimal("0");
		int seatsFilledPerSection_[] = flightToCalculate_.getSeatsFilledPerSection();
		BigDecimal seatCostPerSection_[] = flightToCalculate_.getSeatCostPerSection();
		
		for (int i = 0; i < flightToCalculate_.getSeatsFilledPerSection().length; i++) {
			BigDecimal _numberOfPeopleInClass = new BigDecimal(seatsFilledPerSection_[i]);
			revenue = revenue.add(_numberOfPeopleInClass.multiply(seatCostPerSection_[i]));
		}
		return revenue;
	}

	public BigDecimal findCost(Flight flightToCalculate_) {
		cost = new BigDecimal(flightToCalculate_.getDistanceTravelled());
		cost = cost.multiply(FUELCOST);
		cost = cost.add(flightToCalculate_.getCoPilot().getCostPerFlight());
		cost = cost.add(flightToCalculate_.getPilot().getCostPerFlight());
		return cost;
	}
	
	public BigDecimal findProfit(Flight flightToCalculate_) {
		profit = new BigDecimal("0");
		profit = profit.add(findRevenue(flightToCalculate_));
		profit = profit.subtract(findCost(flightToCalculate_));
		return profit;
	}
	
	public BigDecimal getRevenue() {
		return revenue;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public BigDecimal getProfit() {
		return profit;
	}
	public static BigDecimal getFuelcost() {
		return FUELCOST;
	}
}
