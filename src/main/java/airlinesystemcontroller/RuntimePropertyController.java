/*
 * RuntimePropertyController class
 *  	Manages the loading of the properties file that are specific
 *  	to this program.
 */
package airlinesystemcontroller;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuntimePropertyController {

	/*
	 * Loads the default.properties file and throws exceptions
	 * if it is unable to.
	 * 
	 * @return the Properties object with the defaults loaded
	 */
	public Properties loadDefaultProperties() {
		Properties _defaultProperties = new Properties();

		Logger _debugLogger = LoggerFactory.getLogger("debugLogger");
		Logger _consoleLogger = LoggerFactory.getLogger("consoleLogger");

		try (InputStream _is = RuntimePropertyController.class.getResourceAsStream("/default.properties")) {
			_defaultProperties.load(_is);
			_debugLogger.debug("defaults loaded...");
		} catch(NullPointerException e_){
			_consoleLogger.error("Could not find default.properties file");
		} catch(IOException e_) {
			_consoleLogger.error("Error reading default.properties file");
		} catch (Exception e_) {
			e_.printStackTrace();
		}	
		
		return _defaultProperties;
	}

	/*
	 *  Loads in the runtime properties for a specified properties file and
	 *  reverts to default if it is not readable
	 *  
	 *  @param the string filename/path of the custom properties file
	 *  @return the loaded properties file, default or custom
	 */
	public Properties createRuntimeProperties(String fileName_) {
		Properties _defaultProperties = new Properties();

		Logger _consoleLogger = LoggerFactory.getLogger("consoleLogger");

		try (InputStream _is = RuntimePropertyController.class.getResourceAsStream(fileName_)) {
			_defaultProperties.load(_is);
		} catch(IOException e_){
			_consoleLogger.error("Unable to use " + fileName_ + ", reverting to default properties");
			_defaultProperties = loadDefaultProperties();
		} catch(NullPointerException e_) {
			_consoleLogger.error("Unable to use " + fileName_ + ", reverting to default properties");
			_defaultProperties = loadDefaultProperties();	
		}
		
		return _defaultProperties;
	}

	/*
	 *  Decides whether to load the defaults or use the custom properties 
	 *  file, then calls the appropriate method for loading.
	 *  
	 *  @param the string filename/path of the properties file
	 *  @return the loaded Properties object
	 */
	public Properties loadRuntimeProperties(String fileName_) {
		Properties _returnProperties;

		if(fileName_.equals("/default.properties")) {
			_returnProperties = loadDefaultProperties();
		} else {
			_returnProperties = createRuntimeProperties(fileName_);
		}
		return _returnProperties;
	}
}

