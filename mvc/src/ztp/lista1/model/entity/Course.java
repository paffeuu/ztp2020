package ztp.lista1.model.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Course {
    private String name;
    private int semester;
    private String teacherName;
    private List<Student> signedUpStudents;

    public Course(String name, int semester, String teacherName) {
        this.name = name;
        this.semester = semester;
        this.teacherName = teacherName;
        this.signedUpStudents = new ArrayList<>();
    }

    public void addStudent(Student newStudent) {
        signedUpStudents.add(newStudent);
        sort();
    }

    public void removeStudent(Student removedStudent) {
        signedUpStudents.remove(removedStudent);
    }

    private void sort() {
        signedUpStudents.sort(Comparator.comparing(Student::getLastName));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<Student> getSignedUpStudents() {
        return signedUpStudents;
    }

    public void setSignedUpStudents(List<Student> signedUpStudents) {
        this.signedUpStudents = signedUpStudents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" (");
        sb.append(teacherName);
        sb.append(" ");
        sb.append(semester);
        sb.append("), studenci: [");
        signedUpStudents.forEach(sb::append);
        sb.append("]");
        return sb.toString();
    }
}
