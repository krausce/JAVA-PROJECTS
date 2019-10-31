/********************************************************************************************************
 * Class: Parenthesis.java																				*
 * DESCRIPTION																							*
 * Parenthesis is the abstract super class of Left and Right Paren.										*																													*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/

/**
 * Parenthesis is the superclass of LeftParen and RightParen. These are treated as a weird sort of Operator
 * because we need to be able to push LeftParens on the operator stack when evaluating the expression.
 */
public abstract class Parenthesis extends Operator {

    public Parenthesis() {
    }
    
    /**
     * Parenthesiss are not really operators so return false.
     */
    @Override
    public boolean isBinaryOperator() {
        return false;
    }

}