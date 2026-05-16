/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *This class is extended by three subtypes
 * -HeadManager
 * -AssistantManager
 * -TeamLead
 * 
 * @author grazi
 */
public class Manager extends Employee {
    
    //The number of staff members this manager oversees
    private int teamSize;
    
    /**
     * Constructor to create a Manager.
     * 
     * @param name  the manager`s full name
     * @param managerType the specific role level of this manager
     * @param department the department this manager belongs to
     * @param teamSize the number of people this manager is responsible for 
     */
    
    public Manager(String name, ManagerType managerType, Department department, int teamSize) {
        super(name, managerType, department);
        this.teamSize = teamSize;
    }

    //Getter and Setter
    
    
    /**Returns the number of staff this manager oversees. */
    public int getTeamSize(){
        return teamSize;
    }
    
     /** Sets the number of staff this manager oversees. */
    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
    /**
     * Returns a formatted string including parent Employee infor plus team size.
     */
          @Override
    public String toString() {
        return super.toString() + String.format(" | Team Size: %d", teamSize);
    }
}