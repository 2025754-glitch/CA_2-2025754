/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;


public enum Department {

    IT("IT Development"),
    HR("HR"),
    FINANCE("Finance"),
    SALES("Sales"),
    MARKETING("Marketing"),
    OPERATIONS("Operations"),
    CUSTOMER_SERVICE("Customer Service");

    // Human-readable label displayed in the console
    private final String label;

    /**
     * Constructor for each department.
     * @param label the display text shown in the terminal
     */
    Department(String label) {
        this.label = label;
    }

    /**
     * Returns the display label for this department.
     * @return the label string
     */
    public String getLabel() {
        return label;
    }
}
