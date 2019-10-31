/********************************************************************************************************
 * Class: Token.java																					*
 * DESCRIPTION																							*
 * Serves as the super class for Operators and Operands.												*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/

/**
 * Token is the abstract superclass of the different types of tokens that can appear in an infix expression.
 */
public abstract class Token {
    public Token() { }
    
    public abstract String toString();
}
