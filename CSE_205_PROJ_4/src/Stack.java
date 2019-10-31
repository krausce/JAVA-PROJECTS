/********************************************************************************************************
 * Class: Stack<E> Stack.java																			*
 * DESCRIPTION																							*
 * Stack is used to implement the "Stack" methodology of data flow management.							*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/

/**
 * Implements a generic stack data structure using a DList to store the elements.
 */
public class Stack<E> {

    private DList<E> mList;

    /**
     * Creates a new empty Stack by creating a new empty DList.
     */
    public Stack() {
        setList(new DList<>());
    }

    /**
     * Removes all of the elements from this Stack. After clear() returns this Stack is empty.
     */
    public void clear() {
        getList().clear();
    }

    /**
     * Accessor method for mList.
     */
    protected DList<E> getList() {
        return mList;
    }

    /**
     * Returns true if this Stack is empty.
     */
    public boolean isEmpty() {
        return getList().isEmpty();
    }

    /**
     * Returns the top element on this Stack without removing it.
     */
    public E peek() {
        return getList().get(0);
    }

    /**
     * Removes the top element from this Stack and returns it.
     */
    public E pop() {
        E top = getList().remove(0);
        return top;
    }

    /**
     * Pushes pData onto the top of this Stack.
     */
    public void push(E pData) {
        getList().prepend(pData);
    }

    /**
     * Mutator method for mList.
     */
    protected void setList(DList<E> pList) {
        mList = pList;
    }

    /**
     * Overrides toString() inherited from Object. Returns a String representation of the elements
     * of this Stack by calling the DList.toString() method.
     */
    @Override
    public String toString() {
        return getList().toString();
    }
}
