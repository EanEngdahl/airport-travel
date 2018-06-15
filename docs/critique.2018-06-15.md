# General

- There should be one class containing all loggers instances that provides methods for logging. Example:

```java
	public class OurLogger {
		private static OutLogger instance;
		
		private Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
		private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
		private Logger debugLogger = LoggerFactory.getLogger("debugLogger");
		
		private void init() {
			// TODO init all loggers if required
		}
		
		public static getInstance() {
			if (instance == null) {
				
				instance = OurLogger();
				instance.init();
			}
			return instance;
		}
		
		
		public void debug(String message) {
			debugLogger.debug(message);
		}
		
		// TODO add more methods if required
	}
```

This way if logging mechanism changes, there should be new implementation of this class or interface if required.

- **Enums** should be documented. It is also practice to place enum to same class where it is used, e.g. AircraftSize is might be better to place it in Aircraft class, as it is related to it, and not something generally used. 

- Declare and initialize variable in same line if possible, e.g. instead

```java
Flight _addedFlight;
_addedFligt = ...
```
use

```java
Flight _addedFlight = ....
```

- When adding new graph edge, if Exception occurs, propagate it to caller. If there is method in controller called `public boolean addBook(Book book)` and book must add its author, where there is method called `public void addAuthor(Author author)` and client app calls `addBook`, but `addAuthor` throws exception if `Author` is not valid, `addBook` method should propagate exception to client.


```java
	public class BookCtrl throws BookCreationFailed {
		
		// init AuthorCtrl
		AuthorCtrl authorCtrl;
		
		
		public boolean addBook(Book book) {
			boolean retVal = false;
			
			...
			// add author
			try {
				authorCtrl.addAuthor(book.getAuthor());
			}
			catch(AuthorNotValid e) {
				throw new BookCreationFailed(e);
			}	
			...
			
			return retVal;
		}
	}
	
	public class AuthorCtrl {
		public boolean adAuthor(Author author) {
			...
			// something not valid, throw exception
			throw new AuthorNotValidException(message);
		}
	}
	
	
	public class Client {
		public static void main(String[] args) {
			// call book
			Book book = new Book();
			book.setTitle("the catcher in the rye");
			
			// add author
			try {
				bookCtrl.addBook(book);
			}
			catch(BookCreationFailed e) {
				// notify user of console app
				e.printStackTrace();
			}
		}
	}

```

- Add custom exception that will wrap all other exceptions. Methods should declare that they throw lets say `AirlineSystemException` instead of multiple exceptions like `NullPointerException`, or `ArithmeticException`. This will also help better tracking where exception occurred and exception propagation path should be easier to follow.

Number of exception classes should be put on minimal, with messages describing them. If there is need that code should throw some custom exception like validation exception then there can be e.g. `AirlineSystemPathValidationException` that can be thrown when there is no path between airports but it was expected for some reason (this is for sake of example).


## Console view controller

- When used with filename list parameter, method expects that properties file is first, and graph properties file second list.

This is not good practice. If these files are only ones that are required by method in this moment, there should be variable per file.

```java

	public void showMenus(File propertiesFile, File graphFile) {
		...
		// TODO add logic
		...
	}
```
**All similar usages should be updated correspondingly.**

- If there is finite set of options for console, there should be enum instead of numbers, e.g. `Option.ENTER_PROPERTY_FILES` if user presses key `1`.


## Models

- It might be possible that some models require validation before usage. E.g. `Aircraft` might require size, sections list and maximum number of seats.

# Testing

- If resource is used without change in mutliple tests, move initialization and loading to method annotated with `@BeforeClass`, e.g. `FlightRCPControllerTest.java`.

