package airlinesystemcontroller;

import java.util.Properties;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public class RuntimePropertyController {

	public Properties loadDefaultProperties() {
		Properties _defaultProperties = new Properties();
		
		try (FileInputStream _readIn = new FileInputStream("default.properties")) {
			_defaultProperties.load(_readIn);
		} catch(FileNotFoundException e_){
		} catch(IOException e_) {
		}
		
		return _defaultProperties;
	}
	
	public Properties createRuntimeProperties(BigDecimal fuelCost_, int numberOfFlights_, BigDecimal costPerSeat_[]) {
		Properties _runtimeProperties = new Properties();
		
		_runtimeProperties.setProperty("FUEL_COST", fuelCost_.toString());
		_runtimeProperties.setProperty("NUM_FLIGHTS", Integer.toString(numberOfFlights_));
		_runtimeProperties.setProperty("COST_PER_SEAT", Arrays.stream(costPerSeat_).iterator().toString());

		return _runtimeProperties;
	}
	
}

