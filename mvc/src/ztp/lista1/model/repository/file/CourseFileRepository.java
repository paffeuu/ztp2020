package ztp.lista1.model.repository.file;

import ztp.lista1.model.entity.Course;
import ztp.lista1.model.entity.Student;
import ztp.lista1.model.repository.CourseRepository;
import ztp.lista1.model.repository.StudentRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseFileRepository implements CourseRepository {
    private final File file;
    private StudentRepository studentRepository;

    public CourseFileRepository(String file, StudentRepository studentRepository) {
        this.file = new File(file);
        this.studentRepository = studentRepository;
    }

    @Override
    public void createCourse(Course course) {
        StringBuilder sb = new StringBuilder();
        sb.append(course.getName());
        sb.append(",");
        sb.append(course.getSemester());
        sb.append(",");
        sb.append(course.getTeacherName());
        sb.append(",");
        course.getSignedUpStudents().forEach(student -> {
            sb.append(student.getStudentId());
            sb.append(",");
        });
        sb.append("\n");
        String courseStr = sb.toString();
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.append(courseStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> readCourses() {
        List<Student> students = studentRepository.readStudents();
        List<Course> courses = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0) {
                    break;
                }
                String[] elements = line.split(",");
                String name = elements[0];
                int semester = Integer.parseInt(elements[1]);
                String teacherName = elements[2];
                Course course = new Course(name, semester, teacherName);
                for (int i = 3; i < elements.length; i++) {
                    int studentId = Integer.parseInt(elements[i]);
                    Student student =
                            students.stream()
                            .filter(stud -> stud.getStudentId() == studentId)
                            .findFirst()
                            .get();
                    course.addStudent(student);
                }
                courses.add(course);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public void updateCourse(Course course) {

    }

    @Override
    public void deleteCourse(Course course) {

    }
}
