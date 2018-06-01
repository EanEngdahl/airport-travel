/*
 * ConsoleView Class
 * 		Contains displays that are shown to the user
 * 		when necessary
 */

package airlinesystemview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.math.BigDecimal;
import java.text.NumberFormat;


public class ConsoleView {
	
	private Logger menuLogger = LoggerFactory.getLogger("menuLogger");

	/*
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
	public String[] promptUserForFileNames(Logger output_, Scanner input_) {
		String _fileNames[] = new String [2];
		String _selection;

		try {
			do {
				menuLogger.info("Input custom file paths. If none are input, use defaults.\n");
				menuLogger.info("1. Enter custom properties file path\n"
						+ "2. Enter custom graph file path\n"
						+ "3. Return to Main Menu\n\n");
			
				_selection = input_.nextLine();
				
				switch(_selection) {
					case "1":
					case "2":
						menuLogger.info("Input file path: ");
						_fileNames[Integer.parseInt(_selection) - 1] = input_.nextLine();
						break;
					case "3":
						menuLogger.info("Running program\n");
						break;
					default:
						menuLogger.info("Input a valid option\n\n");
				}

			} while(!_selection.equals("3"));

		} catch(Exception e_) {
			output_.error("Prompt input error");
		}
		
		return _fileNames;
	}	

	/*
	 * Displays main menu where all other options can be accessed from
	 * 
	 * @param output_
	 * 		logger for if any errors occur
	 * @param input_
	 * 		scanner for reading user input
	 * @return
	 * 		integer representing user choice
	 */
	public int showMainMenu(Logger output_, Scanner input_) {
		String _selection;
		
		menuLogger.info("MAIN MENU:\n");
		menuLogger.info("1. Input custom files\n"
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
							menuLogger.info("Input a valid option\n");
							break;
				}
			}
		} catch(Exception e_) {
			output_.error("Menu input error");
			return 0;
		}
	}
	
	/*
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
		
		menuLogger.info("Input first airport name: ");
		_airportNames[0] = input_.nextLine().toUpperCase();
		menuLogger.info("Input second airport name: ");
		_airportNames[1] = input_.nextLine().toUpperCase();
		
		return _airportNames;
	}
	
	/*
	 * Prompts a user to choose to input either a new data file
	 * to read from
	 * 
	 * @param input_
	 * 		scanner for reading user input
	 * @return
	 * 		String of the data file name input
	 */
	public String promptForDataFile(Scanner input_) {
		menuLogger.info("Input data file to read: ");
		return input_.nextLine();
	}
	
	/*
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
		menuLogger.info("The average profit is $" + _numberFormatter.format(averageProfit_) + "\n\n");
	}
	
	/*
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
	public void resultsView(Logger output_, BigDecimal profit_, BigDecimal cost_, 
			BigDecimal revenue_, int totalFlights_, BigDecimal averageFlightProfit_) {
		NumberFormat _numberFormatter = NumberFormat.getInstance();
		menuLogger.info("\n\n*****Flight Results*****\n\n");
		menuLogger.info("Total number of flights: " + _numberFormatter.format(totalFlights_)
				+ "\nTotal revenue: " + _numberFormatter.format(revenue_)
				+ "\nTotal cost: " + _numberFormatter.format(cost_)
				+ "\nTotal Profit: " + _numberFormatter.format(profit_) 
				+ "\nAverage Profit: " + _numberFormatter.format(averageFlightProfit_) + "\n\n");
	}
}
