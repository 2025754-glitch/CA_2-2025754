/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;


public class Employee {

    // The full name of the employee (first + last name)
    private String name;

    // The management role of this employee (e.g. Head Manager, Team Lead)
    private ManagerType managerType;

    // The department this employee belongs to (e.g. IT, HR, Finance)
    private Department department;

    /**
     * Constructor to create a new Employee.
     *
     * @param name        the employee's full name
     * @param managerType the manager type / role level of this employee
     * @param department  the department the employee belongs to
     */
    public Employee(String name, ManagerType managerType, Department department) {
        this.name = name;
        this.managerType = managerType;
        this.department = department;
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /** Returns the employee's full name. */
    public String getName() {
        return name;
    }

    /** Returns the manager type / role of this employee. */
    public ManagerType getManagerType() {
        return managerType;
    }

    /** Returns the department this employee belongs to. */
    public Department getDepartment() {
        return department;
    }

    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------

    /** Sets the employee's full name. */
    public void setName(String name) {
        this.name = name;
    }

    /** Sets the manager type / role of this employee. */
    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    /** Sets the department this employee belongs to. */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Returns a formatted string representation of this employee.
     * Used when displaying search results and sorted lists in the terminal.
     */
    @Override
    public String toString() {
        return String.format("Name: %-25s | Role: %-20s | Department: %s",
                name, managerType.getLabel(), department.getLabel());
    }
}
