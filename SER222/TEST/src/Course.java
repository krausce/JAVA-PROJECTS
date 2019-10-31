
public class Course implements Comparable {
    private String name;
    private String prefix;
    private String prof;
    private int number;


    public Course(String p, int n){
        prefix = p.toUpperCase();
        number = n;
    }

    //assume getters and setters
    //for all private attributes
    //are here

    //pretend other fancy Course methods here too

    public String toString(){
        return prefix + " " + number;
    }
    
    public int compareTo(Comparable that) {
    	if (this.getPrefix().compareTo(that.getPrefix()) < 0) { return -1;}
    	if (this.getPrefix().compareTo(that.getPrefix()) > 0) { return 1;}
    	if (this.getNumber().compareTo(that.getNumber()) < 0) { return -1;}
    	if (this.getNumber().compareTo(that.getNumber()) > 0) { return 1;}
    	return 0;
    }
}
