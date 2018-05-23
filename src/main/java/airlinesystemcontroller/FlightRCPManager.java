/*
 * FlightRCPManager class
 *		Calculates the revenue, cost, and overall profit
 *		for a given flight
 */

package airlinesystemcontroller;

import java.math.BigDecimal;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.Flight;
import airlinesystemmodel.FlightList;

public class FlightRCPManager {
	private static final BigDecimal FUELCOST = new BigDecimal("15");
	
	/*
	 * Default constructor
	 */
	public FlightRCPManager() {}
	
	/*
	 * Calculate and set revenue class variable
	 * 
	 * @param flightToCalculate_
	 * 		flight with information to use for finding revenue
	 * @return
	 * 		BigDecimal type revenue to be used for profit calculations
	 */
	public BigDecimal findRevenue(Flight flightToCalculate_) {
		BigDecimal _revenue = new BigDecimal("0");
		int seatsFilledPerSection_[] = flightToCalculate_.getSeatsFilledPerSection();
		BigDecimal seatCostPerSection_[] = flightToCalculate_.getSeatCostPerSection();
		
		for (int i = 0; i < flightToCalculate_.getSeatsFilledPerSection().length; i++) {
			BigDecimal _numberOfPeopleInClass = new BigDecimal(seatsFilledPerSection_[i]);
			_revenue = _revenue.add(_numberOfPeopleInClass.multiply(seatCostPerSection_[i]));
		}
		return _revenue;
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
		BigDecimal _cost = new BigDecimal(flightToCalculate_.getDistanceTravelled());
		_cost = _cost.multiply(FUELCOST);
		_cost = _cost.add(flightToCalculate_.getCoPilot().getCostPerFlight());
		_cost = _cost.add(flightToCalculate_.getPilot().getCostPerFlight());
		return _cost;
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
		BigDecimal _profit = new BigDecimal("0");
		_profit = _profit.add(findRevenue(flightToCalculate_));
		_profit = _profit.subtract(findCost(flightToCalculate_));
		return _profit;
	}
	
	public BigDecimal[] getRCPAsArray(Flight flightToCalculate_) {
		BigDecimal[] _flightRCPArray = new BigDecimal[3];
		_flightRCPArray[0] = findRevenue(flightToCalculate_);
		_flightRCPArray[1] = findCost(flightToCalculate_);
		_flightRCPArray[2] = _flightRCPArray[0].subtract(_flightRCPArray[1]);
		return _flightRCPArray;
	}
	
	public BigDecimal findTotalProfitOfFlightList (FlightList listOfFlights_) {
		Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
		Iterator<Flight> _flightListItr = listOfFlights_.iterator();
		BigDecimal _totalProfit = new BigDecimal("0");
		Flight _currentFlight;
		
		while (_flightListItr.hasNext()) {
			_currentFlight = _flightListItr.next();
			_totalProfit = _totalProfit.add(_currentFlight.getProfit());
			resultsLogger.info("Individual flight profit = $" + 
			_currentFlight.getProfit().toString());
		}
		return _totalProfit;
	}
	
	public static BigDecimal getFuelcost() {
		return FUELCOST;
	}
}
