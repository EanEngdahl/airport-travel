package airlinesystemview;

import org.slf4j.Logger;
import java.util.Scanner;
import java.math.BigDecimal;
import java.text.NumberFormat;


public class ConsoleView {
	
	public String[] promptUserForFilenames(Logger output_, Scanner input_) {
		String _fileNames[] = new String [2];
		String _selection;

		try {
			do {
				System.out.println("Input custom file paths. If none are input, use defaults.");
				System.out.println("1. Enter custom properties file path\n"
						+ "2. Enter custom graph file path\n"
						+ "3. Return to Main Menu\n");
			
				_selection = input_.nextLine();
				
				switch(_selection) {
					case "1":
					case "2":
						System.out.print("Input file path: ");
						_fileNames[Integer.parseInt(_selection) - 1] = input_.nextLine();
						break;
					case "3":
						System.out.println("Running program");
						break;
					default:
						System.out.println("Input a valid option\n");
				}

			} while(!_selection.equals("3"));

		} catch(Exception e_) {
			output_.error("Prompt input error");
		}
		
		return _fileNames;
	}	

	public int showMainMenu(Logger output_, Scanner input_) {
		String _selection;
		
		System.out.println("MAIN MENU:");
		System.out.println("1. Input custom files\n"
				+	 "2. Run simulation\n"
				+	 "3. Show results\n"
				+	 "4. Find average profit between airports\n"
				+	 "0. Quit\n");

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
						default:
							System.out.println("Input a valid option");
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
		
		System.out.print("Input first airport name: ");
		_airportNames[0] = input_.nextLine().toUpperCase();
		System.out.print("Input second airport name: ");
		_airportNames[1] = input_.nextLine().toUpperCase();
		
		return _airportNames;
	}
	
	public void displayAverageBetweenAirports(BigDecimal averageProfit_) {
		NumberFormat _numberFormatter = NumberFormat.getInstance();
		System.out.println("The average profit is $" + _numberFormatter.format(averageProfit_) + "\n");
	}
	
	public void resultsView(Logger output_, BigDecimal profit_, BigDecimal cost_, 
			BigDecimal revenue_, int totalFlights_, BigDecimal averageFlightProfit_) {
		NumberFormat _numberFormatter = NumberFormat.getInstance();
		System.out.println("\n\n*****Flight Results*****");
		System.out.println("Total number of flights: " + _numberFormatter.format(totalFlights_)
				+ "\nTotal revenue: " + _numberFormatter.format(revenue_)
				+ "\nTotal cost: " + _numberFormatter.format(cost_)
				+ "\nTotal Profit: " + _numberFormatter.format(profit_) 
				+ "\nAverage Profit: " + _numberFormatter.format(averageFlightProfit_) + "\n\n");
	}
}
