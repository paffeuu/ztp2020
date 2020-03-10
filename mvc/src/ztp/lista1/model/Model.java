package ztp.lista1.model;

import ztp.lista1.model.entity.Course;
import ztp.lista1.model.entity.Student;
import ztp.lista1.model.repository.CourseRepository;
import ztp.lista1.model.repository.StudentRepository;
import ztp.lista1.model.repository.file.CourseFileRepository;
import ztp.lista1.model.repository.file.StudentFileRepository;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<Course> courses;
    private final List<Student> students;

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public Model() {
        this.studentRepository = new StudentFileRepository("student.csv");
        this.courseRepository = new CourseFileRepository("course.csv", studentRepository);

        this.students = studentRepository.readStudents();
        this.courses = courseRepository.readCourses();
        //MOCK
//        createStudent(new Student("Pawel", "Kowanski", 234234, 'M'));
//        createStudent(new Student("Mariusz", "Wisniewski", 345345, 'M'));
//        createStudent(new Student("Janina", "Truskawska", 555555, 'K'));
//        createStudent(new Student("Tomasz", "Kowal", 333333, 'M'));
//        createStudent(new Student("Marianna", "Wisniak", 567888, 'K'));

//        createCourse(new Course("Matematyka", 2, "Woźniak"));
//        createCourse(new Course("Mechanika kwantowa", 4, "Wojnarowicz"));
//        createCourse(new Course("Programowanie obiektowe", 5, "Misztela"));
    }

    //COURSE

    //Create
    public void createCourse(String name, int semester, String teacherName) {
        Course newCourse = new Course(name, semester, teacherName);
        courses.add(newCourse);
    }

    public void createCourse(Course course) {
        courses.add(course);
        courseRepository.createCourse(course);
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

    public void addStudentToCourse(Course course, Student student) {
        if (course != null && student != null && !course.getSignedUpStudents().contains(student)) {
            course.addStudent(student);
        }
    }

    public void removeStudentFromCourse(Course course, Student student) {
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
        studentRepository.createStudent(student);
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
