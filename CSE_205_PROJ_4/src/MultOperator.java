/********************************************************************************************************
 * Class: MultOperator.java																				*
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
 * Represents the multiplication operator which is a specific type of binary operator.
 */
public class MultOperator extends BinaryOperator {

	public MultOperator() { }
	
	/*
	 * Takes in two Operand args and returns a new Operand based on their product.
	 */
	@Override
	public Operand evaluate(Operand pLhsOperand, Operand pRhsOperand) {
		return new Operand(pLhsOperand.getValue() * pRhsOperand.getValue());
	}
	// Sets the Mathematical precedence for multiplication.
	@Override
	public int precedence() {
		return 3;
	}
	// Sets the stack precedence for multiplication.
	@Override
	public int stackPrecedence() {
		return 3;
	}
	// Provided for debugging.
	@Override
	public String toString() {
		return " * ";
	}
	
}