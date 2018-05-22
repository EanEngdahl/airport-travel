package airlinesystemmodel;

import java.util.LinkedList;
import javafx.util.Pair;

@SuppressWarnings("restriction")
public class AirportGraph {
	
	    private final LinkedList< Pair<Integer, Integer> >[] adjacencyList;
	      
	    @SuppressWarnings("unchecked")
		public AirportGraph(int vertices_) {
	        adjacencyList = (LinkedList< Pair<Integer, Integer> >[]) new LinkedList[vertices_];
	        for (int i = 0; i < adjacencyList.length; ++i) {
	            adjacencyList[i] = new LinkedList<>();
	        }
	    }
	      
	    public void addEdge(int startVertex_, int endVertex_, int weight_) {
	        adjacencyList[startVertex_].add(new Pair<>(endVertex_, weight_));
	        adjacencyList[endVertex_].add(new Pair<>(startVertex_, weight_));
	    }
	      
	    public int getNumberOfVertices() {
	        return adjacencyList.length;
	    }
	      
	    public int getNumberOfEdgesFromVertex(int sourceVertex_) {
	        return adjacencyList[sourceVertex_].size();
	    }
	      
	    // Returns a copy of the Linked List of outward edges from a vertex
	    @SuppressWarnings("unchecked")
	    public LinkedList< Pair<Integer, Integer> > getEdgesFromVertex(int startVertex) {
	        LinkedList< Pair<Integer, Integer> > edgeList
	        = (LinkedList< Pair<Integer, Integer> >) new LinkedList(adjacencyList[startVertex]);
	          
	        return edgeList;
	    }
	      
	    //TODO Only used for testing that it works, remove later
	    public void printAdjacencyList() {
	        int i = 0;
	          
	        for (LinkedList< Pair<Integer, Integer> > list : adjacencyList) {
	            System.out.print("adjacencyList[" + i + "] -> ");
	              
	            for (Pair<Integer, Integer> edge : list) {
	                System.out.print(edge.getKey() + "(" + edge.getValue() + ")");
	            }
	              
	            ++i;
	            System.out.println();
	        }
	    }
	      
	    public boolean removeEdge(int startVertex_, int endVertex_) {
	    	int _distance = getDistanceBetweenNodes(startVertex_, endVertex_);
	    	Pair<Integer, Integer> _edge = new Pair <>(endVertex_, _distance);
	    	adjacencyList[startVertex_].remove(_edge);
	    	_edge = new Pair <>(startVertex_, _distance);
	        return adjacencyList[endVertex_].remove(_edge);
	    }
	    
	    public int getDistanceBetweenNodes(int startVertex_, int endVertex_) {
	    	for (int i = 0; i < getNumberOfEdgesFromVertex(startVertex_); i++) {
	    		if(adjacencyList[startVertex_].get(i).getKey() == endVertex_) {
	    			return adjacencyList[startVertex_].get(i).getValue();
	    		}
	    	}
	    	return -1;
	    }
}
