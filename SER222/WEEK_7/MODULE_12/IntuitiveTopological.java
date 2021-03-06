import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: Implements topographical sort through a HashMap rather than LinkedList
 * or other methods. Results in more efficient execution.
 * 
 * @author Chris Kraus
 * 
 * @version: 1.0
 */
/**
 * @author chris
 *
 */
/**
 * @author chris
 *
 */
/**
 * @author chris
 *
 */
/**
 * @author chris
 *
 */
/**
 * @author chris
 *
 */
public class IntuitiveTopological implements TopologicalSort {
	
	private BetterDiGraph diGraph;

	/**
	 * Default constructor.
	 * 
	 * @param diGraph
	 */
	public IntuitiveTopological(BetterDiGraph diGraph) {
		this.diGraph = diGraph;
	}

	/**
	 * Initiates the Topographic sort process.
	 * 
	 * @return Iterable vertices in topographic order
	 */
	@Override
	public Iterable<Integer> order() {
		ArrayList<Integer> order = new ArrayList<Integer>();
		if(this.isDAG()) {
			while(this.diGraph.getVertexCount() > 0) {
				for(Integer v: this.diGraph.vertices()) {
					if(this.diGraph.getIndegree(v) == 0) {
						order.add(v);
						this.diGraph.removeVertex(v);
					}
				}
			}
		}
		return order;
	}

	/**
	 * Determines if the graph has a cycle or not before sorting.
	 *
	 * @return boolean true if the the graph is not cyclic, false otherwise
	 */
	@Override
	public boolean isDAG() {
		// Map indicating if a node is visited (has been processed by the algorithm)
	    Map<Integer, Boolean> visited = new HashMap<>();
	    boolean cyclic = true;
	    for(Integer v: this.diGraph.vertices()) {
	    	cyclic = isCyclic(v, visited);
	    }
		
		return !cyclic;
	}

	/**
	 * Recursively looks through each vertex adjacent to the ancestor vertex
	 * and determines if a cycle exists.
	 * 
	 * @param v current ancestor vertex being tested
	 * @param visited ArrayList of elements which have already been visited
	 * @return boolean true if a cycle exists, false otherwise
	 */
	private boolean isCyclic(Integer v, Map<Integer, Boolean> visited) {
		// Mark the current vertex as visited
		visited.put(v, true);
		for(Integer i: this.diGraph.getAdj(v)) {
			if(!visited.containsKey(i)) {
				return isCyclic(i, visited);
			}
			return true;
		}
		
		return false;
	}

}
