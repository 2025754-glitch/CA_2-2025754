/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main entry point for the CA_2 Organisation Management System.
 *
 * This program models a BANK organisation and provides a console-based menu
 * driven by Enums. It supports the following features:
 *
 *   1. SORT   — Sort the employee list alphabetically using recursive Merge Sort
 *   2. SEARCH — Search for an employee by name using recursive Binary Search
 *   3. ADD    — Add a new employee record with full input validation
 *   4. TREE   — Build and display an Employee Hierarchy using a Binary Tree
 *   5. EXIT   — Exit the application
 *
 * The program reads employee data from a CSV file (Applicants_Form.txt) on startup.
 * All data is stored in memory during the session.
 */
public class Main {

    // The master list of employees loaded from the file and/or added by the user
    private static List<Employee> employeeList = new ArrayList<>();

    // Tracks whether the list has been sorted — Binary Search requires a sorted list
    private static boolean isSorted = false;

    // The binary tree used to display the employee hierarchy
    private static EmployeeBinaryTree hierarchyTree = new EmployeeBinaryTree();

    // Scanner used for all console input throughout the program
    private static final Scanner scanner = new Scanner(System.in);

    // =========================================================================
    // ENTRY POINT
    // =========================================================================

    /**
     * Main method — entry point of the program.
     * Displays the startup banner, loads the data file, then runs the main menu.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        printBanner();
        loadFileAtStartup();
        runMainMenu();
    }

    // =========================================================================
    // STARTUP — Load data file
    // =========================================================================

    /**
     * Asks the user to enter a filename and attempts to load employee data from it.
     * If the file cannot be found or read, the program continues with an empty list.
     */
    private static void loadFileAtStartup() {
        System.out.print("\nPlease enter the filename to read: ");
        String filename = scanner.nextLine().trim();

        try {
            employeeList = FileLoader.loadFromFile(filename);
            System.out.println("File read successfully. "
                    + employeeList.size() + " records loaded.\n");
            isSorted = false;
        } catch (IOException e) {
            System.out.println("[ERROR] Could not read file: " + e.getMessage());
            System.out.println("Continuing with empty list. You can add records manually.\n");
        }
    }

    // =========================================================================
    // MAIN MENU LOOP
    // =========================================================================

    /**
     * Displays the main console menu and routes the user to the correct feature.
     * The menu options are defined by the MenuOption enum and iterated using values().
     * The loop continues until the user selects EXIT.
     */
    private static void runMainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("========================================");
            System.out.println("    BANK ORGANISATION MANAGEMENT SYSTEM  ");
            System.out.println("========================================");

            // Get all enum values and display them as numbered options
            MenuOption[] options = MenuOption.values();
            for (int i = 0; i < options.length; i++) {
                System.out.println("  " + (i + 1) + ". " + options[i].getLabel());
            }

            System.out.print("\nEnter your choice (1-" + options.length + "): ");
            int choice = readIntInRange(1, options.length);

            // Map the selected number to the corresponding enum value
            MenuOption selectedOption = options[choice - 1];

            // Route to the correct handler based on which option was selected
            switch (selectedOption) {
                case SORT              -> handleSort();
                case SEARCH            -> handleSearch();
                case ADD_RECORD        -> handleAddRecord();
                case CREATE_BINARY_TREE -> handleBinaryTree();
                case EXIT -> {
                    System.out.println("\nGoodbye! Thank you for using the Bank Organisation System.");
                    running = false;
                }
            }
        }
    }

    // =========================================================================
    // OPTION 1: SORT
    // =========================================================================

    /**
     * Sorts the employee list alphabetically by name using recursive Merge Sort,
     * then displays the first 20 names as required by the assignment.
     */
    private static void handleSort() {
        System.out.println("\nSORT selected\n");

        // Check that there is data to sort
        if (employeeList.isEmpty()) {
            System.out.println("No records to sort. Please load a file or add records first.");
            return;
        }

        // Perform the recursive merge sort on the full employee list
        AlgorithmEngine.mergeSort(employeeList);
        isSorted = true;

        System.out.println("List sorted successfully (Recursive Merge Sort).");
        System.out.println("Displaying first 20 names:\n");

        // Display up to 20 names from the sorted list
        int displayLimit = Math.min(20, employeeList.size());
        for (int i = 0; i < displayLimit; i++) {
            System.out.println("  " + (i + 1) + ". " + employeeList.get(i).toString());
        }

        // Inform the user if there are more records beyond the first 20
        if (employeeList.size() > 20) {
            System.out.println("\n  ... and " + (employeeList.size() - 20) + " more records.");
        }

        System.out.println();
    }

    // =========================================================================
    // OPTION 2: SEARCH
    // =========================================================================

    /**
     * Searches the employee list for a name entered by the user.
     * Uses recursive Binary Search (requires list to be sorted first).
     * Displays the employee's name, Manager Type, and Department if found.
     */
    private static void handleSearch() {
        System.out.println("\nSEARCH selected\n");

        // Check that there is data to search
        if (employeeList.isEmpty()) {
            System.out.println("No records to search. Please load a file or add records first.");
            return;
        }

        // Binary Search requires a sorted list — sort automatically if needed
        if (!isSorted) {
            System.out.println("List must be sorted before searching. Sorting now...");
            AlgorithmEngine.mergeSort(employeeList);
            isSorted = true;
            System.out.println("Sorted successfully.\n");
        }

        // Ask the user to enter the name they want to find
        System.out.print("Please input the name to search for: ");
        String searchName = scanner.nextLine().trim();

        // Validate that the user entered something
        if (searchName.isEmpty()) {
            System.out.println("No name entered. Returning to menu.");
            return;
        }

        // Perform the recursive binary search on the sorted list
        Employee result = AlgorithmEngine.binarySearch(employeeList, searchName);

        // Display the result of the search
        if (result != null) {
            System.out.println("\n  Record FOUND:");
            System.out.println("  " + result.toString());
        } else {
            System.out.println("\n  No record found for \"" + searchName + "\".");
        }

        System.out.println();
    }

    // =========================================================================
    // OPTION 3: ADD RECORD
    // =========================================================================

    /**
     * Allows the user to add a new employee record to the list.
     * The user must enter a name, choose a valid Manager Type from the enum,
     * and choose a valid Department from the enum.
     * All inputs are validated before the record is added.
     */
    private static void handleAddRecord() {
        System.out.println("\nADD RECORD selected\n");

        // --- Step 1: Get the employee name ---
        System.out.print("Please input the Employee Name: ");
        String name = scanner.nextLine().trim();

        // Validate that a name was entered
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty. Returning to menu.");
            return;
        }

        // --- Step 2: Choose a Manager Type from the enum ---
        System.out.println("\nPlease select from the following Management Staff:");
        ManagerType[] managerTypes = ManagerType.values();
        for (int i = 0; i < managerTypes.length; i++) {
            System.out.println("  " + (i + 1) + ". " + managerTypes[i].getLabel());
        }
        System.out.print("Enter your choice (1-" + managerTypes.length + "): ");
        int managerChoice = readIntInRange(1, managerTypes.length);
        ManagerType chosenManagerType = managerTypes[managerChoice - 1];

        // --- Step 3: Choose a Department from the enum ---
        System.out.println("\nPlease select the Department:");
        Department[] departments = Department.values();
        for (int i = 0; i < departments.length; i++) {
            System.out.println("  " + (i + 1) + ". " + departments[i].getLabel());
        }
        System.out.print("Enter your choice (1-" + departments.length + "): ");
        int departmentChoice = readIntInRange(1, departments.length);
        Department chosenDepartment = departments[departmentChoice - 1];

        // --- Step 4: Create the new Employee and add to the list ---
        Employee newEmployee = new Employee(name, chosenManagerType, chosenDepartment);
        employeeList.add(newEmployee);

        // Adding a new record means the list is no longer sorted
        isSorted = false;

        // Confirm the addition to the user (matching the format shown in the assignment)
        System.out.println("\n\"" + name + "\" has been added as \""
                + chosenManagerType.getLabel() + "\" to \""
                + chosenDepartment.getLabel() + "\" successfully!\n");

        // Display all newly added records (those at the end of the list beyond loaded data)
        System.out.println("Current total records in list: " + employeeList.size());
        System.out.println("Newly added record:");
        System.out.println("  " + newEmployee.toString());
        System.out.println();
    }

    // =========================================================================
    // OPTION 4: BINARY TREE
    // =========================================================================

    /**
     * Builds the employee hierarchy binary tree using level-order insertion.
     * Requires a minimum of 20 records as per the assignment.
     * If fewer than 20 records are loaded, default employees are added to reach the minimum.
     * Displays the full tree level by level, plus the tree height and node count.
     */
    private static void handleBinaryTree() {
        System.out.println("\nCreate Binary Tree selected\n");

        // Build the source list — use a copy of the employee list
        List<Employee> treeSource = new ArrayList<>(employeeList);

        // Ensure we have at least 20 records as required by the assignment
        if (treeSource.size() < 20) {
            System.out.println("Fewer than 20 records found. Adding default records to reach minimum of 20...");
            treeSource.addAll(getDefaultEmployees());
        }

        // Reset the tree and insert all records using level-order insertion
        hierarchyTree = new EmployeeBinaryTree();
        for (Employee emp : treeSource) {
            hierarchyTree.insert(emp);
        }

        System.out.println(treeSource.size() + " employees inserted into the hierarchy tree.");
        System.out.println("\n========== EMPLOYEE HIERARCHY (Level-Order Traversal) ==========");
        hierarchyTree.displayLevelOrder();
        System.out.println("\n=================================================================");
        System.out.println("  Tree Height : " + hierarchyTree.getHeight());
        System.out.println("  Total Nodes : " + hierarchyTree.getNodeCount());
        System.out.println("=================================================================\n");
    }

    // =========================================================================
    // DEFAULT EMPLOYEES (used when fewer than 20 records are loaded)
    // =========================================================================

    /**
     * Returns a pre-built list of 20 employees based on the data file.
     * Used to ensure the binary tree always has the minimum required 20 nodes.
     *
     * @return a list of 20 default Employee objects
     */
    private static List<Employee> getDefaultEmployees() {
        List<Employee> defaults = new ArrayList<>();
        defaults.add(new Employee("Kate Summer",       ManagerType.ASSISTANT_MANAGER, Department.IT));
        defaults.add(new Employee("Ken Winter",        ManagerType.ASSISTANT_MANAGER, Department.IT));
        defaults.add(new Employee("James Anderson",    ManagerType.HEAD_MANAGER,      Department.SALES));
        defaults.add(new Employee("Sarah Johnson",     ManagerType.HEAD_MANAGER,      Department.HR));
        defaults.add(new Employee("John Smith",        ManagerType.HEAD_MANAGER,      Department.FINANCE));
        defaults.add(new Employee("Emily Brown",       ManagerType.ASSISTANT_MANAGER, Department.MARKETING));
        defaults.add(new Employee("Michael Williams",  ManagerType.TEAM_LEAD,         Department.IT));
        defaults.add(new Employee("Sophia Taylor",     ManagerType.TEAM_LEAD,         Department.IT));
        defaults.add(new Employee("David Wilson",      ManagerType.ASSISTANT_MANAGER, Department.IT));
        defaults.add(new Employee("Olivia Martinez",   ManagerType.TEAM_LEAD,         Department.FINANCE));
        defaults.add(new Employee("Ethan Anderson",    ManagerType.ASSISTANT_MANAGER, Department.FINANCE));
        defaults.add(new Employee("Mia Clark",         ManagerType.ASSISTANT_MANAGER, Department.OPERATIONS));
        defaults.add(new Employee("John Anderson",     ManagerType.HEAD_MANAGER,      Department.SALES));
        defaults.add(new Employee("Sam Johnson",       ManagerType.HEAD_MANAGER,      Department.HR));
        defaults.add(new Employee("Benjamin Hill",     ManagerType.HEAD_MANAGER,      Department.CUSTOMER_SERVICE));
        defaults.add(new Employee("Jessica Lee",       ManagerType.HEAD_MANAGER,      Department.IT));
        defaults.add(new Employee("Daniel Walker",     ManagerType.TEAM_LEAD,         Department.OPERATIONS));
        defaults.add(new Employee("Eleanor Harris",    ManagerType.TEAM_LEAD,         Department.MARKETING));
        defaults.add(new Employee("Chris Evans",       ManagerType.TEAM_LEAD,         Department.CUSTOMER_SERVICE));
        defaults.add(new Employee("Alex Turner",       ManagerType.ASSISTANT_MANAGER, Department.IT));
        return defaults;
    }

    // =========================================================================
    // INPUT VALIDATION HELPER
    // =========================================================================

    /**
     * Reads an integer from the console and keeps prompting until the user enters
     * a valid number within the range [min, max] (inclusive).
     * This ensures that menu choices and enum selections are always valid.
     *
     * @param min the minimum acceptable value (inclusive)
     * @param max the maximum acceptable value (inclusive)
     * @return a validated integer within the specified range
     */
    private static int readIntInRange(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value; // Valid input — return it
                }

                // Number is out of the valid range — prompt again
                System.out.print("  Invalid choice. Please enter a number between "
                        + min + " and " + max + ": ");

            } catch (NumberFormatException e) {
                // User entered something that is not a number — prompt again
                System.out.print("  Invalid input. Please enter a number between "
                        + min + " and " + max + ": ");
            }
        }
    }

    // =========================================================================
    // DISPLAY HELPER
    // =========================================================================

    /**
     * Prints the application startup banner to the console.
     */
    private static void printBanner() {
        System.out.println("========================================");
        System.out.println("  BANK ORGANISATION MANAGEMENT SYSTEM   ");
        System.out.println("  CCT College Dublin - CA_2              ");
        System.out.println("  Algorithms & Constructs                ");
        System.out.println("========================================\n");
    }
}
