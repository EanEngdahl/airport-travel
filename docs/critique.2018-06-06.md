# NOTES

## General

- Package naming should follow java convention, e.g. `org.airlinesystem.controllers`.
- Please use proper way to document classes and methods, e.g. 

```java
	/**
	 * Returns logic calculate string
	 * @return String value represents something.
	 */
	public String getString() {
		String retVal = ...
		// TODO some logic
		return retVal;
	}
```
- It is good practice to place main class to root of package, e.g.  to `org.airlinesystem`.

## Tests

- When creating unit tests, follow methods logic. If handling exception is hidden from methods usage, 
e.g. load default properties if runtime properties loading fails, add tests as in example below:


```java
	public class RuntimePropertyControllerTest {
		
		public void testCreateRuntimePropertiesWithSuccess() {
			// TODO expecting successful result when loading properties in runtime
		}
		
		public void testCreateRuntimePropertiesLoadsDefaultValueIfPropertiesIsNotValid() {
			// TODO load invalid properties file with value 4
			// assert equal if default is expected with value 3
		}
	}
```