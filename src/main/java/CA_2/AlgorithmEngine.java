/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the two custom recursive algorithms required by the assignment:
 *   1. Recursive Merge Sort  — for sorting the employee list alphabetically
 *   2. Recursive Binary Search — for searching the sorted list by name
 *
 * 
 */
public class AlgorithmEngine {

    // =========================================================================
    // RECURSIVE MERGE SORT
    // =========================================================================

    /**
     * Public entry point for the recursive merge sort.
     * Sorts the given list of Employee objects alphabetically by name (A to Z).
     * The list is sorted in place.
     *
     * @param list the list of Employee objects to sort
     */
    public static void mergeSort(List<Employee> list) {
        // Base case: a list of 0 or 1 elements is already sorted
        if (list.size() <= 1) {
            return;
        }

        // Find the midpoint and split into left and right halves
        int midIndex = list.size() / 2;
        List<Employee> leftHalf  = new ArrayList<>(list.subList(0, midIndex));
        List<Employee> rightHalf = new ArrayList<>(list.subList(midIndex, list.size()));

        // Recursively sort each half
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        // Merge the two sorted halves back into the original list
        merge(list, leftHalf, rightHalf);
    }

    /**
     * Merges two sorted sublists back into one sorted list.
     * Compares names alphabetically (case-insensitive).
     *
     * @param result    the list to write the merged result into
     * @param leftHalf  the left sorted sublist
     * @param rightHalf the right sorted sublist
     */
    private static void merge(List<Employee> result,
                              List<Employee> leftHalf,
                              List<Employee> rightHalf) {
        int leftIndex  = 0; // pointer for the left half
        int rightIndex = 0; // pointer for the right half
        int resultIndex = 0; // pointer for writing into result

        // Compare elements from both halves and place the smaller one first
        while (leftIndex < leftHalf.size() && rightIndex < rightHalf.size()) {
            String leftName  = leftHalf.get(leftIndex).getName().toLowerCase();
            String rightName = rightHalf.get(rightIndex).getName().toLowerCase();

            if (leftName.compareTo(rightName) <= 0) {
                // Left name comes first alphabetically
                result.set(resultIndex++, leftHalf.get(leftIndex++));
            } else {
                // Right name comes first alphabetically
                result.set(resultIndex++, rightHalf.get(rightIndex++));
            }
        }

        // Copy any remaining elements from the left half
        while (leftIndex < leftHalf.size()) {
            result.set(resultIndex++, leftHalf.get(leftIndex++));
        }

        // Copy any remaining elements from the right half
        while (rightIndex < rightHalf.size()) {
            result.set(resultIndex++, rightHalf.get(rightIndex++));
        }
    }

    // =========================================================================
    // RECURSIVE BINARY SEARCH
    // =========================================================================

    /**
     * Public entry point for the recursive binary search.
     * Searches the sorted list for an employee with a matching name.
     * The search is case-insensitive.
     *
     * NOTE: The list must be sorted before calling this method.
     *
     * @param sortedList the sorted list of Employee objects to search in
     * @param targetName the name to search for (case-insensitive)
     * @return the matching Employee object, or null if not found
     */
    public static Employee binarySearch(List<Employee> sortedList, String targetName) {
        // Start the recursive search covering the full list
        return binarySearchRecursive(
                sortedList,
                targetName.toLowerCase().trim(),
                0,
                sortedList.size() - 1
        );
    }

    /**
     * Recursive helper method for binary search.
     * Narrows the search range by half with each recursive call.
     *
     * @param sortedList the sorted list to search
     * @param targetName the lowercase target name to find
     * @param low        the lower bound of the current search range (inclusive)
     * @param high       the upper bound of the current search range (inclusive)
     * @return the matching Employee, or null if not found
     */
    private static Employee binarySearchRecursive(List<Employee> sortedList,
                                                  String targetName,
                                                  int low,
                                                  int high) {
        // Base case: search range is empty — target not found
        if (low > high) {
            return null;
        }

        // Calculate the midpoint of the current search range
        int midIndex = (low + high) / 2;
        String midName = sortedList.get(midIndex).getName().toLowerCase();

        int comparison = midName.compareTo(targetName);

        if (comparison == 0) {
            // Found the target at the midpoint
            return sortedList.get(midIndex);

        } else if (comparison > 0) {
            // Mid name comes after target alphabetically — search the left half
            return binarySearchRecursive(sortedList, targetName, low, midIndex - 1);

        } else {
            // Mid name comes before target alphabetically — search the right half
            return binarySearchRecursive(sortedList, targetName, midIndex + 1, high);
        }
    }
}
