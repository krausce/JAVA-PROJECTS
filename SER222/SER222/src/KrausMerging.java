/**
 * Implements various merge style algorithms.
 * 
 * Completion time: 4h
 *
 * @author Christopher Kraus, Acuna, Sedgewick and Wayne
 * @verison (version)
 */

import java.util.ArrayList;
import java.util.Random;

public class KrausMerging {
     
    /**
     * Entry point for sample output.
     * 
     * @param args the command line arguments
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
        Queue q1 = new ListQueue(); q1.enqueue("T"); q1.enqueue("R"); q1.enqueue("O"); q1.enqueue("L"); q1.enqueue("E");
        Queue q2 = new ListQueue(); q2.enqueue("X"); q2.enqueue("S"); q2.enqueue("P"); q2.enqueue("M"); q2.enqueue("E"); q2.enqueue("A");        
        Queue q3 = new ListQueue(); q3.enqueue(20); q3.enqueue(17); q3.enqueue(15); q3.enqueue(12); q3.enqueue(5);
        Queue q4 = new ListQueue(); q4.enqueue(18); q4.enqueue(16); q4.enqueue(13); q4.enqueue(12); q4.enqueue(4); q4.enqueue(1);       
        
        //Q1 - sample test cases
        Queue merged1 = mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue merged2 = mergeQueues(q3, q4);
        System.out.println(merged2.toString());
        
        //Q2 - sample test cases
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        assert isSorted(a);
        show(a);
        
        //Q3 - sample test cases
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        shuffle(b);
        show(b);
        
        shuffle(b);
        show(b);
    }
    
    /**
     * Note: If queues contain mismatch datatypes, a data mismatch error will
     * be thrown from the "CompareTo" method.
     * 
     * @param Sorted Queue<Comparable> x 2
     * 
     * @return Queue<Comparable> x 1
     */
    @SuppressWarnings("rawtypes")
	public static Queue mergeQueues(Queue<Comparable> q1, Queue<Comparable> q2) {
    	/*
    	 * We're assuming that each of the nodes stores the same data type, otherwise
    	 * the less() function will not work properly. Also, we are assuming that the
    	 * two queues are sorted in the same order. i.e. both in ascending or descending. 
    	 */
    	assert !(q1.isEmpty() || !q2.isEmpty()); // Test if either Queue is empty.
    	
        @SuppressWarnings("unchecked")
		Queue<Comparable> outQueue = new ListQueue();
        while(!(q1.isEmpty() || q2.isEmpty())) {
        	if(less(q1.front(), q2.front())) {
        		outQueue.enqueue(q1.dequeue());
        	} else if(q1.front().equals(q2.front())) {
        		outQueue.enqueue(q1.dequeue());
        		outQueue.enqueue(q2.dequeue());
        	} else {
        		outQueue.enqueue(q2.dequeue());
        	}
        }
        
        while(!q1.isEmpty()) {
        	outQueue.enqueue(q1.dequeue());
        }
        while(!q2.isEmpty()) {
        	outQueue.enqueue(q2.dequeue());
        }
        return outQueue;
    }
    
    /**
     * Takes in an array of comparable-type elements, calls the recursive
     * mergesort method.
     * 
     * @param Copmarable[] a to be sorted
     * 
     * @void
     */
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		if(a.length < 2) {return;}
    	a = mergesort(a, 0, a.length - 1); 
    	
    	assert isSorted(a);
    }
	
    /**
     * The base case is when hi <= lo. Otherwise merge is called on the recursive
     * mergesort of the input array.
     * 
     * @param Comparable[] a the array to be sorted
     * 		  int lo, hi, used to set the bounds for dividing up the array
     * 
     * @return Comparable[] sorted array
     */
    @SuppressWarnings("rawtypes")
	private static Comparable[] mergesort(Comparable[] a, int lo, int hi) {
    	
    	int mid = lo + (hi - lo)/2;
    	return hi <= lo ? a : merge(mergesort(a, lo, mid), mergesort(a.clone(), mid+1, hi),
    			lo, mid, hi);    	
    }
    
    /**
     * Takes in two sorted arrays and returns them as a singularly merged/sorted
     * array.
     * 
     * @param Comparable[] a, b two sorted arrays to be merged
     * 		  int lo, mid, hi sets up the partition for merging
     * 
     * @return Comparable[] merged array
     */
    @SuppressWarnings("rawtypes")
	private static Comparable[] merge(Comparable[] a, Comparable[] b, int lo, int mid, int hi) {
    	
		assert isSorted(a) && isSorted(b);

		int i = lo, j = mid + 1;
		
		for(int k = lo; k <= hi; k++) {
			if(i > mid) {a[k] = b[j++];}
			else if(j > hi) {a[k] = b[i++];}
			else if(less(b[i], b[j])) {a[k] = b[i++];}
			else {a[k] = b[j++];}
		}
		
		return a;
	}
    
    /**
     * Takes in an Object[] and sends it to a recursive method to
     * randomly shuffle the contents inplace in memory.
     * 
     * @param Object[] a the array to be shuffled.
     * 
     * @void
     */
    public static void shuffle(Object[] a) {
		shuffle(a, 0, a.length-1);
    }
	
    /**
     * Takes in the input Object[] and randomly through recursion
     * shuffles the contents.
     * 
     * @param a 	array to be shuffled
     * @param lo
     * @param hi
     * 
     * @void
     */
	public static void shuffle(Object[] a, int lo, int hi) {
		if(hi<=lo) return;
		int partition = lo + (hi - lo)/2;
		shuffle(a, lo, partition); shuffle(a, partition + 1, hi);
		randomMerge(a, lo, partition, hi);
    }
	
	/**
	 * Takes in an Object array and merges it together in a random order.
	 * 
	 * @param Object[]
	 * @param int lo, partition, hi sets up intervales to merge the array over
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void randomMerge(Object[] a, int lo, int partition, int hi) {
		ArrayList<Integer> indeces = new ArrayList();
		for(int i = lo; i < partition; i++) {
			int nextIdx = getRandInt(indeces, 0, hi);			
			indeces.add(nextIdx);
			swap(a, i, nextIdx);
		}
	}
	
	/**
	 * Takes in an ArrayList<Integer> to track which ints have been used in a set
	 * range (max - min). Once a randomly generated int is determined to be unique,
	 * it is added to the indeces ArrayList before being returned.
	 * 
	 * @param indeces
	 * @param min
	 * @param max
	 * 
	 * @return int random uniquely generated index from a set range of indeces.
	 */
	private static int getRandInt(ArrayList<Integer> indeces, int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		int nextIdx = r.nextInt((max - min) + 1) + min;
		int count = 0;
		while(indeces.contains(nextIdx) && count < max) {
			nextIdx = r.nextInt((max - min) + 1) + min;
		}
		indeces.add(nextIdx);
		return nextIdx;
	}

	/**
	 * Takes in an Object[] and swaps the values stored at the input
	 * indeces in memory.
	 * 
	 * @param Object[]
	 * @param int i, nextIdx indeces for the swap
	 * 
	 * @void
	 */
    private static void swap(Object[] a, int i, int nextIdx) {
		Object temp = a[i];
		a[i] = a[nextIdx];
		a[nextIdx] = temp;		
	}

	//sorting helper from text
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //sorting helper from text
    @SuppressWarnings("rawtypes")
	private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }
    
    //sorting helper from text
    @SuppressWarnings("rawtypes")
	public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
}