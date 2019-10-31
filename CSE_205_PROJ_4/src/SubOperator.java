/********************************************************************************************************
 * Class: SubOperator.java																				*
 * DESCRIPTION																							*
 * This creates an object to represent the "-" math operator.											*																													*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/

/**
 * Represents the subtraction operator which is a specific type of binary operator.
 */
public class SubOperator extends BinaryOperator {
	/*
	 *<ctor> Creates an Operand object set to null. 
	 */
	public SubOperator() { };
	
	/*
	 * Evaluates an subtraction expression returning a Double.
	 */
	@Override
	public Operand evaluate(Operand pLhsOperand, Operand pRhsOperand) {
		return new Operand(pLhsOperand.getValue() - pRhsOperand.getValue());
	}
	
	/*
	 * Returns the mathematical precedence for this operator. 
	 */
	@Override
	public int precedence() {
		return 2;
	}

	/*
	 * Returns the STACK precedence for this operator. 
	 */
	@Override
	public int stackPrecedence() {
		return 2;
	}

	/*
	 * Provides a visual for debugging.
	 */
	@Override
	public String toString() {
		return " - ";
	}
	
}