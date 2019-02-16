import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TrialGroup {
	
	private String fileName;	
	private int count = 0;
	private int sum = 0;
	private double average = 0;
	private int sumOfSquares = 0;
	private double standardDeviation = 0;
	
	@Override
	public String toString() {
	  return getClass().getSimpleName() + "[fileName = " + fileName + "]";
	}
	
	public TrialGroup(String fileName) throws IOException {
		this.fileName = fileName;
		int[] data = readFile(fileName);
        setCount(data);
		setSum(data);			
		setAverage();
		setSumOfSquares(data);
		setStandardDeviation();
	}
	
	private static int [] readFile(String fileName) throws IOException {
		File data = new File(fileName);
        Scanner file_input_1 = new Scanner(data);
        int counter = 0;
        while(file_input_1.hasNextInt()) {
        	counter++;
        	file_input_1.nextInt();
        }
        file_input_1.close();
        Scanner file_input_2 = new Scanner(data);
        int[] intsArray = new int[counter];
        for(int i = 0; i < intsArray.length; i++) {
        	intsArray[i] = file_input_2.nextInt();
        }
        file_input_2.close();
        return intsArray;
    }
    
    public void setCount(int[] data) {
    	BigInteger temp = new BigInteger(Integer.toString(data.length));
    	this.count = temp.intValue();
    }
    
    public void setSum(int[] data) {
    	BigInteger temp = new BigInteger(Integer.toString(IntStream.of(data).sum()));
    	this.sum = temp.intValue();
    }
    
    public void setAverage() {
    	BigDecimal temp = new BigDecimal((double) sum/count);
    	this.average = temp.doubleValue();
    }
    
    public void setSumOfSquares(int[] data) {
    	int[] dataClone = data.clone();
        for(int i = 0; i < dataClone.length; i++) {
        	dataClone[i] = data[i]*data[i];
        }
        BigInteger temp = new BigInteger(Integer.toString(IntStream.of(dataClone).sum()));
        this.sumOfSquares = temp.intValue();
    }
    
    public void setStandardDeviation() {
        BigDecimal squareOfAverage = new BigDecimal(Double.toString((this.average * this.average)));
        BigDecimal averageOfSquares = new BigDecimal(Double.toString(((double) this.sumOfSquares / this.count)));
        this.standardDeviation = Math.sqrt((averageOfSquares.subtract(squareOfAverage).doubleValue()));
    }
    
    public double getAverage() {
    	return this.average;
    }

    public double getStandardDeviation() {
    	return this.standardDeviation;
    }
    
    public String getFileName() {
    	return this.fileName;
    }
    
    public double getSum() {
    	return this.sum;
    }

	public int getCount() {
		return this.count;
	}
}
