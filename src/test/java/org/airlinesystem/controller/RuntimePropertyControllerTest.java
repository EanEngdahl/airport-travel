package org.airlinesystem.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import org.airlinesystem.controller.RuntimePropertyController;
import java.util.Properties;
import java.io.IOException;
import java.io.File;

public class RuntimePropertyControllerTest {

	@Test
	public void testLoadDefaultProperties() {
		Properties defaultTest; 
		RuntimePropertyController propController = new RuntimePropertyController();
		
		defaultTest = propController.loadDefaultProperties();
		
		// Check a few of the default file properties to assure they are loaded correctly
		assertEquals("15", defaultTest.getProperty("FUEL_COST"));
		assertEquals("100", defaultTest.getProperty("NUMBER_OF_FLIGHTS"));
		assertEquals("150|100|100|50", defaultTest.getProperty("LARGE_PLANE_SEAT_MAX_PER_SECTION"));
	}
	
	@Test
	public void testCreateRuntimeProperiesWithValidFile() {
		Properties createTest_;
		RuntimePropertyController propController_ = new RuntimePropertyController();
		File _file = new File("src/test/resources/loadTest.properties");
		
		createTest_ = propController_.loadRuntimeProperties(_file.getAbsolutePath());

		// Check a few of the default file properties to assure they are loaded correctly
		assertEquals("15", createTest_.getProperty("FUEL_COST"));
		assertEquals("100", createTest_.getProperty("NUMBER_OF_FLIGHTS"));
		assertEquals("150|100|100|50", createTest_.getProperty("LARGE_PLANE_SEAT_MAX_PER_SECTION"));
		
	}

	@Test
	public void testCreateRuntimePropertiesWithInvalidFile() throws IOException, NullPointerException {
		RuntimePropertyController propController_ = new RuntimePropertyController();
		Properties createTest_;

		// Check that it handles the exceptions
		createTest_ = propController_.createRuntimeProperties("this is not a file");
		
		// Check that it loaded the default
		assertEquals("15", createTest_.getProperty("FUEL_COST"));
		assertEquals("100", createTest_.getProperty("NUMBER_OF_FLIGHTS"));
		assertEquals("150|100|100|50", createTest_.getProperty("LARGE_PLANE_SEAT_MAX_PER_SECTION"));
	}

	@Test
	public void testLoadRuntimeProperties() {
		Properties loadTest_;
		RuntimePropertyController propController_ = new RuntimePropertyController();
		File _file = new File("src/test/resources/loadTest.properties");
		
		loadTest_ = propController_.loadRuntimeProperties(_file.getAbsolutePath());

		// Check a few of the default file properties to assure they are loaded correctly
		assertEquals("15", loadTest_.getProperty("FUEL_COST"));
		assertEquals("100", loadTest_.getProperty("NUMBER_OF_FLIGHTS"));
		assertEquals("150|100|100|50", loadTest_.getProperty("LARGE_PLANE_SEAT_MAX_PER_SECTION"));
		
	}
}
