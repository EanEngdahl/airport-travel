# Airport-Travel-System
* Modeling out an airline, tracking profit and costs

# Project Members
* Ean
* Bryson

#Goal
You have been hired by the airline executives, and your goal is to estimate total income to the airline on any given day.
This should be able to run through several different scenarios, providing projections to the airline executives.


## Airlines
* Airlines have planes, pilots, and passengers.
* Airplanes have to land and take-off from airports
* not all airports fly to each other.

We will not be tracking people in this project. Instead, we will use this project to estimate airline costs.
We can assume that there are sections to an airplane (business, first class, economy, etc.) and that each person will pay *classPrice* dollars for a  flight. We can assume that *fuelCost* dollars are used in fuel for the airline, and *pilotCost* dollars are used to pay the pilots. So, the total cost of the flight will be:

		cost=(*fuelCost* x *edgeWeight*) + *pilotCost*
		revenue=forEachClass(*classPrice* x *numberOfPeopleInClass*)
		income=*gross*-*cost* 
		
## Airports
Airports are nodes on a graph, and all nodes have connections to other nodes. However, all nodes are not connected to each other. This is NOT an optimization problem, and I am not looking for how to travel b/w disconnected nodes.
Each node connection has a weight indicating how far away from each othe the ndoe is. So, for example:

	A - 7 - B - 5 - C
            |      /
            3     4
            |    /
            D----
	

so, in the above graph, the following connections are made:
* (A,B)=7 - node A is connected to node B, and is 7 miles away
* (B,D)=3 - Node B is connected to node D, and is 3 miles away 
* (C,D)=4 - Node D is connected to node C, and is 4 miles away
* Nodes A-C are not connected
* Nodes A-D are not connected
	
Here is the [wikipedia](https://en.wikipedia.org/wiki/Graph_theory) article on graphs	


## Planes
Not all planes are equal, however, the cost of fuel for each kind of plane is equal (so, gas price is constant).
Larger airplanes have 4 classes (first, business, economy plus, economy basic), and each one has class has it's own price. Smallest airplanes have 1 class. The cost of operating the plane is directly related to the size of the plane, as larger planes have more experienced pilots. Also the airline owns a smaller number of large plan and a larger number of small planes. Size of plane is determined by the distance.

As alluded to, pilots have different levels of seniority, and cost different. More senior pilots may fly smaller planes, but more junior pilots may not fly larger planes. 

## Flights
Each flight has 2 pilots at least.
Flights travel b/w nodes on the graph. The *edgeWeight* is the distance used the the calculation shown above.
Each flight will have a certain percentage of each section full, ranging from 0 to 100%.