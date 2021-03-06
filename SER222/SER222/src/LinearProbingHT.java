import java.util.LinkedList;
import java.util.Queue;

/**
 * Description: Implements a linear probing technique for creating a hash table data structure.
 * 				Simply probes along at each index until an available slot is located.
 * 
 * @author Chris Kraus
 * @version 1.0
 */

public class LinearProbingHT<Key, Value> implements SymbolTable<Key, Value> {
	
	private int N; // number of key-value pairs
	protected int M; // hash table size
	private Entry<Key, Value>[] entries;

	/**
	 * <ctor>
	 */
	public LinearProbingHT() {
		this(997);
	}

	/**
	 * <ctor> with an M value provided as a param
	 * @param M
	 */
	@SuppressWarnings("unchecked")
	public LinearProbingHT(int M) {
		this.M = M;
		this.N = 0;
		
		this.entries = new Entry[M];
	}
	
	/**
	 * Hash code generator
	 * 
	 * @param key
	 * @return int
	 */
	private int hash(Key key) {
       return (key.hashCode() & 0x7fffffff) % M;
    }

	/**
	 * Determines if a key is already in the hash table. If not, a new
	 * Entry element is added to the hash table. Otherwise the value is
	 * updated.
	 * 
	 * @param Key
	 * @param Value
	 */
	@Override
	public void put(Key key, Value val) {
		if(N >= M) {
			resize();
		}
		int i;
		for(i = hash(key); entries[i] != null; i = (i + 1) % M) {
			if(entries[i].equals(key)) {
				entries[i].value = val;
			}
		}
		entries[i] = new Entry<Key, Value>(key, val);
	}

	/**
	 * In the event that the hash table has filled up, a new hash table
	 * is created which the next prime number at least 2 x the size of
	 * the original.
	 *  
	 */
	private void resize() {
		M *= 2;
		while(M%2 == 0 || M%3 == 0) {
			M++;
		}
		LinearProbingHT<Key, Value> resized = new LinearProbingHT<>(2*M);
		for(int i = 0; i < M; i++) {
			if(entries[i] != null) {
				resized.put(entries[i].key, entries[i].value);
			}
		}
		this.entries = resized.entries;
		this.M = resized.M;
	}


	@Override
	public Value get(Key key) {
		for(int i = hash(key); entries[i] != null; i = (i + 1)%M) {
			if(entries[i].key.equals(key)) {
				return entries[i].value;
			}
		}
		return null;
	}

	/**
	 * Locates and removes an entry if it exists in the hash table. Otherwise
	 * it only loops through without notifying the user.
	 * 
	 * @param Key
	 */
	@Override
	public void delete(Key key) {
		if(contains(key)) {
			for(int i = hash(key); entries[i] != null; i = (i + 1) % M) {
				if(entries[i].key == key) {
					for(int j = i + 1; entries[j] != null; j = (j+1) % M) {
						entries[i] = entries[j];
						i = (i + 1) % M;
					}
					entries[i] = null;
					N--;
				}				
			}
		}		
	}

	/**
	 * @param Key
	 * @return boolean True if the key is found, False otherwise
	 */
	@Override
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * @return boolean True if N == 0, False otherwise
	 */
	@Override
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * @return int N Current number of entries in the hash table
	 */
	@Override
	public int size() {
		return N;
	}

	/**
	 * Converts the hash table into an iterable object.
	 * 
	 * @return Queue
	 */
	@Override
	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<>();
        for (int i = 0; i < M; i++)
            if(entries[i] != null) {
            	queue.add(entries[i].key);
            }
        return queue;
	}	

}
