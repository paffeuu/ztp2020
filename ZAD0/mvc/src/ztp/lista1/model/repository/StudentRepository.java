package ztp.lista1.model.repository;

import ztp.lista1.model.entity.Student;

import java.util.List;

public interface StudentRepository {
    void createStudent(Student student);
    List<Student> readStudents();
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
