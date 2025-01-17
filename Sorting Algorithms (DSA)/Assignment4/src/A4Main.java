import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * I Devang Bhojane, 000901300 certify that this is my original work. I have not
 * used any uncited exernal resources and I have not shared my work with anyone else.
 * I did not use ChatGPT or any other AI based tool.
 */

/**
 * Write answers to assignment questions here:
 *  ArrayList (built-in)   -  3,509 ms
 *  Primitive Array (iSort)-  3,472 ms
 *  SortedArray            -  285 ms
 *  SortedLinkedList       -  665 ms
 *
 *  Performance Differences: LinkedList is slower to find items but faster to add; Array is faster to find but slower to add for big data.
 * Use Cases: LinkedList is better for many adds/removes; Array is better for quick look.
 * LinkedList vs ArrayList: LinkedList is faster because it keeps items sorted as you add, while ArrayList keeps re-sorting everything.
 * iSort vs Built-in Sort: iSort is much slower for big data because it’s simpler and less efficient than the built-in sort.
 *
 *
 */

public class A4Main {
    /**
     * Main entry point.
     */
    public static void main(String[] args) {
        runPartA(); // Part A tests
        runPartB(); // Part B performance
    }

    /**
     * Check if sorted.
     */
    public static int ckSumSorted(String[] array) {
        if (array.length == 0) // Handle empty
            return 1;

        int sum = 0;
        int i = 0;
        String prev = array[0]; // Store first element
        for (String str : array) {
            if (str.compareTo(prev) < 0) // Check order
                return -1;
            i++;
            sum += str.hashCode() * i; // Update checksum
            prev = str;
        }
        return Math.abs(sum % 1000_000); // Return checksum
    }

    /**
     * Validate structures.
     */
    public static void runPartA() {
        System.out.println("\n\nPart A --- \n");

        // Initialize lists
        SortedLinkedList<String> linkedList = new SortedLinkedList<>();
        SortedArray<String> sortedArray = new SortedArray<>();

        // Test data
        String[] names = {"Bob", "Carol", "Aaron", "Alex", "Zaphod"};

        System.out.println("Initial Array = " + Arrays.toString(names));
        System.out.printf("Initial Array sorted = %,d\n", ckSumSorted(names));

        // Add elements
        for (String name : names) {
            linkedList.add(name);
            sortedArray.add(name);
        }

        // Display LinkedList
        System.out.println("LinkedList: " + linkedList);
        String[] sl = {linkedList.get(0), linkedList.get(1), linkedList.get(2), linkedList.get(3), linkedList.get(4)};
        System.out.println(Arrays.toString(sl));
        System.out.printf("LinkedList ckSumSorted = %,d\n\n", ckSumSorted(sl));

        // Display SortedArray
        System.out.println("SortedArray: " + sortedArray);
        String[] sa = {sortedArray.get(0), sortedArray.get(1), sortedArray.get(2), sortedArray.get(3), sortedArray.get(4)};
        System.out.println(Arrays.toString(sa));
        System.out.printf("SortedArray ckSumSorted = %,d\n", ckSumSorted(sa));

        // Remove elements
        for (String name : names) {
            linkedList.remove(name);
        }
        linkedList.remove("Karen");
        System.out.println("LinkedList after removals: " + linkedList);
    }

    /**
     * Insertion sort.
     */
    public static void iSort(String[] array, int n) {
        String unsortedValue;
        int scan;
        for (int index = 1; index < n; index++) {
            unsortedValue = array[index];
            scan = index;
            while (scan > 0 && array[scan - 1].compareTo(unsortedValue) > 0) {
                array[scan] = array[scan - 1]; // Shift elements
                scan--;
            }
            array[scan] = unsortedValue; // Insert element
        }
    }

    /**
     * Performance tests.
     */
    public static void runPartB() {
        ArrayList<String> bnames = new ArrayList<>();
        long startTime;

        System.out.println("\n\nPart B --- \n");

        // Read file
        startTime = System.currentTimeMillis();
        try (Scanner scanner = new Scanner(new File("src/bnames.txt"))) {
            while (scanner.hasNextLine()) {
                bnames.add(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            return;
        }
        long endTime = System.currentTimeMillis();
        String[] sa = new String[bnames.size()];
        for (int i = 0; i < bnames.size(); i++)
            sa[i] = bnames.get(i);
        System.out.printf("bnames.txt ckSumSorted = %,d\n", ckSumSorted(sa));
        System.out.printf("Read file bnames.txt: %,d items read in %,d ms\n\n",
                bnames.size(), (endTime - startTime));

        // ArrayList performance
        ArrayList<String> sortedAL = new ArrayList<>();
        startTime = System.currentTimeMillis();
        for (String name : bnames) {
            sortedAL.add(name);
            sortedAL.sort(String::compareTo); // Sort after add
        }
        endTime = System.currentTimeMillis();
        System.out.printf("ArrayList added %,d items\n", sortedAL.size());
        for (int i = 0; i < sortedAL.size(); i++)
            sa[i] = sortedAL.get(i);
        System.out.printf("ArrayList ckSumSorted = %,d\n", ckSumSorted(sa));
        System.out.printf("Time to build sorted ArrayList (sort after each .add()): %,d ms\n\n", endTime - startTime);

        // Primitive array performance
        String[] sortedArray = new String[bnames.size()];
        startTime = System.currentTimeMillis();
        for (int i = 0; i < bnames.size(); i++) {
            sortedArray[i] = bnames.get(i);
            iSort(sortedArray, i + 1); // Sort first i+1 elements
        }
        endTime = System.currentTimeMillis();
        System.out.printf("Primitive Array added %,d items\n", bnames.size());
        System.out.printf("Primitive Array ckSumSorted = %,d\n", ckSumSorted(sortedArray));
        System.out.printf("Time to build sorted Primitive Array (iSort after each .add()): %,d ms\n\n", endTime - startTime);

        // SortedArray performance
        SortedArray<String> sortedArrayList = new SortedArray<>();
        startTime = System.currentTimeMillis();
        for (String name : bnames) {
            sortedArrayList.add(name);
        }
        endTime = System.currentTimeMillis();
        System.out.printf("SortedArray added %,d items\n", bnames.size());
        for (int i = 0; i < bnames.size(); i++) {
            sa[i] = sortedArrayList.get(i);
        }
        System.out.printf("SortedArray ckSumSorted = %,d\n", ckSumSorted(sa));
        System.out.printf("Time to build SortedArray: %,d ms\n\n", endTime - startTime);

        // SortedLinkedList performance
        SortedLinkedList<String> sortedLinkedList = new SortedLinkedList<>();
        startTime = System.currentTimeMillis();
        for (String name : bnames) {
            sortedLinkedList.add(name);
        }
        endTime = System.currentTimeMillis();
        System.out.printf("SortedLinkedList added %,d items\n", bnames.size());
        for (int i = 0; i < bnames.size(); i++) {
            sa[i] = sortedLinkedList.get(i);
        }
        System.out.printf("SortedLinkedList ckSumSorted = %,d\n", ckSumSorted(sa));
        System.out.printf("Time to build SortedLinkedList: %,d ms\n\n", endTime - startTime);
    }
}