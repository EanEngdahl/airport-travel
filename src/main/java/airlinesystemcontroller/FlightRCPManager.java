/*
 * FlightRCPManager class
 *		Calculates the revenue, cost, and overall profit
 *		for a given flight
 */

package airlinesystemcontroller;

import java.math.BigDecimal;

import airlinesystemmodel.Flight;

public class FlightRCPManager {
	private static final BigDecimal FUELCOST = new BigDecimal("15");

	private BigDecimal revenue;
	private BigDecimal cost;
	private BigDecimal profit;
	
	/*
	 * Default constructor
	 */
	public FlightRCPManager() {}
	
	/*
	 * Constructor calls to find profit of a flight
	 * 
	 * @param flightToCalculate_
	 * 		flight to be passed and used for finding profit
	 */
	public FlightRCPManager(Flight flightToCalculate_) {
		findProfit(flightToCalculate_);
	}
	
	/*
	 * Calculate and set revenue class variable
	 * 
	 * @param flightToCalculate_
	 * 		flight with information to use for finding revenue
	 * @return
	 * 		BigDecimal type revenue to be used for profit calculations
	 */
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

	/*
	 * Calculate and set cost class variable
	 * 
	 * @param flightToCalculate_
	 * 		flight with information to use for finding cost
	 * @ return
	 * 		BigDecimal type cost to be used for profit calculations
	 */
	public BigDecimal findCost(Flight flightToCalculate_) {
		cost = new BigDecimal(flightToCalculate_.getDistanceTravelled());
		cost = cost.multiply(FUELCOST);
		cost = cost.add(flightToCalculate_.getCoPilot().getCostPerFlight());
		cost = cost.add(flightToCalculate_.getPilot().getCostPerFlight());
		return cost;
	}
	
	/*
	 * Calculate and set profit class variable
	 * 
	 * @param flightToCalculate_
	 * 		flight with information to use for finding profit
	 * @return
	 * 		BigDecimal type profit for the given flight
	 */
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
