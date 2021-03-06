import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Description: This class is an implementation for a directed graph data structure.
 * 
 * @author Chris Kraus
 * @version: 1.0
 */
public class BetterDiGraph implements EditableDiGraph {
	
	private int vertices;
	private int edges;
	private HashMap<Integer, HashSet<Integer>> adjacentElements;
	
	/**
	 * Default constructor.
	 */
	public BetterDiGraph() {
		this.adjacentElements = new HashMap<>();
		this.vertices = 0;
		this.edges = 0;
	}

	/**
	 * Creates a link between two vertices.
	 * 
	 * @param int vertex from
	 * @param int vertex to
	 */
	@Override
	public void addEdge(int v, int w) {
		addVertex(v); 
		addVertex(w);
		if(this.adjacentElements.containsKey(v)) {
			if(!this.adjacentElements.get(v).contains(w)) {
				this.adjacentElements.get(v).add(w);
				this.edges++;
			}
		}
	}

	/**
	 * Adds an individual vertex to the graph if it is not already
	 * in the graph.
	 * 
	 * @param int vertex to be added
	 */
	@Override
	public void addVertex(int v) {
		if(!this.adjacentElements.containsKey(v)) {
			this.adjacentElements.put(v, new HashSet<>());
			this.vertices++;
		}
	}

	/**
	 * Returns all of the vertices connected to the input vertex.
	 * 
	 * @param int vertex ancestor
	 */
	@Override
	public Iterable<Integer> getAdj(int v) {
		return this.adjacentElements.get(v);
	}

	/**
	 * @return int number of vertices in the graph.
	 */
	@Override
	public int getEdgeCount() {
		return this.edges;
	}

	/**
	 * Determines the number of vertices connected to the input vertex.
	 * 
	 * @param int ancestor vertex
	 * 
	 * @return int number of descendants.
	 */
	@Override
	public int getIndegree(int v) throws NoSuchElementException {
		if(!containsVertex(v)) {
			throw new NoSuchElementException();
		}
		int numInHash = 0;
		for(Integer out: this.adjacentElements.keySet()) {
			for(Integer i: this.adjacentElements.get(out)) {
				if(i.equals(v)) {
					numInHash++;
				}
			}
		}
		return numInHash;
	}

	/**
	 * Determines if a vertex is already in the graph.
	 * 
	 * @param v
	 * @return boolean true if the vertex is in the graph, false otherwise
	 */
	private boolean containsVertex(int v) {
		return this.adjacentElements.containsKey(v);
	}

	/**
	 * @return int number of vertices in the graph
	 */
	@Override
	public int getVertexCount() {
		return this.vertices;
	}

	/**
	 * Removes an edge between to vertices
	 * 
	 * @param int ancestor vertex
	 * @param int descendant vertex
	 */
	@Override
	public void removeEdge(int v, int w) {
		if(this.adjacentElements.containsKey(v) && this.adjacentElements.containsKey(w)) {
			if(this.adjacentElements.get(v).contains(w)) {
				this.adjacentElements.get(v).remove(w);
				this.edges--;
			}
		}
	}

	/**
	 * Removes a vertex from the graph and decrements "vertices"
	 * 
	 * @param int vertex to be removed
	 */
	@Override
	public void removeVertex(int v) {
		if(this.adjacentElements.containsKey(v)) {
			this.adjacentElements.remove(v);
			for(Map.Entry<Integer, HashSet<Integer>> entry: this.adjacentElements.entrySet()) {
				if(entry.getValue().contains(v)) {
					this.adjacentElements.get(entry.getKey()).remove(v);
				}
			}
		this.vertices--;
		}
	}

	/**
	 * Iterates through the vertices and adds them to an iterable data type.
	 *
	 * @return Iterable
	 */
	@Override
	public Iterable<Integer> vertices() {
		ArrayList<Integer> hashDisplay = new ArrayList<>();
		for(Integer out: this.adjacentElements.keySet()) {
			if(!hashDisplay.contains(out)) {
				hashDisplay.add(out);
			}
		}
		return hashDisplay;
	}
	
	/**
	 * @return String represents the data stored in the graph.
	 */
	@Override
	public String toString() {
		return "" + this.adjacentElements;	
	}
}
