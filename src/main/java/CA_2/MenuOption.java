/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 * Enum representing the main menu options available to the user.
 * Using an Enum ensures the menu is structured, type-safe, and easy to iterate.
 * The menu is displayed by looping through the enum values in Main.java.
 */
public enum MenuOption {

    SORT("Sort Applicants List"),
    SEARCH("Search in the List"),
    ADD_RECORD("Add New Employee Record"),
    CREATE_BINARY_TREE("Create Employee Hierarchy (Binary Tree)"),
    EXIT("Exit");

    // Label shown to the user in the console menu
    private final String label;

    /**
     * Constructor for each menu option.
     * @param label the display text shown in the terminal menu
     */
    MenuOption(String label) {
        this.label = label;
    }

    /**
     * Returns the display label for this menu option.
     * @return the label string
     */
    public String getLabel() {
        return label;
    }
}
