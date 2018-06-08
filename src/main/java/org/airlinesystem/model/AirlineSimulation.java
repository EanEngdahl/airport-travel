/*
 * AirlineSimulation class
 *		Runs a full simulation with calculations and
 *		has a graph of the airports with a list of flights
 */

package org.airlinesystem.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.airlinesystem.controller.AirportGraph;

public class AirlineSimulation {


	private FlightList listOfFlights = new FlightList();
	private AirportGraph graphOfAirports = new AirportGraph();
	private Properties simulationProperties;
	private BigDecimal totalCost;
	private BigDecimal totalRevenue;
	private BigDecimal totalProfit;


	public AirlineSimulation() {}

	public FlightList getListOfFlights() {
		return listOfFlights;
	}
	public void setListOfFlights(FlightList listOfFlights) {
		this.listOfFlights = listOfFlights;
	}
	public AirportGraph getGraphOfAirports() {
		return graphOfAirports;
	}
	public void setGraphOfAirports(AirportGraph graphOfAirports) {
		this.graphOfAirports = graphOfAirports;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	public Properties getSimulationProperties() {
		return simulationProperties;
	}

	public void setSimulationProperties(Properties simulationProperties) {
		this.simulationProperties = simulationProperties;
	}

}
