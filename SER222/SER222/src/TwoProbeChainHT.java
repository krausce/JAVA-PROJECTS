import java.util.LinkedList;
import java.util.Queue;

/**
 * Description: Implements a linear probing technique for creating a hash table data structure.
 * 				This particular method creates two hash codes for each entry and thus checks if
 * 				either exists in the hash table. If they do, then the entry is added to the smaller
 * 				of the two chains at that index.
 * 
 * @author Chris Kraus
 * @version 1.0
 */

public class TwoProbeChainHT<Key, Value> implements SymbolTable<Key, Value> {

	private int N; // number of key-value pairs
	private int M; // hash table size
	@SuppressWarnings("rawtypes")
	private LinkedList<Entry>[] entries;

	public TwoProbeChainHT() {
		this(53);
	}

	/**
	 * <ctor> Instantiates instance variables as well as populates the linked list
	 * with blank Entry-type references.
	 * 
	 * @param M
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TwoProbeChainHT(int M) {
		this.M = M;
		N = 0;

		// Create Array of size M
		entries = (LinkedList<Entry>[]) new LinkedList[M];

		for (int i = 0; i < M; i++)
			entries[i] = new LinkedList<Entry>();

	}

	/**
	 * Computes the hash code for a given key.
	 * 
	 * @param key
	 * @return int hash code
	 */
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	/**
	 * Computes the hash code for a given key slightly differently
	 * so that its returned value differs greatly from the first
	 * has function.
	 * 
	 * @param key
	 * @return
	 */
	private int hash2(Key key) {
		return ((key.hashCode() & 0x7fffffff) % M) * 31 % M;
	}

	/**
	 * Determines if a give key is already int he hash table,
	 * if so it updates its give value. Otherwise, a new Entry
	 * is added to the hash table.
	 * 
	 * @param Key
	 * @param Value
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void put(Key key, Value val) {
		boolean isAdded = false;

		for(Entry entry: entries[hash(key)]) {
			if(key.hashCode() == entry.key.hashCode()) {
				entry.value = val;
				isAdded = true;
			}
		}

		for(Entry entry: entries[hash2(key)]) {
			if(key.hashCode() == entry.key.hashCode()) {
				entry.value = val;
				isAdded = true;
			}
		}

		if(!isAdded) {
			if(entries[hash(key)].size() < entries[hash2(key)].size()) {
				entries[hash(key)].add(new Entry<Key, Value>(key, val));
				N++;
			}
		} else {
			entries[hash2(key)].add(new Entry<Key, Value>(key, val));
			N++;
		}
	}

	/**
	 * Retrieves the element at the given key if it is in the hash table,
	 * otherwise returns null.
	 * 
	 * @param Key
	 * @return Value or null if key not found
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Value get(Key key) {
		for(Entry entry: entries[hash(key)]) {
			if(key.hashCode() == entry.hashCode()) {
				return (Value) entry.value;
			}
		}
		for(Entry entry: entries[hash2(key)]) {
			if(key.hashCode() == entry.hashCode()) {
				return (Value) entry.value;
			}
		}
		return null;
	}

	/**
	 * Removes the entry at the "key" location if it is in the hash
	 * table.
	 * 
	 * @param Key
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void delete(Key key) {
		if(contains(key)) {
			Entry target = null;

			for(Entry entry: entries[hash(key)]) {
				if(entry.key == key) {
					target = entry;
					entries[hash(key)].remove(target);
					N--;
					return;
				}

			}

			for(Entry entry: entries[hash2(key)]) {
				if(entry.key == key) {
					target = entry;
					entries[hash2(key)].remove(target);
					N--;
					return;
				}

			}
		}
	}

	/**
	 * Determines if a given key is in the hash table.
	 * 
	 * @param key
	 * @return bool True if the key exists, False otherwise
	 */
	@Override
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * @return True if N == 0, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return N == 0;
	}

	
	/**
	 * @return int N which represents the number of entry elements
	 */
	@Override
	public int size() {
		return N;
	}

	/**
	 * Makes this class into an iterable object.
	 * @return Queue
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<>();
		for (int i = 0; i < M; i++)
			for(@SuppressWarnings("rawtypes") Entry entry : entries[i])
				queue.add((Key) entry.key);
		return queue;
	}

}