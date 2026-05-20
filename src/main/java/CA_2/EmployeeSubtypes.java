/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

// =============================================================================
// Three subtypes of the Employee parent class.
// Each subtype adds one extra attribute to specialise the Employee.
// =============================================================================

/**
 * Subtype 1 of Employee: A full-time permanent employee of the organisation.
 * Adds yearsOfService to track how long this employee has been with the company.
 */
class PermanentEmployee extends Employee {

    // Number of years this employee has worked in the organisation
    private int yearsOfService;

    /**
     * Constructor for a PermanentEmployee.
     *
     * @param name           the employee's full name
     * @param managerType    the role level of this employee
     * @param department     the department this employee belongs to
     * @param yearsOfService how many years this employee has been with the company
     */
    public PermanentEmployee(String name, ManagerType managerType,
                             Department department, int yearsOfService) {
        super(name, managerType, department);
        this.yearsOfService = yearsOfService;
    }

    /** Returns the number of years this employee has worked here. */
    public int getYearsOfService() {
        return yearsOfService;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Permanent | Years of Service: " + yearsOfService;
    }
}

// -----------------------------------------------------------------------------

/**
 * Subtype 2 of Employee: A contract-based employee with a fixed end date.
 * Adds contractEndDate to track when their contract expires.
 */
class ContractEmployee extends Employee {

    // The date when this employee's contract ends (stored as a String, e.g. "31/12/2025")
    private String contractEndDate;

    /**
     * Constructor for a ContractEmployee.
     *
     * @param name            the employee's full name
     * @param managerType     the role level of this employee
     * @param department      the department this employee belongs to
     * @param contractEndDate the date the contract expires
     */
    public ContractEmployee(String name, ManagerType managerType,
                            Department department, String contractEndDate) {
        super(name, managerType, department);
        this.contractEndDate = contractEndDate;
    }

    /** Returns the contract end date for this employee. */
    public String getContractEndDate() {
        return contractEndDate;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Contract | Contract Ends: " + contractEndDate;
    }
}

// -----------------------------------------------------------------------------

/**
 * Subtype 3 of Employee: A part-time employee working reduced hours.
 * Adds hoursPerWeek to track how many hours they work each week.
 */
class PartTimeEmployee extends Employee {

    // The number of hours per week this employee works
    private int hoursPerWeek;

    /**
     * Constructor for a PartTimeEmployee.
     *
     * @param name         the employee's full name
     * @param managerType  the role level of this employee
     * @param department   the department this employee belongs to
     * @param hoursPerWeek the number of hours worked per week
     */
    public PartTimeEmployee(String name, ManagerType managerType,
                            Department department, int hoursPerWeek) {
        super(name, managerType, department);
        this.hoursPerWeek = hoursPerWeek;
    }

    /** Returns the number of hours per week this employee works. */
    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Part-Time | Hours/Week: " + hoursPerWeek;
    }
}


