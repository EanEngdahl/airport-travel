package airportsysmodel;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

public class CommandHandler {

	public CommandHandler(String fname_) throws IOException {
		Logger debugLogger = LoggerFactory.getLogger("debugLogger");
		Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
		debugLogger.debug("Command Handler");
		FlightList _listOfFlights = new FlightList();
		ReadPSVIntoState _input = new ReadPSVIntoState();
		
		_input.ReadFileInputIntoFlightList(_listOfFlights);
		
		BigDecimal _profitSum = new BigDecimal("0");
		BigDecimal _singleFlightProfit = new BigDecimal("0");
		FlightRCPManager flightProfitManager = new FlightRCPManager();
		for (Flight i : _listOfFlights) {
			_singleFlightProfit = flightProfitManager.findProfit(i);
			_profitSum = _profitSum.add(_singleFlightProfit);
			resultsLogger.info("Individual flight profit = $" + _singleFlightProfit.toString());
		}
		resultsLogger.info("Total profit = $" + _profitSum.toString());
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
