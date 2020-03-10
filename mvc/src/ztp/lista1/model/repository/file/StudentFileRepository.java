package ztp.lista1.model.repository.file;

import ztp.lista1.model.entity.Student;
import ztp.lista1.model.repository.StudentRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentFileRepository implements StudentRepository {
    private final File file;

    public StudentFileRepository(String file) {
        this.file = new File(file);
    }

    @Override
    public void createStudent(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getFirstName());
        sb.append(",");
        sb.append(student.getLastName());
        sb.append(",");
        sb.append(student.getStudentId());
        sb.append(",");
        sb.append(student.getGender());
        sb.append("\n");
        String courseStr = sb.toString();
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.append(courseStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0) {
                    break;
                }
                String[] elements = line.split(",");
                String firstName = elements[0];
                String lastName = elements[1];
                int studentId = Integer.parseInt(elements[2]);
                char gender = elements[3].charAt(0);
                Student student = new Student(firstName, lastName, studentId, gender);
                students.add(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(Student student) {

    }
}
