/********************************************************************************************************
 * Class: AddOperator.java																				*
 * DESCRIPTION																							*
 * This creates an object to represent the "+" math operator.											*																													*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/

/**
 * Represents the addition operator which is a specific type of binary operator.
 */
public class AddOperator extends BinaryOperator {

    public AddOperator() {
    }

    /**
     * Returns the sum of the left-hand side operand and the right-hand side operand.
     */
    @Override
    public Operand evaluate(Operand pLhsOperand, Operand pRhsOperand) {
        return new Operand(pLhsOperand.getValue() + pRhsOperand.getValue());
    }

    /**
     * Returns the normal precedence level of this operator.
     */
    @Override
    public int precedence() {
        return 2;
    }

    /**
     * Returns the precedence level of this operator when on it is on the operator stack.
     */
    @Override
    public int stackPrecedence() {
        return 2;
    }

	@Override
	public String toString() {
		return " + ";
	}

}