# General

- When working with default properties include it in some constant class so it is easier to update when needed
 
```
	java
	 
	File _dataFile = new File(System.getProperty("user.dir") + "/airlinesystem-defaults/default-data");
	
	 # move default to constant class 
	 
	public class AirlineSystemConstants {
	 
		public static final String AIRLINESYSTEM_DEFAULT_DATA = "/airlinesystem-defaults/default-data";
	 	...
	}
	
	# And use it like
	
	
	File _dataFile = new File(System.getProperty("user.dir") + AirlineSystemConstants.AIRLINESYSTEM_DEFAULT_DATA;
 
```

- If there are default files needed for system to work as it should, add those files into project. 
- Because java can be run from different OS, create file path that is not one OS based.

```
	java

	# This can be updated

	File file = new File("/path/to/file.properties");
	
	
	# so it is not OS based
	
	
	import java.nio.file.Path;
	import java.nio.file.Paths;
	
	Path pathToFile = Paths.get("path", "to", "file.txt");
	File file = pathToFile.toFile();
```
