/********************************************************************************************************
 * Class: LeftParen.java																				*
 * DESCRIPTION																							*
 * LeftParen is used to signify the opening of an expression in Math. Thus this object is created		*
 * for the same purpose within the program.																*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/
/**
 * Represents a left parenthesis in the expression.
 */
public class LeftParen extends Parenthesis {

    public LeftParen() {
    }

    /**
     * Returns the normal precedence level of LeftParen which is highest at 5.
     */
    @Override
    public int precedence() {
        return 5;
    }

    /**
     * Returns the precedence level of LeftParen when on it is on the operator stack, it is lowest at 0.
     */
    @Override
    public int stackPrecedence() {
        return 0;
    }

	@Override
	public String toString() {
		return "(";
	}

}