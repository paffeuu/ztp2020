package ztp.lista2.model;

public class Employee extends Person {
    protected String company;
    protected String department;
    protected int yearsInCompany;

    public Employee(String firstName, String lastName, String company, String department, int yearsInCompany) {
        super(firstName, lastName);
        this.company = company;
        this.department = department;
        this.yearsInCompany = yearsInCompany;
    }

    public void givePromotion(boolean manager) {
        System.out.println(this + " has been promoted! " + (manager ? "He or she is the manager now." : ""));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "company='" + company + '\'' +
                ", department='" + department + '\'' +
                ", yearsInCompany=" + yearsInCompany +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
