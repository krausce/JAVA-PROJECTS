/*
 * Description: This is a base class for Hash Table entries.
 * 
 * @author Chris Kraus
 * 
 */
public class Entry<Key, Value>{
	public Key key;
	public Value value;
	
	public Entry(Key k, Value v) {
		this.key = k;
		this.value = v;
	}
}

