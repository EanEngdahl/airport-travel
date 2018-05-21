package airportsysmodel;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

public class CommandHandler {

	public CommandHandler(String fname_) throws IOException {
		Logger logger = LoggerFactory.getLogger(CommandHandler.class);
		logger.info("Command Handler");
		FlightList _listOfFlights = new FlightList();
		ReadPSVIntoState _input = new ReadPSVIntoState();
		
		_input.ReadFileInputIntoFlightList(_listOfFlights);
		logger.debug("Successfully read file");
		
		BigDecimal _profitSum = new BigDecimal("0");
		FlightRCPManager flightProfit = new FlightRCPManager();
		for (Flight i : _listOfFlights) {
			_profitSum = _profitSum.add(flightProfit.findProfit(i));
		}
		logger.info("Total profit" + _profitSum.toString());
	}
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(CommandHandler.class);
        try {
            new CommandHandler("/default.csv");
        }
        catch (IOException e_) {
        	logger.error("IOException" + e_.getMessage());
        }
    }
}
