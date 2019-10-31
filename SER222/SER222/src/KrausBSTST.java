/**
 * A binary search tree based implementation of a symbol table.
 * 
 * @author Chris Kraus, Sedgewick and Wayne, Acuna
 * @version (version) 1.0
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


        

public class KrausBSTST<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value> {
    private Node root;

    private class Node
    {
        private final Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key; this.val = val; this.N = N;
        }
    }
    
    /**
     * Sets up the method call to solve this problem.
     */
    @Override
    public int size() {
        return size(root);
    }
    
    /**
     * Returns the size of a given folder and its subfolders.
     * 
     * @param x
     * @return
     */
    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }
    
    /**
     * Sets up the method call to solve this problem.
     */
    @Override
    public Value get(Key key) {
        return get(root, key);
    }
    
    
    /**
     * Recursively finds the element Value and retrieves it.
     * @param x
     * @param key
     * @return
     */
    private Value get(Node x, Key key) {
        // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }
    
    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    
    /**
     * Recursive method for adding an element to the BST.
     * 
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        // Change key’s value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    /**
	 * Iterative method for retrieving a value from the BST.
	 * 
	 * @param x
	 * @param key
	 * @param val
	 * @return
	 */
    public Value getFast(Key key) {
    	Node x = root;
    	while(x != null) {
    		int cmp = key.compareTo(x.key);
    		if(cmp == 0) {return x.val;} 
    		else if(cmp < 0) {x = x.left;}
    		else {x = x.right;}
    	}
    	return null;
    }
    
    /**
     * Sets up the method call to solve this problem.
     */
    public void putFast(Key key, Value val) {
    	if(root == null) {root = new Node(key, val, 1);}
    	root = putFast(root, key, val);
    }

	/**
	 * Iterative method for adding an element to the BST.
	 * 
	 * @param x
	 * @param key
	 * @param val
	 * @return
	 */
	private KrausBSTST<Key, Value>.Node putFast(KrausBSTST<Key, Value>.Node x, Key key, Value val) {
    	while(x != null) {
    		int cmp = key.compareTo(x.key);
    		if(cmp == 0) {
    			x.val = val;
    			return x;
    		} else if(cmp < 0) {
    			x.N++;
    			if(x.left == null) {
    				x.left = new Node(key, val, 1);
    			}
    			break;
    		}
    		else {
    			x.N++;
    			if(x.right == null) {
    				x.right = new Node(key, val, 1);
    			}
    			break;
    		}
    	}
    	return x;
	}

	/**
     * Sets up the recursive call to solve this problem.
     */
	@Override
    public Key min() {
      return min(root).key;
    }
    
	/**
     * Returns the min element in the BST.
     * 
     * @param x
     * @return
     */
    private Node min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }
    
    /**
     * Sets up the recursive call to solve this problem.
     */
    @Override
    public Key max() {
      return max(root).key;
    }
    
    /**
     * Returns the max element in the BST.
     * 
     * @param x
     * @return
     */
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }
    
    /**
     * Sets up the recursive call to solve this problem.
     */
    @Override
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }
    
    
    /**
     * Either returns the Node at a given location which matches the key
     * or it returns the location just after where it should be located.
     * 
     * @param x
     * @param key
     * @return
     */
    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }
    
    /**
     * Sets up the recursive call to solve this problem.
     */
    @Override
    public Key select(int k) {
        return select(root, k).key;
    }
    
    /**
     * Returns the selected node.
     * 
     * @param x
     * @param k
     * @return
     */
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }
    
    /**
     * Sets up the recursive call to solve this problem.
     * 
     * @param Key
     */
    @Override
    public int rank(Key key) {
        return rank(key, root);
    }
    
    /**
     * Returns an int representing the size of the BST below a certain Node.
     * 
     * @param key
     * @param x
     * @return
     */
    private int rank(Key key, Node x) {
        // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }
    
    /**
     * Sets up the recursive call to solve this problem.
     */
    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }
    
    /**
     * Deletes the min element from the BST.
     * 
     * @param x
     * @return
     */
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    /**
     * Sets up the recursive call to delete the keyed element.
     * 
     * @param key
     */
    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }
    
    /**
     * Searches through the BST and deletes the keyed element.
     * 
     * @param x
     * @param key
     * @return
     */
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else
        {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    
    /**
     * Sets up the recursive method call to solve this problem.
     */
    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
    
    /**
     * Recursively retrieves the keys from the BST.
     * 
     * @param Key lo
     * @param Key hi
     */
    @Override
    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    
    /**
     * Returns a Queue containing all of the BST keys.
     * 
     * @param x
     * @param queue
     * @param lo
     * @param hi
     */
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
    {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
    
    /**
     * Sets up the recursive call to solve this problem.
     */
    @Override
    public boolean contains(Key key) {
        if(isEmpty()) {return false;}
        return contains(root, key);
    }

    /**
     * Recursively searches for an element. Returns true if it is present,
     * false otherwise.
     * 
     * @param x
     * @param key
     * @return boolean
     */
    private boolean contains(KrausBSTST<Key, Value>.Node x, Key key) {
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {return contains(x.left, key);}
		else if(cmp > 0) {return contains(x.right, key);}
		else return true;
	}

	@Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Sets up the recursive call to solve this problem.
     * 
     * @param Key search parameter
     */
    @Override
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {return null;}
        return x.key;
    }
    
    /**
     * Either locates the key or the location just before where it should be located and
     * returns that Node.
     * 
     * @param x
     * @param key
     * @return
     */
    private KrausBSTST<Key, Value>.Node ceiling(KrausBSTST<Key, Value>.Node x, Key key) {
		if(x == null) {return null;}
		int cmp = key.compareTo(x.key);
		if(cmp == 0) {return x;}
		else if(cmp > 0) {return ceiling(x.right, key);}
		Node t = ceiling(x.left, key);
		if(t != null) {return t;}
		return x;
	}

	/**
	 * Sets up the method call for recursively solving this problem.
	 */
	@Override
    public void deleteMax() {
		root = deleteMax(root);
    }

    /**
     * Recursively searches for the max element.
     * 
     * @param x
     * @return
     */
    private KrausBSTST<Key, Value>.Node deleteMax(KrausBSTST<Key, Value>.Node x) {
    	if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
	}

	/**
	 * Sets up the recurion to return the size of the BST from the given node to its lowest leaf.
	 * 
	 * @param Key lo
	 * @param Key hi
	 */
	@Override
    public int size(Key lo, Key hi) {
       if(lo == null && hi == null) {return 0;}
       return size(root, lo) + size(root, hi);
    }
    
    /**
     * Returns the tree size from the given node to its lowest leaf.
     * 
     * @param x
     * @param key
     * @return
     */
    private int size(KrausBSTST<Key, Value>.Node x, Key key) {
		return ceiling(root, key).N;
	}

	/**
	 * Caller function to balance the BST.
	 */
	public void balance() {
		LinkedList<Node> nodes = new LinkedList();
		sortTree(nodes, root);
		
		root = balance(nodes, 0, size() - 1);
    }

	/**
	 * Recursive function which balances the BST.
	 * 
	 * @param nodes
	 * @param lo
	 * @param hi
	 * @return
	 */
	private KrausBSTST<Key, Value>.Node balance(LinkedList<KrausBSTST<Key, Value>.Node> nodes, int lo, int hi) {
		if(lo > hi) {return null;}
		
		int mid = (lo + hi)/2;
		
		if((lo + hi) % 2 == 1) {mid++;}
		
		Node midNode = nodes.get(mid);
		
		midNode.left = balance(nodes, lo, mid - 1);
		midNode.right = balance(nodes, mid + 1, hi);
		
		return midNode;
	}

	/**
	 * BST Sort function.
	 * 
	 * @param nodes
	 * @param x
	 */
	private void sortTree(LinkedList<KrausBSTST<Key, Value>.Node> nodes, KrausBSTST<Key, Value>.Node x) {
		if(x == null) {return;}
		
		sortTree(nodes, x.left);
		nodes.add(x);
		sortTree(nodes, x.right);
	}

	/**
	 * Starter function for retrieving a specific Node from the BST.
	 * 
	 * @param key
	 * @return
	 */
	private Node getNode(Key key) {
		return getNode(root, key);
	}
	
	/**
	 * Recursive helper function to locate and retrieve a node from the BST.
	 * 
	 * @param x
	 * @param key
	 * @return
	 */
	private KrausBSTST<Key, Value>.Node getNode(KrausBSTST<Key, Value>.Node x, Key key) {
		if(root.key == key) {return root;}
		int cmp = key.compareTo(root.key);
		if(cmp < 0) {return getNode(x.left, key);}
		else {return getNode(x.right, key);}
	}

	
	/**
	 * Displays the BST back to the user in level order from left to right.
	 * 
	 * @param key
	 */
	public void printLevel(Key key) {
		Node x = getNode(key);
		if(x == null) {return;}
		Queue<Node> listOut = new LinkedList<Node>();
		listOut.add(x);
		
		while(!listOut.isEmpty()) {
			x = listOut.poll();
			System.out.println(x.val);
			
			if(x.left != null) {
				listOut.add(x.left);
			}
			if(x.right != null) {
				listOut.add(x.right);
			}
		}
    }

	/**
     * entry point for testing.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KrausBSTST<Integer, String> bst = new KrausBSTST();
        
        bst.put(10, "TEN");
        bst.put(3, "THREE");
        bst.put(1, "ONE");
        bst.put(5, "FIVE");
        bst.put(2, "TWO");
        bst.put(7, "SEVEN");
        
        bst.printLevel(10); //root
        
        System.out.println("After balance:");
        bst.balance();
        bst.printLevel(5); //root
    }
}