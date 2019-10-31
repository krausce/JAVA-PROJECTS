/**
 * Description: Implements a linear probing technique for creating a hash table data structure.
 * 				Simply probes along at each index until an available slot is located. The difference
 * 				between this and the "LinearProbingHT" class is the hash code calc implementation.
 * 				This version uses a quadratic method for calculating the hash code.
 * 
 * @author Chris Kraus
 * @version 1.0
 */

public class QuadProbingHT<Key, Value> extends LinearProbingHT<Key, Value> {
	
	/**
	 * <ctor> which uses the super class constructror
	 */
	public QuadProbingHT() {
		super(997);
	}

	/**
	 * Generates a hash code using a quadratic form
	 * 
	 * @param key
	 * @param i
	 * @return
	 */
	@SuppressWarnings("unused")
	private int hash(Key key, int i) {
		return (((key.hashCode() & 0x7fffffff) + i * i) % M);		
	}
}
