/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

class HeadManager extends Manager {
    private String branchName;
    public HeadManager(String name, Department department, int teamSize, String branchName) {
        super(name, ManagerType.HEAD_MANAGER, department, teamSize);
        this.branchName = branchName;
    }
    public String getBranchName() { return branchName; }
    @Override
    public String toString() { return super.toString() + " | Branch: " + branchName; }
}

class AssistantManager extends Manager {
    private String reportsTo;
    public AssistantManager(String name, Department department, int teamSize, String reportsTo) {
        super(name, ManagerType.ASSISTANT_MANAGER, department, teamSize);
        this.reportsTo = reportsTo;
    }
    public String getReportsTo() { return reportsTo; }
    @Override
    public String toString() { return super.toString() + " | Reports To: " + reportsTo; }
}

class TeamLead extends Manager {
    private String specialisation;
    public TeamLead(String name, Department department, int teamSize, String specialisation) {
        super(name, ManagerType.TEAM_LEAD, department, teamSize);
        this.specialisation = specialisation;
    }
    public String getSpecialisation() { return specialisation; }
    @Override
    public String toString() { return super.toString() + " | Specialisation: " + specialisation; }
}