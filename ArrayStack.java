import java.util.EmptyStackException;
public class ArrayStack implements BKStack {
    private double[] stackArray;
    private int top;

    /**
     * Constructor that initializes the array with an initial of 10, and sets the top pointer to 0
     */
    public ArrayStack() {
        int INITIAL_SIZE = 10;
        stackArray = new double[INITIAL_SIZE];
        top = 0; // pointer to the "top" of the stack
    }

    /**
     * Method that checks if the stack (in this case, the array) is empty
     *
     * @return boolean, true if empty
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Method that determines how many elements are in the stack (in this case, the array)
     *
     * @return integer representing the size of the stack (# of elements)
     */
    @Override
    public int count() {
        return top;
    }

    /**
     * Method that adds another element to the top of the stack
     *
     * @param d double representing the data that will be pushed to the stack
     */
    @Override
    public void push(double d) {
        if (top == stackArray.length) {
            resizeArray(); // resize the array if it hits the end of the array
        }
        stackArray[top++] = d; // push the element onto the stack, then increment the top pointer
    }

    /**
     * Method that removes (pops) an element from the top of the stack
     *
     * @return a double representing the element that was removed
     */
    @Override
    public double pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray[--top]; // move the top pointer first, then return that element
    }

    /**
     * Method that shows the element at the top of the stack without removing (popping) it
     *
     * @return double representing the element that was looked at
     */
    @Override
    public double peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stackArray[top - 1]; // return the element at the top - 1 as the top points to the insertion position
    }

    /**
     * private method that resizes the array by creating a new one, doubling the size,
     * and copying the data over
     *
     */
    private void resizeArray() {
        double[] newArr = new double[stackArray.length * 2];
        for (int i = 0; i < stackArray.length; i++) {
            newArr[i] = stackArray[i];
        }
        stackArray = newArr;
    }
}
