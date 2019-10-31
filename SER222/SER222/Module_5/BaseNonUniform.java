/**
 * (basic description of the program or class)
 * 
 * Completion time: (estimation of hours spent on this program)
 *
 * @author Christopher Kraus, Acuna, Sedgewick
 * @version 1.0
 */

import java.util.Random;

public class BaseNonUniform implements SER222_02_01_HW02_Submission {
    
    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK)               *
     **************************************************************************/
    
    public static void insertionSort(Comparable[] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++)
        {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..          
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    
    
    public static void shellsort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        
        while (h >= 1) {
            // h-sort the array.
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                exch(a, j, j-h);
            }
            h = h/3;
        }
    }
    
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    
    /***************************************************************************
     * END - SORTING UTILITIES, DO NOT MODIFY                                  *
     **************************************************************************/

    /*
     * Generates an array of integers where half the data is 0s, half 1s.
     
      * @param size number of elements in the array.
      * @return generated test set.
     */
     public Integer[] generateTestDataBinary(int size) {
    	 Integer[] data = new Integer[size];
    	 
    	 for (int i = 0; i < size; i++) {
    		 if(i < (size/2)) {
    			 data[i] = 0;
    		 } else {
    			 data[i] = 1;
    		 }
    	 }
    	 return data;
     }
     
     /**
      * Generates an array of integers where half the data is 0s, half the
      * remainder is 1s, half the reminder is 2s, half the reminder is 3s, and so
      * forth. 
      * 
      * @param size number of elements in the array.
      * @return generated test set.
      */
     public Integer[] generateTestDataHalfs(int size) {
    	 Integer[] data = new Integer[size];
    	 int count = 1;
    	 
    	 for (int i = 0; i < size; i++) {
    		 if(i == int(size/2)) {
    			 count++;
    		 } else if (i == (1/2)**count*size + size) {
    			 count++;
    		 }
    		 data[i] = count;
    	 }
    	 
    	 return data;
     }
     
     /**
      * Generates an array of integers where half the data is 0s, and half random
      * int values.
      * @param size
      * @return 
      */
     public Integer[] generateTestDataHalfRandom(int size) {
    	 Integer[] data = new Integer[size];
    	 Random randNum = new Random();
    	 
    	 for(int i = 0; i < size; i++) {
    		 if(i > int(size/2)) {
    			 data[i] = randNum.nextInt();
    		 } else {
    			 data[i] = 0;
    		 }
    	 }
    	 
    	 return data;
     }
     
     /**
      * Computes the double formula value for two run times.
      * 
      * @param t1 first time
      * @param t2 second time
      * @return b value
      */
     public double computeDoublingFormula(double t1, double t2) {
    	 return Math.log((t2/t1));
     }
     
     /**
      * Computes an empirical b value for insertion sort by running it on a pair
      * of inputs and using the doubling formula.
      * 
      * @param small small test data array
      * @param large large test data array. twice the same of small array.
      * @return b value
      */
     public double benchmarkInsertionSort(Integer[] small, Integer[] large) {
    	 Stopwatch timer_1 = new Stopwatch();
    	 insertionSort(small);
    	 double shortTime = timer_1.elapsedTime();
    	 
    	 Stopwatch timer_2 = new Stopwatch();
    	 insertionSort(large);
    	 double longTime = timer_2.elapsedTime();
    	 
    	 return computeDoublingFormula(shortTime, longTime);
     }
     
     /**
      * Computes an empirical b value for shellsort sort by running it on a pair
      * of inputs and using the doubling formula.
      * @param small small test data array
      * @param large large test data array. twice the same of small array.
      * 
      * @return b value
      */
     public double benchmarkShellsort(Integer[] small, Integer[] large) {
    	 Stopwatch timer_1 = new Stopwatch();
    	 shellsort(small);
    	 double shortTime = timer_1.elapsedTime();
    	 
    	 Stopwatch timer_2 = new Stopwatch();
    	 shellsort(large);
    	 double longTime = timer_2.elapsedTime();
    	 
    	 return computeDoublingFormula(shortTime, longTime);
     }
     
     /**
      * Runs the two sorting algorithms on the three types of test data to
      * produce six different b values. B values are displayed to the user.
      * 
      * @param size size of benchmark array. to be doubled later.
      */
     public void runBenchmarks(int size) {
    	 
    	 Integer smBinary[] = generateTestDataBinary(size);
    	 Integer smHalfs[] = generateTestDataHalfs(size);
    	 Integer smRandom[] = generateTestDataHalfRandom(size);
    	 
    	 Integer lgBinary[] = generateTestDataBinary(size*2);
    	 Integer lgHalfs[] = generateTestDataHalfs(size*2);
    	 Integer lgRandom[] = generateTestDataHalfRandom(size*2);
    	 
    	 Integer[] smallBatch = {smBinary, smHalfs, smRandom};
    	 Integer[] lgBatch = {lgBinary, lgHalfs, lgRandom};
    	 String[] names = {"Bin", "Half", "RanInt"};
    	 
    	 System.out.println("\t\tInsertion\tShellsort");
    	 for (int i = 0; i < smallBatch.length; i++) {
    		 if (i == 0) {
    			 System.out.format("%s\t\t%f\t%f", names[i], benchmarkInsertionSort(smBinary, lgBinary), benchmarkShellsort(smBinary, lgBinary));
    		 } else if (i == 1) {
    			 System.out.format("%s\t\t%f\t%f", names[i], benchmarkInsertionSort(smHalfs, lgHalfs), benchmarkShellsort(smHalfs, lgHalfs));
    		 } else if (i == 2) {
    			 System.out.format("%s\t\t%f\t%f", names[i], benchmarkInsertionSort(smRandom, lgRandom), benchmarkShellsort(smRandom, lgRandom));
    		 }
    	 }
     }
     
    public static void main(String args[]) {
        SER222_02_01_HW02_Submission me = new BaseNonUniform();
        int size = 4096;
        
        //NOTE: feel free to change size here. all other code must go in the
        //      methods.
        
        me.runBenchmarks(size);
    }
}