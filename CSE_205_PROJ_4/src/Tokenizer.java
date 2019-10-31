/********************************************************************************************************
 * Class: Tokenizer.java																				*
 * DESCRIPTION																							*
 * Takes in a String represenation of an expression and converts each token into the appropriate obj.	*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/

/**
 * The Tokenizer class scans a string containing an infix expression and breaks it into tokens. For this project,
 * a token will be either an Operand (a double value), a LeftParen or RightParen, or an arithmetic UnaryOperator
 * or BinaryOperator.
 */
public class Tokenizer {

    // These constants are used to represent the state the scanner is in.
    private static final int STATE_DOUBLE = 0;
    private static final int STATE_END    = 1;
    private static final int STATE_START  = 2;

    /**
     * mIndex is an index into mString. It keeps track of the position in the String where we are at as we
     * break the String into tokens.
     */
    private int mIndex;

    /**
     * mString is a String containing an infix expression that is is to be scanned an split into tokens.
     */
    private String mString;

    /**
     * The input parameter is a String containing an infix expression to be split into tokens.
     */
    public Tokenizer(String pString) {
        setIndex(-1);
        setString(pString);
    }

    /**
     * Accessor method for mIndex.
     */
    protected int getIndex() {
        return mIndex;
    }

    /**
     * Accessor method for mString.
     */
    protected String getString() {
        return mString;
    }

    /**
     * Returns the next token in the infix expression string being scanned. Employs a simple Finite State
     * Machine (http://en.wikipedia.org/wiki/Finite-state_machine).
     */
    public Token nextToken() {
        int state = STATE_START, nextState = STATE_START;
        StringBuffer buffer = new StringBuffer();
        Token token = null;

        while (state != STATE_END) {
            Character ch = nextChar();
            switch (state) {
            case STATE_START:
                // Check for the end of the expression string.
                if (ch == null) {
                    token = null;
                    nextState = STATE_END;

                // Is this a double literal?
                } else if (Character.isDigit(ch) || ch == '.') {
                    buffer.append(ch);
                    nextState = STATE_DOUBLE;

                // Is this the addition operator?
                } else if (ch == '+') {
                    token = new AddOperator();
                    nextState = STATE_END;

                // Is this the subtraction operator?
                } else if (ch == '-') {
                    token = new SubOperator();
                    nextState = STATE_END;

                // Is this the multiplication operator?
                } else if (ch == '*') {
                    token = new MultOperator();
                    nextState = STATE_END;

                // Is this the division operator?
                } else if (ch == '/') {
                    token = new DivOperator();
                    nextState = STATE_END;

                // Is this a left parenthesis?
                } else if (ch == '(') {
                    token = new LeftParen();
                    nextState = STATE_END;

                // Is this a right parenthesis?
                } else if (ch == ')') {
                    token = new RightParen();
                    nextState = STATE_END;
                }
                break;

            // We are scanning the characters of a double literal.
            case STATE_DOUBLE:
                // Have we reached the end of the expression string?
                if (ch == null) {
                    nextState = STATE_END;

                // As long as we keep seeing digit characters or a . we keep scanning a double literal.
                } else if (Character.isDigit(ch) || ch == '.') {
                    buffer.append(ch);
                    nextState = state;

                // We found the end of the double literal. Unget the character that we just saw and stop scanning.
                } else {
                    ungetChar();
                    nextState = STATE_END;
                }

                // We have reached the end of the double literal. Convert it into an Operand for return.
                if (nextState == STATE_END) {
                    token = new Operand(Double.parseDouble(buffer.toString()));
                }
                break;
            }
            state = nextState;
        }
        return token;
    }

    /**
     * Returns the next Character in mString and updates mIndex.
     */
    private Character nextChar() {
        Character next = null;
        if (getIndex() < getString().length() - 1) {
            setIndex(getIndex() + 1);
            next = getString().charAt(getIndex());
        }
        return next;
    }

    /**
     * Mutator method for mIndex.
     */
    protected void setIndex(int pIndex) {
        mIndex = pIndex;
    }

    /**
     * Mutator method for mString.
     */
    protected void setString(String pString) {
        mString = pString;
    }

    /**
     * Moves mIndex back to point to the previous character in mString.
     */
    private void ungetChar() {
        setIndex(getIndex() - 1);
    }

}
