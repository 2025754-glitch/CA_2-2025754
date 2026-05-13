/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;


public enum ManagerType {

    HEAD_MANAGER("Head Manager"),
    ASSISTANT_MANAGER("Assistant Manager"),
    TEAM_LEAD("Team Lead");

    // Human-readable label displayed in the console
    private final String label;

    /**
     * Constructor for each manager type.
     * @param label the display text shown in the terminal
     */
    ManagerType(String label) {
        this.label = label;
    }

    /**
     * Returns the display label for this manager type.
     * @return the label string
     */
    public String getLabel() {
        return label;
    }
}
