package org.airlinesystem.helpers;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultsLoader {

	public File createNewDefaultDirectoryInUserFilesystem() {
		File _outputDirectory = new File(System.getProperty("user.dir") + "/airlinesystem-defaults");
		_outputDirectory.mkdir();
		return _outputDirectory;
	}
	
	public void createDefaultPropertiesInUserFilesystem(File dirFile_) throws IOException {
		InputStream _in = this.getClass().getResourceAsStream("/default.properties");
		
		File _propFile = new File(dirFile_.getAbsolutePath() + "/default.properties");
		
		Path _outPath = _propFile.toPath();
		System.out.println("The path thingy does: " + _outPath.toString());
		Files.copy(_in, _outPath);
		_in.close();
	}
	
	public void createDefaultGraphInUserFilesystem(File dirFile_) throws IOException {
		InputStream _in = this.getClass().getResourceAsStream("/default-graph");
		
		File _graphFile = new File(dirFile_.getAbsolutePath() + "/default-graph");
		
		Path _outPath = _graphFile.toPath();
		Files.copy(_in, _outPath);	
		_in.close();
	}
	
	public void createExampleDataFileInUserFilesystem(File dirFile_) throws IOException {
		InputStream _in = this.getClass().getResourceAsStream("/default-data");
		
		File _dataFile = new File(dirFile_.getAbsolutePath() + "/default-data");
		
		Path _outPath = _dataFile.toPath();
		Files.copy(_in, _outPath);
		_in.close();
	}
	
	public void createDefaultsInUserFilesystem() throws IOException {
		File _outputDirectory = createNewDefaultDirectoryInUserFilesystem();
		createDefaultPropertiesInUserFilesystem(_outputDirectory);
		createDefaultGraphInUserFilesystem(_outputDirectory);
		createExampleDataFileInUserFilesystem(_outputDirectory);
	}
}
