/**
 * I Devang Bhojane, 000901300 certify that this is my original work. I have not
 * used any uncited exernal resources and I have not shared my work with anyone else.
 * I did not use ChatGPT or any other AI based tool.
 */

public class SortedLinkedList<T extends Comparable<T>> {

    private class Node {
        T value;
        Node next;

        Node(T value, Node next) { // Node with reference
            this.value = value;
            this.next = next;
        }

        Node(T value) { // Node without reference
            this(value, null);
        }
    }

    private Node first;  // Head of list

    public SortedLinkedList() {
        first = null; // Initialize empty list
    }

    public boolean isEmpty() {
        return first == null; // Check if list is empty
    }

    public int size() {
        int count = 0; // Count nodes
        Node p = first;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    public void add(T element) {
        Node newNode = new Node(element); // Create new node

        if (first == null || element.compareTo(first.value) <= 0) { // Add at head
            newNode.next = first;
            first = newNode;
        } else { // Find correct position
            Node current = first;
            while (current.next != null && element.compareTo(current.next.value) > 0) {
                current = current.next;
            }
            newNode.next = current.next; // Insert at position
            current.next = newNode;
        }
    }

    public T get(int index) {
        Node current = first; // Traverse to index
        for (int i = 0; i < index; i++) {
            if (current == null) throw new IndexOutOfBoundsException();
            current = current.next;
        }
        if (current == null) throw new IndexOutOfBoundsException(); // Check bounds
        return current.value;
    }

    public boolean remove(T element) {
        if (first == null) return false; // Handle empty list

        if (first.value.equals(element)) { // Remove head
            first = first.next;
            return true;
        }

        Node current = first; // Find element
        while (current.next != null && !current.next.value.equals(element)) {
            current = current.next;
        }

        if (current.next == null) return false; // Element not found

        current.next = current.next.next; // Remove element
        return true;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("["); // Build string
        Node current = first;
        while (current != null) {
            result.append(current.value).append(",");
            current = current.next;
        }
        if (result.length() > 1) result.setLength(result.length() - 1); // Remove trailing comma
        result.append("]");
        return result.toString();
    }
}

