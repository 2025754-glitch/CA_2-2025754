/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileLoader {

    /**
     * Reads the CSV file at the given path and returns a list of Employee objects.
     *
     * @param filePath the path to the CSV data file (e.g. "Applicants_Form.txt")
     * @return a list of Employee objects loaded from the file
     * @throws IOException if the file cannot be found or read
     */
    public static List<Employee> loadFromFile(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // Used to skip the header row
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Skip the header row (first line of the CSV)
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Split the line by comma into individual fields
                String[] fields = line.split(",");

                // We need at least 8 columns to read the required fields
                if (fields.length < 8) {
                    System.out.println("  [WARNING] Skipping malformed line " + lineNumber
                            + ": \"" + line + "\"");
                    continue;
                }

                try {
                    // Combine first and last name into a single full name string
                    String firstName  = fields[0].trim();
                    String lastName   = fields[1].trim();
                    String fullName   = firstName + " " + lastName;

                    // fields[2] = Gender  (not used)
                    // fields[3] = Email   (not used)
                    // fields[4] = Salary  (not used)

                    String departmentStr = fields[5].trim(); // e.g. "IT Development"
                    String positionStr   = fields[6].trim(); // e.g. "senior", "intern", ""

                    // fields[7] = Job title (not used)
                    // fields[8] = Company  (not used, optional)

                    // Map the text values to enum values
                    ManagerType managerType = parseManagerType(positionStr);
                    Department  department  = parseDepartment(departmentStr);

                    // Create and store the Employee object
                    employees.add(new Employee(fullName, managerType, department));

                } catch (Exception e) {
                    System.out.println("  [WARNING] Could not parse line " + lineNumber
                            + " — skipping. (" + e.getMessage() + ")");
                }
            }
        }

        return employees;
    }

    /**
     * Maps the "Position" string from the CSV to a ManagerType enum value.
     *
     * Mapping rules:
     *   blank / "manager" / "head manager" / "senior manager" → HEAD_MANAGER
     *   "senior" / "middle" / "assistant manager" / "team lead" → ASSISTANT_MANAGER
     *   "junior" / "intern" / "contract" / anything else        → TEAM_LEAD
     *
     * @param positionStr the position string read from the CSV (may be blank)
     * @return the corresponding ManagerType enum value
     */
    public static ManagerType parseManagerType(String positionStr) {
        String pos = positionStr.toLowerCase().trim();
        return switch (pos) {
            case "", "manager", "head manager", "senior manager" -> ManagerType.HEAD_MANAGER;
            case "senior", "middle", "assistant manager", "team lead" -> ManagerType.ASSISTANT_MANAGER;
            default -> ManagerType.TEAM_LEAD; // covers: junior, intern, contract, etc.
        };
    }

    /**
     * Maps the "Department" string from the CSV to a Department enum value.
     *
     * @param deptStr the department name string read from the CSV
     * @return the corresponding Department enum value
     */
    public static Department parseDepartment(String deptStr) {
        String dept = deptStr.toLowerCase().trim();
        return switch (dept) {
            case "it development", "technical support" -> Department.IT;
            case "hr"                                  -> Department.HR;
            case "finance", "accounting"               -> Department.FINANCE;
            case "sales"                               -> Department.SALES;
            case "marketing"                           -> Department.MARKETING;
            case "operations"                          -> Department.OPERATIONS;
            case "customer service"                    -> Department.CUSTOMER_SERVICE;
            default                                    -> Department.OPERATIONS; // fallback
        };
    }
}

