package ztp.lista2.model;

public class Manager extends Employee {
    private int managementSkills;

    public Manager(String firstName, String lastName, String company, String department, int yearsInCompany, int managementSkills) {
        super(firstName, lastName, company, department, yearsInCompany);
        this.managementSkills = managementSkills;
    }

    public void givePromotion(String position) {
        System.out.println(this + " has been promoted! He or she is the " + position + " now." );
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managementSkills=" + managementSkills +
                ", company='" + company + '\'' +
                ", department='" + department + '\'' +
                ", yearsInCompany=" + yearsInCompany +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
