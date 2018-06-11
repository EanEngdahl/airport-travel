/**
 * RuntimePropertyController class
 *  	Manages the loading of the properties file that are specific
 *  	to this program.
 */

package org.airlinesystem.controller;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuntimePropertyController {

	/**
	 * Loads the default.properties file and throws exceptions
	 * if it is unable to.
	 * 
	 * @return the Properties object with the defaults loaded
	 */
	public Properties loadDefaultProperties() {
		Properties _defaultProperties = new Properties();
		Logger _debugLogger = LoggerFactory.getLogger("debugLogger");
		Logger _consoleLogger = LoggerFactory.getLogger("consoleLogger");

		try (InputStream _is = this.getClass().getResourceAsStream("/default.properties")) {
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

	/**
	 *  Loads in the runtime properties for a specified properties file and
	 *  reverts to default if it is not readable
	 *  
	 *  @param the string filename/path of the custom properties file
	 *  @return the loaded properties file, default or custom
	 */
	public Properties createRuntimeProperties(File file_) {
		Properties _properties = new Properties();

		Logger _consoleLogger = LoggerFactory.getLogger("consoleLogger");

		try (InputStream _is = new FileInputStream(file_)) {
			_properties.load(_is);
		} catch(IOException e_){
			_properties = loadDefaultProperties();
			_consoleLogger.error("Unable to use {}, reverting to default properties. {}", file_, e_.getStackTrace());
		} catch(NullPointerException e_) {
			_consoleLogger.error("Unable to use {}, reverting to default properties. {}", file_, e_.getStackTrace());
			_properties = loadDefaultProperties();	
		}
		
		return _properties;
	}

	/**
	 *  Decides whether to load the defaults or use the custom properties 
	 *  file, then calls the appropriate method for loading.
	 *  
	 *  @param the string filename/path of the properties file
	 *  @return the loaded Properties object
	 */
	public Properties loadRuntimeProperties(File file_) {
		Properties _returnProperties;

		//TODO: This is probably broken
		if(file_.getName().matches("^.??default.properties")) {
			_returnProperties = loadDefaultProperties();
		} else {
			_returnProperties = createRuntimeProperties(file_);
		}
		return _returnProperties;
	}
}

