/**
 * This class is built and designed to test the Insertion Sort and Shell Sort
 * algorithms.
 * 
 * Completion time: 4 hours
 *
 * @author Christopher Kraus, Acuna, Sedgewick
 * @version 1.0
 */

import java.math.BigDecimal;
import java.util.Random;

public class KrausBaseNonUniform implements SER222_02_01_HW02_Submission {
    
    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK)               *
     **************************************************************************/
    
    @SuppressWarnings("unchecked")
	public static void insertionSort(@SuppressWarnings("rawtypes") Comparable[] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++)
        {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..          
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public static void shellsort(@SuppressWarnings("rawtypes") Comparable[] a) {
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
    
    
    private static boolean less(@SuppressWarnings("rawtypes") Comparable<Comparable> v, @SuppressWarnings("rawtypes") Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    
    private static void exch(@SuppressWarnings("rawtypes") Comparable[] a, int i, int j) {
        @SuppressWarnings("rawtypes")
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
    	 size *= 100;
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
    	 size *= 100;
    	 Integer[] data = new Integer[size];
    	 double count = 1.0;
    	 
    	 for (int i = 0; i < size; i++) {
    		 if (i == (size - Math.floorDiv(size, (int) Math.pow(2, count)))) {
    			 count++;
    		 }
    		 data[i] = (int) count;
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
    		 if (i < Math.floorDiv(size, 2)) {
    			 data[i] = 0;
    		 } else {
    			 data[i] = randNum.nextInt();
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
    	 return Math.abs(Math.log(divideDouble(t2, t1)));
     }
     
     @SuppressWarnings("deprecation")
	public double divideDouble(double t1, double t2) {
    	 BigDecimal t3 = new BigDecimal(t1);
    	 BigDecimal t4 = new BigDecimal(t2);
    	 
    	return t4.divide(t3, 6).doubleValue();
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
    	 
    	 Integer[][] data = new Integer[6][size*2];
    	 String[] names = {"Bin", "Half", "Ranint"};
    	 
    	 for (int i = 0; i < 6; i++) {
    		 if (i > 2) { size *= 2; }
    		 if ((i == 0) || (i == 3)) {
    			 data[i] = generateTestDataBinary(size);
    		 } else if ((i == 2) || (i == 4)) {
    			 data[i] = generateTestDataHalfs(size);
    		 } else {
    			 data[i] = generateTestDataHalfRandom(size);
    		 }
    	 }
    	 
    	 System.out.println("\tInsertion\tShellsort");
    	 for (int j = 0; j < 3; j++) {
    		 double insertSort = benchmarkInsertionSort(data[j], data[j+3]);
    		 if(insertSort > 1000000) {insertSort = 0.000;}
    		 double shellSort = benchmarkShellsort(data[j], data[j+3]);
    		 if (shellSort > 1000000) { shellSort = 0.000;}
    		 System.out.format("%s\t%.3f\t\t%.3f\n", names[j], insertSort, shellSort);   		 
    	 }
     }
     
     /**
      * Returns a string representation of the input array.
      *
      * @param Integer[]
      * @return String
      **/
     
     public String toString(Integer[] nums) {
    	 String strOut = "";
    	 int count = 0;
    	 for (Integer num: nums) {
    		 strOut += String.format("%d, ", num);
    		 count++;
    		 if(count%10 == 0) {
    			 strOut += "\n";
    		 }
    	 }
    	 return strOut;
     }
     
    public static void main(String args[]) {
        SER222_02_01_HW02_Submission me = new KrausBaseNonUniform();
        int size = 4096*2;
        
        //NOTE: feel free to change size here. all other code must go in the
        //      methods.
        
        me.runBenchmarks(size);
   	 	}
    
}