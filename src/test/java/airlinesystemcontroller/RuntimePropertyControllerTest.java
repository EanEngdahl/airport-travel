package airlinesystemcontroller;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Properties;

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
/*
	@Test
	public void testCreateRuntimeProperties() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testLoadRuntimeProperties() {
		Properties loadTest_;
		RuntimePropertyController propController_ = new RuntimePropertyController();
		
		loadTest_ = propController_.loadRuntimeProperties("/loadTest.properties");

		// Check a few of the default file properties to assure they are loaded correctly
		assertEquals("15", loadTest_.getProperty("FUEL_COST"));
		assertEquals("100", loadTest_.getProperty("NUMBER_OF_FLIGHTS"));
		assertEquals("150|100|100|50", loadTest_.getProperty("LARGE_PLANE_SEAT_MAX_PER_SECTION"));
	}
}
