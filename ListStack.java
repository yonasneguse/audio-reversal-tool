import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class ListStack implements BKStack, Iterable<Double> {
    private Node top; // Node representing the top node (pointer)
    private int modCount; // mod count for iterator

    /**
     * Constructor that initializes the top pointer to null and the mod count to 0
     */
    public ListStack() {
        top = null;
        modCount = 0;
    }

    /**
     * Method that checks if the stack (in this case, the linked list) is empty
     *
     * @return boolean, true if empty
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Method that counts the number of elements in the stack (linked list). This method uses a for each loop
     * to count through the elements in the list, implementing the iterable methods
     *
     * @return integer representing how many elements are in the list
     */

    @Override
    public int count() {
        int count = 0;

        for (double e: this) { // for each loop counting through the linked list
            count++;
        }

        return count;
    }

    /**
     * Method that adds another element to the top of the stack
     *
     * @param d double representing the data that the node will contain when pushed to the stack
     */
    @Override
    public void push(double d) {
        Node newNode = new Node(d, top);
        top = newNode;

        modCount++; // increment the modCount for the iterator
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

        double val = top.data; // the element that is going to be removed is the current top node
        top = top.next;

        modCount++;
        return val;

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
        return top.data; // top pointer's data
    }

    /**
     * Iterator method implemented from the interface
     *
     * @return an Object of type ListStackIterator
     */
    @Override
    public Iterator<Double> iterator() {
        return new ListStackIterator();
    }

    // private embedded class for the Nodes
    private static class Node {
        private double data;
        private Node next;

        // constructor for the Node, including the next and prev pointers
        private Node(double d, Node n) {
            this.data = d;
            next = n;
        }
    }

    // private embedded class for the Iterator

    private class ListStackIterator implements Iterator<Double> {
        private Node curr; // current node
        private final int expectedModCount; // expected mod count to compare to the real mod count

        // constructor for the class
        private ListStackIterator() {
            curr = top; // current is the top
            expectedModCount = modCount; // expected mod count should be equal to the mod count
        }

        /**
         * Iterator method that determines whether the list has another element after it
         *
         * @return boolean, true if there is another element in the list
         */
        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) { // if the mod count differs from the expected mod count
                throw new ConcurrentModificationException();
            }
            return curr != null; // returns true if there is another element
        }

        /**
         * Iterator method that traverses the spot once, and returns the element that the iterator just passed
         * over
         *
         * @return double representing the element the iterator passed over
         */
        @Override
        public Double next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            double val = curr.data;
            curr = curr.next; // move further down the stack (the way my linkedlist is built)

            return val;
        }
    }
}