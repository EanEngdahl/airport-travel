# General

- Aircraft size should be `Enum`, e.g.

```java
	public enum AircraftSize {
		S, 
		M,
		L		
	}
```

In case new airplane size is added, it can be done in single spot.

- If airplane has size, and pilot has seniority, map of relation between those two values would be better than using one for everything. It is confusing to have pilot seniroty in check for airplane size.

E.g.:

```java
	public AircraftPilot assignPilotToAircraft(char aircraftSize_) {
        AircraftPilot returnPilot;
        switch (aircraftSize_) {
            case SENIOR:
                returnPilot = new AircraftPilot();
                returnPilot.setSeniority(2);
                returnPilot.setCostPerFlight(_seniorCost);
                return returnPilot;
            case MIDLEVEL:
                returnPilot = new AircraftPilot();
                returnPilot.setSeniority(1);
                returnPilot.setCostPerFlight(_midlevelCost);
                return returnPilot;
            case JUNIOR:
            default:
                returnPilot = new AircraftPilot();
                returnPilot.setSeniority(0);
                returnPilot.setCostPerFlight(_juniorCost);
                return returnPilot;
        }
    }
```


# Testing

- Annotation `@Before` should be used for some initializations before tests, e.g. each test requires new client or user from zero, or new connection.
- Annotation `@BeforeClass` should be used if logic or initialization is used for whole test case. It will initialize all required before all tests. Also, if resource takes too much time or memory to tear down, uninitialize it in `@After` for each test, or `@AfterClass` for all tests in test case.

# GIT

- File `.gitignore` file should not contain general expressions, e.g. `/****`, as it ignores all files.
