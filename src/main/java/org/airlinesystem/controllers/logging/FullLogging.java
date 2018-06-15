package org.airlinesystem.controllers.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullLogging {

	private static FullLogging instance;
	
	private Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
	private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	private Logger debugLogger = LoggerFactory.getLogger("debugLogger");
	private Logger menuLogger = LoggerFactory.getLogger("menuLogger");
	
	public FullLogging() {
		//TODO add here if needed
	}
	
	private void init() {
		//TODO Not sure if below needed at all
		resultsLogger = LoggerFactory.getLogger("resultsLogger");
		consoleLogger = LoggerFactory.getLogger("consoleLogger");
		debugLogger = LoggerFactory.getLogger("debugLogger");
		menuLogger = LoggerFactory.getLogger("menuLogger");
	}
	
	public static FullLogging getInstance() {
		if (instance == null) {
			instance = new FullLogging();
			instance.init();
		}
		return instance;
	}
	
	public void menuInfo(String message_) {
		menuLogger.info(message_);
	}
	
	public void consoleError(String message_) {
		consoleLogger.error(message_);
	}
	
	public void debugDebug(String message_) {
		debugLogger.debug(message_);
	}
	
	public void resultsInfo(String message_) {
		resultsLogger.info(message_);
	}
}
