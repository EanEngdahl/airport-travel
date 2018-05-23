package airlinesystemcontroller;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RuntimePropertyController {

	public Properties loadDefaultProperties() {
		Properties _defaultProperties = new Properties();

		Logger _consoleLogger = LoggerFactory.getLogger("consoleLogger");


		try (InputStream _is = RuntimePropertyController.class.getResourceAsStream("/default.properties")) {
			_defaultProperties.load(_is);
			_consoleLogger.debug("defaults loaded...");
		} catch(FileNotFoundException e_){
			_consoleLogger.error("Could not find default.properties file" + e_.getMessage());
		} catch(IOException e_) {
			_consoleLogger.error("Error reading default.properties file" + e_.getMessage());
		}
		
		return _defaultProperties;
	}
	
	public Properties createRuntimeProperties(String fileName_) {
		Properties _defaultProperties = new Properties();

		Logger _consoleLogger = LoggerFactory.getLogger("consoleLogger");

		try (InputStream _is = RuntimePropertyController.class.getResourceAsStream(fileName_)) {
			_defaultProperties.load(_is);
		} catch(FileNotFoundException e_){
			_consoleLogger.error("Could not find" + fileName_ + "file" + e_.getMessage());
		} catch(IOException e_) {
			_consoleLogger.error("Error reading" + fileName_ + "file" + e_.getMessage());
		}
		
		return _defaultProperties;
	}
	
	
	public Properties loadRuntimeProperties(String[] args_) {
		Properties _returnProperties;

		if(args_.length != 1) {
			_returnProperties = loadDefaultProperties();
		} else {
			_returnProperties = createRuntimeProperties(args_[0]);
		}
		
		return _returnProperties;
	}
}

