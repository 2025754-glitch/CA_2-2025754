/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author grazi
 */
public class DepartmentEntity {
    
    // The name of the departament (e.g "IT Development", "HR")
    private String departmentName;
    
    //The physical location or floor of the departament
    private String location;
    
    //The total number employees currenttly in this departament
    private int headCount;
    
    /**
     * Constructor for a DepartamentEntity
     * 
     * @param departamentName the of the department 
     * @param location the physical location (e.g "Floor 2", Dublin Office")
     * @param headCount the number of employees in this department 
     *
     */
    public DepartmentEntity(String departmentName, String location, int headCount){
        this.departmentName = departmentName;
        this.location = location;
        this.headCount = headCount;
    }
    //Getters
    
    /**Returns the department name.*/
    public String getDepartmentName(){
        return departamentName;
    }
    
    /**Returns the physical location of this department.*/
    public String getLocation(){
        return location;
    }
    /**Returns the total number of employees in this department.*/
    public int getHeadCount(){
        return headCount;
    }
     //Setters
    
    /**Sets the department name. */
    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
    
    /** Sets the physical location of this department.*/
    public void setLocation(String location) {
        this.location = location;
    }
    /** Sets the total number of employees in this department.
    */
     @Override
    public String toString() {
        return String.format("Department: %-20s | Location: %-15s | Head Count: %d",
                departmentName, location, headCount);
    }
}
