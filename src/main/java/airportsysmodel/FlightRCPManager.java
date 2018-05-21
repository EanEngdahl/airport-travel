package airportsysmodel;

import java.math.BigDecimal;

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
		for (int i = 0; i < flightToCalculate_.getSeatsFilledPerSection().length; i++) {
			BigDecimal _numberOfPeopleInClass = new BigDecimal(flightToCalculate_.getSeatsFilledPerSectionAtIndex(i));
			revenue = revenue.add(_numberOfPeopleInClass.multiply(flightToCalculate_.getSeatCostPerSectionAtIndex(i)));
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
}
