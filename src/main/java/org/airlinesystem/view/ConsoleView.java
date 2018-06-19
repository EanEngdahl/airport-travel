/**
 * ConsoleView Class
 * 		Contains displays that are shown to the user
 * 		when necessary
 */

package org.airlinesystem.view;

import java.util.Scanner;
import java.math.BigDecimal;
import java.text.NumberFormat;

import org.airlinesystem.controllers.logging.FullLogging;

public class ConsoleView {
	
	private FullLogging viewLog = FullLogging.getInstance();

	/**
	 * Prompts a user to choose to input either a new properties or graph file
	 * uses default files if nothing is entered
	 * 
	 * @param output_
	 * 		logger for if any errors occur
	 * @param input_
	 * 		scanner for reading user input
	 * @return
	 * 		String array containing input file names
	 */
	public String[] promptUserForFileNames(Scanner input_) {
		String _fileNames[] = new String [2];
		String _selection;

		try {
			do {
				viewLog.menuInfo("\nInput custom file paths. If none are input, use defaults.\n");
				viewLog.menuInfo("1. Enter custom properties file path\n"
						+ "2. Enter custom graph file path\n"
						+ "3. Return to Main Menu\n\n");
			
				_selection = input_.nextLine();
				
				switch(_selection) {
					case "1":
					case "2":
						viewLog.menuInfo("Input file path: ");
						_fileNames[Integer.parseInt(_selection) - 1] = input_.nextLine();
						break;
					case "3":
						viewLog.menuInfo("\nReturning to main menu\n");
						break;
					default:
						viewLog.menuInfo("Input a valid option\n");
				}

			} while(!_selection.equals("3"));

		} catch(Exception e_) {
			viewLog.menuError("Prompt input error\n");
		}
		return _fileNames;
	}	

	/**
	 * Displays main menu where all other options can be accessed from
	 * 
	 * @param output_
	 * 		logger for if any errors occur
	 * @param input_
	 * 		scanner for reading user input
	 * @return
	 * 		integer representing user choice
	 */
	public int showMainMenu(Scanner input_) {
		String _selection;
		
		viewLog.menuInfo("\nMAIN MENU:\n");
		viewLog.menuInfo("1. Input custom files\n"
				+	 "2. Run simulation\n"
				+	 "3. Show results\n"
				+	 "4. Find average profit between airports\n"
				+	 "5. Read flights from file and simulate\n"
				+	 "6. Display graph\n"
				+	 "0. Quit\n\n");

		try {
			for(;;) {
				_selection = input_.nextLine();

				switch(_selection) {
						case "0":
							return 0;
						case "1":
							return 1;
						case "2":
							return 2;
						case "3":
							return 3;
						case "4":
							return 4;
						case "5":
							return 5;
						case "6":
							return 6;
						default:
							viewLog.menuInfo("Input a valid option\n");
							break;
				}
			}
		} catch(Exception e_) {
			viewLog.menuError("Menu input error\n");
			return 0;
		}
	}
	
	/**
	 * Prompts a user to choose to input two airport names
	 * to be used for finding average profit for flights between them
	 * 
	 * @param input_
	 * 		scanner for reading user input
	 * @return
	 * 		String array containing input airport names
	 */
	public String[] findAverageBetweenAirports(Scanner input_) {
		String[] _airportNames = new String[2];
		
		viewLog.menuInfo("Input first airport name: ");
		_airportNames[0] = input_.nextLine().toUpperCase();
		viewLog.menuInfo("Input second airport name: ");
		_airportNames[1] = input_.nextLine().toUpperCase();
		
		return _airportNames;
	}
	
	/**
	 * Prompts a user to choose to input either a new data file
	 * to read from
	 * 
	 * @param input_
	 * 		scanner for reading user input
	 * @return
	 * 		String of the data file name input
	 */
	public String promptForDataFile(Scanner input_) {
		viewLog.menuInfo("Input data file to read: ");
		return input_.nextLine();
	}
	
	/**
	 * Displays the average profit found between two airports
	 * that were entered by the user
	 * 
	 * @param averageProfit_
	 * 		BigDecimal that represents the average profit of flights
	 * 		on the requested edge, will be shown to user
	 * @return
	 * 		N/A
	 */
	public void displayAverageBetweenAirports(BigDecimal averageProfit_) {
		NumberFormat _numberFormatter = NumberFormat.getInstance();
		viewLog.menuInfo("The average profit is $" + _numberFormatter.format(averageProfit_) + "\n\n");
	}
	
	/**
	 * Displays general results found from used data
	 * 
	 * @param output_
	 * 		logger for if any errors occur
	 * @param profit_
	 * 		BigDecimal representation of overall profit of the flight list
	 * @param cost_
	 * 		BigDecimal representation of overall cost of the flight list
	 * @param revenue_
	 * 		BigDecimal representation of overall revenue of the flight list
	 * @param totalFlights_
	 * 		Integer representation of total number of flights in flight list
	 * @param averageFlightProfit_
	 * 		BigDecimal representation of the average flight's profit
	 * @return
	 * 		N/A
	 */
	public void resultsView(BigDecimal profit_, BigDecimal cost_, 
			BigDecimal revenue_, int totalFlights_, BigDecimal averageFlightProfit_) {
		NumberFormat _numberFormatter = NumberFormat.getInstance();
		viewLog.menuInfo("\n\n*****Flight Results*****\n\n");
		try {
		viewLog.menuInfo("Total number of flights: " + _numberFormatter.format(totalFlights_)
				+ "\nTotal revenue: " + _numberFormatter.format(revenue_) 
				+ "\nTotal cost: " +  _numberFormatter.format(cost_)
				+ "\nTotal Profit: " + _numberFormatter.format(profit_)
				+ "\nAverage Profit: " + _numberFormatter.format(averageFlightProfit_) + "\n");
		}
		catch(Exception e_) {
			viewLog.menuError("Number formatting error: " + e_.getStackTrace());
		}
	}
}
