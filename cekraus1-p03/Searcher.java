/********************************************************************************************************
 * Class: Searcher.java																					*
 * DESCRIPTION																							*
 * This class implements a recursive binary search algorithm to find a student in the database			*
 * which matches the last name of the given input String.												*																													*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/
package proj3;

import java.util.ArrayList;

public class Searcher {
	/*
	 * Tests if the provided ArrayList<Student> is empty, if empty, it returns "-1",
	 * if not, it then passes the input pList to recBSearch. Once recBSearch
	 * completes, the returned int is returned to the caller of this method.
	 */
	public static int search(ArrayList<Student> pList, String pKey) {
		if (pList.size() > 0 && pKey != null) {
			return recBSearch(pList, pKey, 0, pList.size() - 1);
		}
		return -1;
	}

	/*
	 * recBSearch takes in an ArrayList<Student> as input as well as a String to be
	 * searched for and a low and high int.
	 * 
	 * Returns an ints representing the index location of the Student object in
	 * pList if found, if not found, it returns "-1".
	 */
	private static int recBSearch(ArrayList<Student> pList, String pKey, int pLow, int pHigh) {
		if (pHigh >= 1 && pLow < pHigh) {
			int mid = pLow + ((pHigh - pLow) / 2);
			if (pList.get(mid).getLastName().compareTo(pKey) == 0) {
				return mid;
			} else if (pList.get(mid).getLastName().compareTo(pKey) > 0) {
				return recBSearch(pList, pKey, pLow, mid - 1);
			} else {
				return recBSearch(pList, pKey, mid + 1, pHigh);
			}
		}
		return -1;
	}
}
