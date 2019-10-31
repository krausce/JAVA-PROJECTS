/********************************************************************************************************
 * Class: Parenthesis.java																				*
 * DESCRIPTION																							*
 * Operator is the abstract super class of for the all Math operators.									*																													*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/

/**
 * Operator is the superclass of all binary and unary operators.
 */
public abstract class Operator extends Token{
	public Operator() {}
	
	public abstract boolean isBinaryOperator();
	
	public abstract int precedence();
	
	public abstract int stackPrecedence();
	
	public abstract String toString();
}
