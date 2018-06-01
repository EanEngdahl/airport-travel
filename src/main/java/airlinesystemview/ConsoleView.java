package airlinesystemview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.math.BigDecimal;
import java.text.NumberFormat;


public class ConsoleView {
	
	private Logger menuLogger = LoggerFactory.getLogger("menuLogger");

	public String[] promptUserForFilenames(Logger output_, Scanner input_) {
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
	
	public String[] findAverageBetweenAirports(Scanner input_) {
		String[] _airportNames = new String[2];
		
		menuLogger.info("Input first airport name: ");
		_airportNames[0] = input_.nextLine().toUpperCase();
		menuLogger.info("Input second airport name: ");
		_airportNames[1] = input_.nextLine().toUpperCase();
		
		return _airportNames;
	}
	
	public String promptForDataFile(Scanner input_) {
		menuLogger.info("Input data file to read: ");
		return input_.nextLine();
	}
	
	public void displayAverageBetweenAirports(BigDecimal averageProfit_) {
		NumberFormat _numberFormatter = NumberFormat.getInstance();
		menuLogger.info("The average profit is $" + _numberFormatter.format(averageProfit_) + "\n\n");
	}
	
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
