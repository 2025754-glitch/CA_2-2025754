/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

// =============================================================================
// Three subtypes of the DepartmentEntity parent class.
// Each subtype adds one extra attribute to specialise the department type.
// =============================================================================

/**
 * Subtype 1 of DepartmentEntity: A customer-facing department.
 * Examples: Customer Service, Sales.
 * Adds serviceHours to track when this department is available to customers.
 */
class CustomerFacingDepartment extends DepartmentEntity {

    // The hours during which this department serves customers (e.g. "9am - 5pm")
    private String serviceHours;

    /**
     * Constructor for a CustomerFacingDepartment.
     *
     * @param departmentName the name of the department
     * @param location       the physical location of the department
     * @param headCount      the number of employees in this department
     * @param serviceHours   the hours this department is open to customers
     */
    public CustomerFacingDepartment(String departmentName, String location,
                                    int headCount, String serviceHours) {
        super(departmentName, location, headCount);
        this.serviceHours = serviceHours;
    }

    /** Returns the service hours for this customer-facing department. */
    public String getServiceHours() {
        return serviceHours;
    }

    @Override
    public String toString() {
        return super.toString() + " | Service Hours: " + serviceHours;
    }
}

// -----------------------------------------------------------------------------

/**
 * Subtype 2 of DepartmentEntity: A back-office department focused on internal operations.
 * Examples: HR, Finance, Operations.
 * Adds remoteFriendly to indicate if this department supports remote working.
 */
class BackOfficeDepartment extends DepartmentEntity {

    // Whether this department supports remote / work-from-home arrangements
    private boolean remoteFriendly;

    /**
     * Constructor for a BackOfficeDepartment.
     *
     * @param departmentName the name of the department
     * @param location       the physical location of the department
     * @param headCount      the number of employees in this department
     * @param remoteFriendly true if this department supports remote working
     */
    public BackOfficeDepartment(String departmentName, String location,
                                int headCount, boolean remoteFriendly) {
        super(departmentName, location, headCount);
        this.remoteFriendly = remoteFriendly;
    }

    /** Returns true if this department supports remote working. */
    public boolean isRemoteFriendly() {
        return remoteFriendly;
    }

    @Override
    public String toString() {
        return super.toString() + " | Remote Friendly: " + (remoteFriendly ? "Yes" : "No");
    }
}

// -----------------------------------------------------------------------------

/**
 * Subtype 3 of DepartmentEntity: A technical/specialised department.
 * Examples: IT Development, Marketing.
 * Adds primaryTechnology to describe the main tools or technologies used.
 */
class TechnicalDepartment extends DepartmentEntity {

    // The primary technology or tool stack used in this department (e.g. "Java", "AWS")
    private String primaryTechnology;

    /**
     * Constructor for a TechnicalDepartment.
     *
     * @param departmentName    the name of the department
     * @param location          the physical location of the department
     * @param headCount         the number of employees in this department
     * @param primaryTechnology the main technology stack used
     */
    public TechnicalDepartment(String departmentName, String location,
                               int headCount, String primaryTechnology) {
        super(departmentName, location, headCount);
        this.primaryTechnology = primaryTechnology;
    }

    /** Returns the primary technology used in this department. */
    public String getPrimaryTechnology() {
        return primaryTechnology;
    }

    @Override
    public String toString() {
        return super.toString() + " | Technology: " + primaryTechnology;
    }
}
