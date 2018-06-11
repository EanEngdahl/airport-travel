# General

- controllers and other classes should be separated. 
- move graph/database related logic to its own package. Good practice is also to have separate interface from implementation.
Reason is maybe at the moment system relies on custom implementation, but in future it might be required to use e.g. Neo4j.

```java
	package org.airlinesystem.graphdb;
	
	public interface Graph {
		void addAirport(Airport airport);
	}
```

Implementation example:

```java
	
	package org.airlinesystem.graphdb.impl;
	
	...
	import org.airlinesystem.graphdb.Graph;
	...
	
	public class CustomGraphDb implements Graph {
	
		public void addAirport(Airport airport) {
			// TODO logic goes here
		}
	}
```

- Methods should be documented properly, as in example.

```java
	
	/**
	 * This is dummy class.
	 */
	public class SomeClass {
	
		/**
		 * Gets info.
		 * @param input 
		 * @returns String calculated value
		 */
		public String getMeInfo(String input) {
		}
	}
``` 


# Testing

- Tests should be placed in corresponding packages after moving testing classes to packages.
