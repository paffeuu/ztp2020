package ztp.lista1.model;

import ztp.lista1.model.entity.Course;
import ztp.lista1.model.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<Course> courses;
    private final List<Student> students;

    public Model() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    //COURSE

    //Create
    public void createCourse(String name, int semester, String teacherName) {
        Course newCourse = new Course(name, semester, teacherName);
        courses.add(newCourse);
    }

    //Read
    public Course readCourse(int index) {
        return courses.get(index);
    }

    //Update
    public void setCourseSemester(int index, int semester) {
        Course course = courses.get(index);
        if (course != null) {
            course.setSemester(semester);
        }
    }

    public void setCourseTeacherName(int index, String newTeacherName) {
        Course course = courses.get(index);
        if (course != null) {
            course.setTeacherName(newTeacherName);
        }
    }

    public void addStudentToCourse(int indexCourse, int indexStudent) {
        Course course = courses.get(indexCourse);
        Student student = students.get(indexStudent);
        if (course != null && student != null) {
            course.addStudent(student);
        }
    }

    public void removeStudentFromCourse(int indexCourse, int indexStudent) {
        Course course = courses.get(indexCourse);
        Student student = students.get(indexStudent);
        if (course != null && student != null) {
            course.removeStudent(student);
        }
    }

    //Delete
    public void removeCourse(int index) {
        courses.remove(index);
    }

    //STUDENT

    //Create
    public void createStudent(String firstName, String lastName, int studentId, char gender) {
        Student student = new Student(firstName, lastName, studentId, gender);
        students.add(student);
    }

    //Read
    public Student readStudent(int index) {
        return students.get(index);
    }

    //Update
    public void setStudentFirstName(int index, String newFirstName) {
        Student student = students.get(index);
        if (student != null) {
            student.setFirstName(newFirstName);
        }
    }

    public void setStudentLastName(int index, String newLastName) {
        Student student = students.get(index);
        if (student != null) {
            student.setLastName(newLastName);
        }
    }

    //Delete
    public void removeStudent(int index) {
        students.remove(index);
    }



}
