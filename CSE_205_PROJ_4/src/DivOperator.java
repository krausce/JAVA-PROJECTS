/********************************************************************************************************
 * Class: DivOperator.java																				*
 * DESCRIPTION																							*
 * Represents the division operation in Math.															*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/

/**
 * Represents the division operator which is a specific type of binary operator.
 */
public class DivOperator extends BinaryOperator {

	public DivOperator() { }
	
	@Override
	public Operand evaluate(Operand pLhsOperand, Operand pRhsOperand) {
		return new Operand(pLhsOperand.getValue() / pRhsOperand.getValue());
	}

	// Sets the Mathematical precedence.
	@Override
	public int precedence() {
		return 3;
	}

	// Sets the stack precedence.
	@Override
	public int stackPrecedence() {
		return 3;
	}

	// Returns a String representation to the user for debugging.
	@Override
	public String toString() {
		return " / ";
	}
	
}