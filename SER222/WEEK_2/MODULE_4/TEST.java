int i=0, j=0;

do {
   
    do {
        System.out.println("...looping...");  //growth should be measured in calls to println. 

        j=j+5;

        } while (j < n);
        i++; 
        j++;
    } while (i < n);