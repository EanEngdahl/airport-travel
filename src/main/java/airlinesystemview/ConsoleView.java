package airlinesystemview;

import org.slf4j.Logger;
import java.util.Scanner;
import java.math.BigDecimal;


public class ConsoleView {
	
	public String[] promptUserForFilenames(Logger output_, Scanner input_) {
		String _fileNames[] = new String [2];
		int _selection;

		try {
			do {
				System.out.println("Input custom file paths. If none are input, use defaults.");
				System.out.println("1. Enter custom properties file path\n"
						+ "2. Enter custom graph file path\n"
						+ "3. Return to Main Menu");
			
				_selection = input_.nextInt();
				
				// Remove newline from next input
				input_.nextLine();
				
				switch(_selection) {
					case 1:
					case 2:
						System.out.print("Input file path: ");
						_fileNames[_selection - 1] = input_.nextLine();
						break;
					case 3:
						System.out.println("Running program");
						break;
					default:
						System.out.println("Input a valid option");
						break; 
				}

			} while(_selection != 3);

		} catch(Exception e_) {
			output_.error("Prompt input error");
		}
		
		return _fileNames;
	}	

	public int showMainMenu(Logger output_, Scanner input_) {
		int _selection;
		
		System.out.println("MAIN MENU:");
		System.out.println("1. Input custom files\n"
				+	 "2. Run simulation\n"
				+	 "3. Show results\n"
				+	 "4. Find average profit between airports\n"
				+	 "0. Quit\n");

		try {
		
			for(;;) {
				_selection = input_.nextInt();

				switch(_selection) {
						case 0:
							return 0;
						case 1:
							return 1;
						case 2:
							return 2;
						case 3:
							return 3;
						case 4:
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
		input_.nextLine();
		_airportNames[0] = input_.nextLine().toUpperCase();
		System.out.print("Input second airport name: ");
		_airportNames[1] = input_.nextLine().toUpperCase();
		
		return _airportNames;
	}
	
	public void displayAverageBetweenAirports(BigDecimal averageProfit_) {
		System.out.println("The average profit is $" + averageProfit_.toString());
	}
	
	public void resultsView(Logger output_, BigDecimal profit_, BigDecimal cost_, 
			BigDecimal revenue_, int totalFlights_) {
		System.out.println("\n\n*****Flight Results*****");
		System.out.println("Total number of flights: " + Integer.toString(totalFlights_)
				+ "\nTotal revenue: " + revenue_.toString()
				+ "\nTotal cost: " + cost_.toString()
				+ "\nTotal Profit: " + profit_.toString() + "\n\n");
	}
}
