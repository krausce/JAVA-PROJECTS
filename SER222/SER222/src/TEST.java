
public class TEST {
	
	public void TEST () {
		
	}
	
	public static void main (String [] args) {
		int i=0, j=0, n=10, count=0;

		do {
		   
		    do {
		        System.out.println("...looping...");  //growth should be measured in calls to println. 
		        count++;
		        j=j+5;

		        } while (j < n);
		        i++; j++;
		    } while (i < n);
		System.out.println("\n" + count);
	}
}


