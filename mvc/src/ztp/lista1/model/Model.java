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

        //MOCK
        students.add(new Student("Pawel", "Kowanski", 234234, 'M'));
        students.add(new Student("Mariusz", "Wisniewski", 345345, 'M'));
        students.add(new Student("Janina", "Truskawska", 555555, 'K'));
        students.add(new Student("Tomasz", "Kowal", 333333, 'M'));
        students.add(new Student("Marianna", "Wisniak", 567888, 'K'));

        courses.add(new Course("Matematyka", 2, "Woźniak"));
        courses.add(new Course("Mechanika kwantowa", 4, "Wojnarowicz"));
        courses.add(new Course("Programowanie obiektowe", 5, "Misztela"));


    }

    //COURSE

    //Create
    public void createCourse(String name, int semester, String teacherName) {
        Course newCourse = new Course(name, semester, teacherName);
        courses.add(newCourse);
    }

    public void createCourse(Course course) {
        courses.add(course);
    }

    //Read
    public Course readCourse(int index) {
        if (index >= courses.size()) {
            return null;
        }
        return courses.get(index);
    }

    public List<Course> readAllCourses() {
        return courses;
    }

    public Course findCourseByName(String name) {
        return courses.stream().filter(course -> course.getName().equals(name)).findFirst().get();
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

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    //STUDENT

    //Create
    public void createStudent(Student student) {
        students.add(student);
    }

    public void createStudent(String firstName, String lastName, int studentId, char gender) {
        Student student = new Student(firstName, lastName, studentId, gender);
        students.add(student);
    }

    //Read
    public Student readStudent(int index) {
        if (index >= students.size()) {
            return null;
        }
        return students.get(index);
    }

    public List<Student> readAllStudents() {
        return students;
    }

    public Student findStudentByStudentId(int studentId) {
        return students.stream().filter(student -> student.getStudentId() == studentId).findFirst().get();
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

    public void removeStudent(Student student) {
        students.remove(student);
    }



}
