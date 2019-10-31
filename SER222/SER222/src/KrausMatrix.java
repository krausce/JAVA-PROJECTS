import java.util.Arrays;

/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * 
 * Time to Completion: 
 * 
 * @author Chris Kraus, Ruben Acuna
 * @version 1.0
 */

class MatrixMismatchException extends Exception 
{ 
    public MatrixMismatchException(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 
} 

public class KrausMatrix implements Matrix {
	
	private int[][] Data;
    	
    
	public KrausMatrix(int[][] pData) {
		if ((pData == null) || (pData.length == 0)) {
			this.Data = new int[0][0];
		} else {
			this.Data = new int[pData.length][];
			for (int row = 0; row < pData.length; row++) {
				this.Data[row] = pData[row].clone();
			}
		}		
	}

	@Override
	public int getElement(int y, int x) {
		// TODO Auto-generated method stub
		return this.Data[y][x];
	}

	@Override
	public int getRows() {
		return this.Data.length;
	}

	@Override
	public int getColumns() {
		if (!(this.Data.length == 0)) {
			return this.Data[0].length;
		}
		return 0;
	}

	@Override
	public Matrix scale(int scalar) {
		int[][] scaledData = new int[this.Data.length][this.Data[0].length];
		for (int i = 0; i < this.Data.length; i++) {
			for (int j = 0; j < this.Data[i].length; j++) {
				scaledData[i][j] = this.Data[i][j] * scalar;
			}
		}
		return new KrausMatrix(scaledData);
	}

	@Override
	public Matrix plus(Matrix other) throws MatrixMismatchException {
		if (!(other.getRows() == this.getRows()) || !(other.getColumns() == this.getColumns())) {
			throw new MatrixMismatchException("Matrices must be of the same dimensions for this operation, please try again.");
		}
		int[][] summedMatrix = new int[other.getRows()][other.getColumns()];
		for (int row = 0; row < other.getRows(); row++) {
			for (int col = 0; col < other.getColumns(); col++) {
				summedMatrix[row][col] = other.getElement(row, col) + this.getElement(row, col);
			}
		}
		return new KrausMatrix(summedMatrix);
	}

	@Override
	public Matrix minus(Matrix other) throws MatrixMismatchException {
		if (!(other.getRows() == this.getRows()) || !(other.getColumns() == this.getColumns())) {
			throw new MatrixMismatchException("Matrices must be of the same dimensions for this operation, please try again.");
		}
		int[][] diffMatrix = new int[other.getRows()][other.getColumns()];
		for (int i = 0; i < other.getRows(); i++) {
			for (int j = 0; j < other.getColumns(); j++) {
				diffMatrix[i][j] = this.getElement(i, j) - other.getElement(i, j);
			}
		}
		return new KrausMatrix(diffMatrix);
	}

	@Override
	public Matrix multiply(Matrix other) throws MatrixMismatchException {
		if (!(other.getRows() == this.getRows()) || !(other.getColumns() == this.getColumns())) {
			throw new MatrixMismatchException("Matrices must be of the same dimensions for this operation, please try again.");
		}
		int[][] multMatrix = new int[other.getRows()][other.getColumns()];
		for (int i = 0; i < other.getRows(); i++) {
			for (int j = 0; j < other.getColumns(); j++) {
				multMatrix[i][j] = this.getElement(i, j) * other.getElement(i, j);
			}
		}
		return new KrausMatrix(multMatrix);
	}
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		return this.toString().contentEquals(other.toString());
	}
	
	@Override
	public String toString() {
		String strOut = "";
		for (int[] row: this.Data) {
			for (int num: row) {
				strOut += num + " ";
			}
			strOut += "\n";
		}
		
		return strOut;
	}

    
    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        
        Matrix m1 = new KrausMatrix(data1);
        Matrix m2 = new KrausMatrix(data2);
        Matrix m3 = new KrausMatrix(data3);
        Matrix m4 = new KrausMatrix(data4);
        
        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());
        
        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true
        
        //test operations (valid)
        System.out.println("2 * m2:\n" + m2.scale(2));
        try {
			System.out.println("m2 + m3:\n" + m2.plus(m3));
			System.out.println("m2 - m3:\n" + m2.minus(m3));
			//not tested... multiply(). you know what to do.
			System.out.println("m2 * m3:\n" + m2.multiply(m3));
			//System.out.println("m1 + m2" + m1.plus(m2));
	        //System.out.println("m1 - m2" + m1.minus(m2));
			
		} catch (MatrixMismatchException e) {
			System.out.println("The shapes of the two input matrices are not equal (i.e. 3 x 3 or 1 x 1 etc.), please try again.");
			e.printStackTrace();
			System.exit(-1);
		}
        
    }
}