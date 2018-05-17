package airportsysmodel;

import java.math.BigDecimal;

public class FlightRCPManager {
	private static final BigDecimal FUELCOST = new BigDecimal("10");
	private BigDecimal revenue;
	private BigDecimal cost;
	private BigDecimal profit;
	
	public FlightRCPManager(Flight flightToCalculate_) {
		findProfit(flightToCalculate_);
	}
	
	public BigDecimal findRevenue(Flight flightToCalculate_) {
		/*TODO get seat class information from flight*/
		revenue = new BigDecimal("0");
		return revenue;
	}
	
	public BigDecimal findCost(Flight flightToCalculate_) {
		/*TODO Get edge weight for finding cost*/
		cost = new BigDecimal("0");
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
