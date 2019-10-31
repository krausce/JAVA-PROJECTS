/**
 * This program provides an implementation of the Deque interface
 * and demonstrates it.
 * 
 * Completion time: 4h
 * 
 * @author Christopher Kraus, Acuna
 * @version 1.0
 */
import java.util.NoSuchElementException;
    
/**
 * This class implements a doubly linked list of generic data type.
 * 
 * @param <Item> generic data type
 */
public class KrausDeque<Item> implements Deque<Item> {
	
	private LinearNode<Item> head;
	private LinearNode<Item> tail;
	private int n = 0;
	
	/**
	 * Default <ctor>
	 */
	public KrausDeque() {
		setHead();
		setTail();
	}

	/**
	 * Adds an element to the front of the list. If the list is empty,
	 * the first element added becomes the new head, tail and is doubly
	 * linked to itself and increments "n".
	 * 
	 * @param <Item> generic data type
	 */
    @Override
	public void enqueueFront(Item element) {
		
    	LinearNode<Item> tmp = new LinearNode<>(element);
    	if (this.isEmpty()) {
    		this.setHead(tmp);
    		this.setTail(tmp);
    		this.incrementN();
    		tmp.setNext(tmp);
    		tmp.setPrev(tmp);
    	} else {
			tmp.setNext(this.getHead());
			this.getHead().setPrev(tmp);
			this.setHead(tmp);
			this.getHead().setPrev(this.getTail());
			this.incrementN();
    	}
	}

    /**
	 * Adds an element to the back of the list. If the list is empty,
	 * the first element added becomes the new head, tail and is doubly
	 * linked to itself and increments "n".
	 * 
	 * @param <Item> generic data type
	 */
	@Override
	public void enqueueBack(Item element) {
				
    	LinearNode<Item> tmp = new LinearNode<>(element);
    	if (this.isEmpty()) {
    		this.setHead(tmp);
    		this.setTail(tmp);
    		this.incrementN();
    		tmp.setNext(tmp);
    		tmp.setPrev(tmp);
    	} else {
	    	tmp.setPrev(this.getTail());
	    	this.getTail().setNext(tmp);
	    	this.setTail(tmp);
	    	this.getTail().setNext(this.getHead());
	    	this.incrementN();
    	}
	}
	
	/**
	 * If the list is empty, throws a new "NoSuchElementException"
	 * to the caller. Otherwise it returns the head element and
	 * changes the appropriate references to Head, next, prev, and
	 * decrements "n".
	 * 
	 * @return <Item> Head element
	 */
	@Override
	public Item dequeueFront() throws NoSuchElementException {
		
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		KrausDeque<Item>.LinearNode<Item> tmp = this.getHead();
		this.setHead(tmp.getNext());
		this.getHead().setPrev(this.getTail());
		tmp.setNext();
		this.decrementN();
		return tmp.getElement();		
	}

	/**
	 * If the list is empty, throws a new "NoSuchElementException"
	 * to the caller. Otherwise it returns the tail element and
	 * changes the appropriate references to Tail, next, prev, and
	 * decrements "n".
	 * 
	 * @return <Item> Tail element
	 */
	@Override
	public Item dequeueBack() throws NoSuchElementException {
		
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		KrausDeque<Item>.LinearNode<Item> tmp = this.getTail();
		this.setTail(tmp.getPrev());
		this.getTail().setNext(this.getHead());
		tmp.setPrev();
		this.decrementN();
		return tmp.getElement();		
	}

	/**
	 * If the list is empty, throws a new "NoSuchElementException"
	 * to the caller. Otherwise it returns the Head element.
	 * 
	 * @return <Item> Head element
	 */
	@Override
	public Item first() throws NoSuchElementException {
		
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.getHead().getElement();
	}

	/**
	 * If the list is empty, throws a new "NoSuchElementException"
	 * to the caller. Otherwise it returns the Tail element.
	 * 
	 * @return <Item> Tail element
	 */
	@Override
	public Item last() throws NoSuchElementException {
		
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.getTail().getElement();
	}

	/**
	 * Checks the size of the deque element, if its size == 0,
	 * returns True.
	 * 
	 * @return <Boolean> True if size == 0, else false
	 */
	@Override
	public boolean isEmpty() {
		
		return this.length() == 0;
	}

	/**
	 * @return <int> An integer representing the number of elements
	 */
	@Override
	public int size() {

		return this.length();
	}
	
	/**
	 * If the deque is empty, returns "empty", else it loops through
	 * the doubly linked list and creates a string representation of
	 * the deque.
	 * 
	 * @return <String> A string of the elements in the list
	 */
	@Override
	public String toString() {
		
		if (this.isEmpty()) {
			return "empty";
		}
		
		String out = "";
		int i = this.length();		
		KrausDeque<Item>.LinearNode<Item> tmpTail = this.getTail();
		
		while (i > 0) {
			out = out.concat((tmpTail.getElement().toString() + ' '));
			tmpTail = tmpTail.getPrev();
			i--;
		}
		
		return out;
	}
	
	/**
	 * Accessor - Mutator methods for all of the instance variables.
	 * @param element
	 */
	public void setHead(LinearNode<Item> element) {
		head = element;
	}
	
	public void setHead() {
		head = null;
	}
	
	public void setTail() {
		tail = null;
	}
	
	public void setTail(LinearNode<Item> element) {
		tail = element;
	}
	
	public LinearNode<Item> getHead(){
		return head;
	}	
	
	public LinearNode<Item> getTail(){
		return tail;
	}
	
	private void incrementN() {
		n++;
	}
	
	private void decrementN() {
		n--;
	}
	
	public int length() {
		return n;
	}
	
	/**
	 * This is an inner class created to represent each node
	 * in the doubly-linked list.
	 * 
	 * @author Chris Kraus
	 *
	 * @param <Item> generic data type for each node created
	 */
	@SuppressWarnings("hiding")
	public class LinearNode<Item> {
		
		private LinearNode<Item> next;
		private LinearNode<Item> prev;
		private Item element;
		
		/**
		 * Default <ctor>.
		 */
		public LinearNode() {
			this.setNext();
			this.setPrev();
		}
		
		/**
		 * <ctor> which sets the node to store the input param element.
		 * 
		 * @param element
		 */
		public LinearNode(Item element) {
			this.setNext();
			this.setPrev();
			setElement(element);
		}
		
		/**
		 * Accessor/Mutator methods for the instance variables.
		 * 
		 * @return <Item> for accessor methods
		 * @param <Item> for mutator methods
		 */
		public Item getElement() {
			return element;
		}
		
		public void setElement(Item elem) {
			element = elem;
		}
		
		public LinearNode<Item> getNext() {
			return next;
		}
		
		public void setNext(LinearNode<Item> node) {
			next = node;
		}
		
		public void setNext() {
			next = null;
		}
		
		public LinearNode<Item> getPrev() {
			return prev;
		}
		
		public void setPrev(LinearNode<Item> node) {
			prev = node;
		}
		
		public void setPrev() {
			prev = null;
		}
	}
	
	/**
     * Program entry point for deque. 
     * @param args command line arguments
     */    
    public static void main(String[] args) {
        KrausDeque<Integer> deque = new KrausDeque<>();

        //standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront();        
        deque.enqueueBack(9);
        deque.enqueueBack(8);
        deque.dequeueFront();
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());   

        //deque features
        System.out.println(deque.dequeueFront());        
        deque.enqueueFront(1);
        deque.enqueueFront(11);                         
        deque.enqueueFront(3);                 
        deque.enqueueFront(5);         
        System.out.println(deque.dequeueBack());
        System.out.println(deque.dequeueBack());        
        System.out.println(deque.last());                
        deque.dequeueFront();
        deque.dequeueFront();        
        System.out.println(deque.first());        
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());            
    }
} 