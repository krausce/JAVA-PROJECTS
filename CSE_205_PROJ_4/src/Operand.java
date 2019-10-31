/********************************************************************************************************
 * Class: NegOperator.java																				*
 * DESCRIPTION																							*
 * Represents real numbers < 0.																			*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/

/**
 * An operand is a numeric value represented as a Double.
 */
public class Operand extends Token {
	private Double mValue;
	
	/*
	 * <ctor> Takes in a Double value as an arg which initializes mValue. 
	 */
	public Operand(Double pValue) {
		setValue(pValue);
	}
	
	/*
	 * Provided for debugging. 
	 */
	@Override
	public String toString() {
		return this.getValue().toString();
	}
	
	// Public accessor method for mValue.
	public Double getValue() {
		return mValue;
	}
	
	// Public mutator method for mValue.
	public void setValue(Double pValue) {
		mValue = pValue;
	}
}
