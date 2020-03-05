package ztp.lista1.model.entity;

public class Student {
    private String firstName;
    private String lastName;
    private int studentId;
    private char gender;

    public Student(String firstName, String lastName, int studentId, char gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
