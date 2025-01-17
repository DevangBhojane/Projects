import java.util.ArrayList;

/**
 * I Devang Bhojane, 000901300 certify that this is my original work. I have not
 * used any uncited exernal resources and I have not shared my work with anyone else.
 * I did not use ChatGPT or any other AI based tool.
 */

public class SortedArray<T extends Comparable<T>> {

    private ArrayList<T> array;

    /**
     * Constructor.
     */
    public SortedArray() {
        array = new ArrayList<>();
    }

    /**
     * The isEmpty method checks to see if the list is empty.
     *
     * @return true if list is empty, false otherwise return true
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * The size method returns the length of the list.
     *
     * @return The number of elements in the list.
     */
    public int size() {
        return array.size();
    }

    /**
     * The add method adds an element at the sorted position in the list.
     *
     * @param element The element to add to the list in sorted order.
     */
    public void add(T element) {
        // Find the correct position to insert the element
        int index = 0;
        while (index < array.size() && element.compareTo(array.get(index)) > 0) {
            index++;
        }
        array.add(index, element);
    }

    /**
     * Return the item from the list at the given index.
     *
     * @param index the elements index
     * @return the item at the given index
     */
    public T get(int index) {
        return array.get(index);
    }

    /**
     * The toString method computes the string representation of the list.
     *
     * @return The string form of the list.
     */
    public String toString() {
        return array.toString();
    }

    /**
     * The remove method removes an element.
     *
     * @param element The element to remove.
     * @return true if the remove succeeded, false otherwise.
     */
    public boolean remove(T element) {
        return array.remove(element);
    }
}
