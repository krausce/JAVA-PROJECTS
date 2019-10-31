/********************************************************************************************************
 * Class: Sorter.java																					*
 * DESCRIPTION																							*
 * This class implements a quickSort algorithm in conjunction with an insertion algorithm which			*	
 * finishes sorting the ArrayList<Student> once the list size falls below a certain threshold.			*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/
package proj3;

import java.util.ArrayList;

public class Sorter {
	/*
	 * Partitions pList into two parts and selects a pivot to compare each element
	 * to. Once the loops have finished, a comparison is made to determine where to
	 * set the next pivot and then it returns the appropriate index whether it be
	 * "high" or "low".
	 */
	private static int partition(ArrayList<Student> pList, int pFromIdx, int pToIdx) {
		Student pivot = pList.get((pToIdx + pFromIdx) / 2);
		int low = pFromIdx - 1;
		int high = pToIdx + 1;
		while (low < high) {
			low++;
			while (pList.get(low).getLastName().compareTo(pivot.getLastName()) < 0) {
				low++;
			}
			high--;
			while (pList.get(high).getLastName().compareTo(pivot.getLastName()) > 0) {
				high--;
			}
		}
		if (low < high) {
			swap(pList, low, high);
		}
		return high;
	}

	/*
	 * quickSort recursively sorts pList by first ensuring that pFrom < pTo. Next,
	 * it checks to see if pList size is >= 7. If it is not, insertionSort is called
	 * on Plist as it is more efficient. If it is >= 7, then quickSort() is called.
	 * There is no return value for this method as it sorts pList in place in
	 * memory.
	 */
	private static void quickSort(ArrayList<Student> pList, int pFromIdx, int pToIdx) {
		if (pFromIdx > pToIdx) {
			return;
		} else if ((pToIdx - pFromIdx) < 7) {
			insertionSort(pList, pFromIdx, pToIdx);
		} else {
			int pivotIdx = partition(pList, pFromIdx, pToIdx);
			quickSort(pList, pFromIdx, pivotIdx);
			quickSort(pList, pivotIdx + 1, pToIdx);
		}
	}

	/*
	 * Compares each element in pList to the elements behind it and then inserts
	 * that element where it is appropriate(i.e. when pList.get(i - 1).getLastName()
	 * comes before the last name of the Student object selected for insertion.
	 */
	private static void insertionSort(ArrayList<Student> pList, int pFromIdx, int pToIdx) {
		for (int i = pFromIdx; i <= pToIdx; i++) {
			int j = i;
			while (j > 0 && pList.get(j - 1).getLastName().compareTo(pList.get(j).getLastName()) > 0) {
				swap(pList, j - 1, j);
				j--;
			}
		}
	}

	/*
	 * Implemented as a static method for other parts of the program to use this
	 * class' functionality.
	 */
	public static void sort(ArrayList<Student> pList) {
		quickSort(pList, 0, pList.size() - 1);
	}

	/*
	 * Swaps two elements within an ArrayList.
	 */
	private static void swap(ArrayList<Student> pList, int pIdx1, int pIdx2) {
		Student temp = pList.get(pIdx1);
		pList.set(pIdx1, pList.get(pIdx2));
		pList.set(pIdx2, temp);
	}
}
