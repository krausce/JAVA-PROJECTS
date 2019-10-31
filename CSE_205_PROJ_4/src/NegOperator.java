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
 * Represents the negation operator which is a specific type of unary operator.
 */
public class NegOperator extends UnaryOperator {
	
	/*
	 * <ctor> Initializes a NegOperator in memory with null value. 
	 */
	public NegOperator() { };

	/*
	 * Takes in an Operand (Number) argument and returns that number with a negative sign.
	 */
	@Override
	public Operand evaluate(Operand pOperand) {
		return new Operand(pOperand.getValue() * -1);		 
	}

	/*
	 * Sets the mathematical precedence.
	 */
	@Override
	public int precedence() {
		// TODO Auto-generated method stub
		return 4;
	}

	/*
	 * Sets the Stack precedence.
	 */
	@Override
	public int stackPrecedence() {
		// TODO Auto-generated method stub
		return 4;
	}

	/*
	 * Provided for debuggin purposes.
	 */
	@Override
	public String toString() {
		return " -";
	}
	
}