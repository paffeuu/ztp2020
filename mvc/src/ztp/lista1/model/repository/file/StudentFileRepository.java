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
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            String studentStr = convertStudentObjectToCsvFormat(student);
            fileWriter.append(studentStr);
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
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0) {
                    break;
                }
                String[] elements = line.split(",");
                int studentId = Integer.parseInt(elements[2]);
                if (studentId == student.getStudentId()) {
                    Student newStudent =
                            new Student(student.getFirstName(), student.getLastName(),
                                    studentId, student.getGender());
                    String newStudentStr = convertStudentObjectToCsvFormat(newStudent);
                    lines.add(newStudentStr);
                } else {
                    line = line.concat("\n");
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            for (String line : lines) {
                fileWriter.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(Student student) {

    }

    private String convertStudentObjectToCsvFormat(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getFirstName());
        sb.append(",");
        sb.append(student.getLastName());
        sb.append(",");
        sb.append(student.getStudentId());
        sb.append(",");
        sb.append(student.getGender());
        sb.append("\n");
        return sb.toString();
    }
}
