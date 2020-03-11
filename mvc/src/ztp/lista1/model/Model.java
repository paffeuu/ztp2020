package ztp.lista1.model;

import ztp.lista1.model.entity.Course;
import ztp.lista1.model.entity.Student;
import ztp.lista1.model.repository.CourseRepository;
import ztp.lista1.model.repository.StudentRepository;
import ztp.lista1.model.repository.file.CourseFileRepository;
import ztp.lista1.model.repository.file.StudentFileRepository;

import java.util.List;

public class Model {
    private List<Course> courses;
    private List<Student> students;

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public Model() {
        this.courseRepository = new CourseFileRepository("course.csv");
        this.studentRepository = new StudentFileRepository("student.csv");
        if (this.courseRepository instanceof CourseFileRepository) {
            ((CourseFileRepository) this.courseRepository).setStudentRepository(studentRepository);
        }
        if (this.studentRepository instanceof StudentFileRepository) {
            ((StudentFileRepository) this.studentRepository).setCourseRepository(courseRepository);
        }
        update();

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


    private void update() {
        this.students = studentRepository.readStudents();
        this.courses = courseRepository.readCourses();
    }
    //COURSE

    //Create
    public boolean createCourse(Course newCourse) {
        if (courses.stream().anyMatch(course -> newCourse.getName().equals(course.getName()))) {
            return false;
        }
        courses.add(newCourse);
        courseRepository.createCourse(newCourse);
        return true;
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
    public void setCourseSemesterAndTeacherName(Course course, int semester, String teacherName) {
        if (course != null) {
            course.setSemester(semester);
            course.setTeacherName(teacherName);
            courseRepository.updateCourse(course);
        }
    }

    public void addStudentToCourse(Course course, Student student) {
        if (course != null && student != null && !course.getSignedUpStudents().contains(student)) {
            course.addStudent(student);
            courseRepository.updateCourse(course);
        }
    }

    public void removeStudentFromCourse(Course course, Student student) {
        if (course != null && student != null) {
            course.removeStudent(student);
            courseRepository.updateCourse(course);
        }
    }

    //Delete
    public void removeCourse(Course course) {
        courses.remove(course);
        courseRepository.deleteCourse(course);
    }

    //STUDENT

    //Create
    public void createStudent(Student student) {
        students.add(student);
        studentRepository.createStudent(student);
    }

    //Read

    public List<Student> readAllStudents() {
        return students;
    }

    public Student findStudentByStudentId(int studentId) {
        return students.stream().filter(student -> student.getStudentId() == studentId).findFirst().get();
    }

    //Update
    public void setStudentFirstNameAndLastName(Student student, String newFirstName, String newLastName) {
        if (student != null) {
            student.setFirstName(newFirstName);
            student.setLastName(newLastName);
        }
        studentRepository.updateStudent(student);
    }

    //Delete
    public void removeStudent(Student student) {
        students.remove(student);
        studentRepository.deleteStudent(student);
        update();
    }



}
