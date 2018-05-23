package airlinesystemcontroller;

import java.util.Properties;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RuntimePropertyController {

	public Properties loadDefaultProperties() {
		Properties _defaultProperties = new Properties();

		Logger _debugLogger = LoggerFactory.getLogger("debugLogger");

		try (FileInputStream _readIn = new FileInputStream("default.properties")) {
			_defaultProperties.load(_readIn);
		} catch(FileNotFoundException e_){
			_debugLogger.error("Could not find default.properties file" + e_.getMessage());
		} catch(IOException e_) {
			_debugLogger.error("Error reading default.properties file" + e_.getMessage());
		}
		
		return _defaultProperties;
	}
	
	public Properties createRuntimeProperties(String fuelCost_, String numberOfFlights_,
			String firstPrice_, String businessPrice_, String econPlusPrice_,
			String econBasicPrice_) {
		Properties _runtimeProperties = new Properties();
		
		_runtimeProperties.setProperty("FUEL_COST", fuelCost_);
		_runtimeProperties.setProperty("NUM_FLIGHTS", numberOfFlights_);
		_runtimeProperties.setProperty("FIRST_CLASS_SEAT_PRICE", firstPrice_);
		_runtimeProperties.setProperty("BUSINESS_CLASS_SEAT_PRICE", businessPrice_);
		_runtimeProperties.setProperty("ECON_PLUS_SEAT_PRICE", econPlusPrice_);
		_runtimeProperties.setProperty("ECON_BASIC_SEAT_PRICE", econBasicPrice_);

		return _runtimeProperties;
	}
	
	
	public Properties loadRuntimeProperties(String[] args_) {
		Properties returnProperties;
		
		if( args_.equals(null)) {
			returnProperties = loadDefaultProperties();
		} else {
			returnProperties = createRuntimeProperties(args_[0], args_[1], args_[2],
					args_[3], args_[4], args_[5]);
		}
		
		return returnProperties;
	}
}

