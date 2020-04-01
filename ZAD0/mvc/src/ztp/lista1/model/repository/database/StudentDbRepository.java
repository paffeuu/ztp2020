package ztp.lista1.model.repository.database;

import ztp.lista1.model.entity.Student;
import ztp.lista1.model.jdbc.JDBCConnector;
import ztp.lista1.model.jdbc.SQLQueryType;
import ztp.lista1.model.repository.StudentRepository;

import java.util.List;

public class StudentDbRepository implements StudentRepository {
    private final JDBCConnector connector;

    public StudentDbRepository() {
        this.connector = new JDBCConnector("jdbc:sqlite:mvc.sqlite3");
    }

    @Override
    public void createStudent(Student student) {
        connector.executeQuery(SQLQueryType.INSERT_STUDENT, student);
    }

    @Override
    public List<Student> readStudents() {
        return (List<Student>) connector.executeQuery(SQLQueryType.SELECT_STUDENTS, null);
    }

    @Override
    public void updateStudent(Student student) {
        connector.executeQuery(SQLQueryType.UPDATE_STUDENT, student);
    }

    @Override
    public void deleteStudent(Student student) {
        connector.executeQuery(SQLQueryType.DELETE_STUDENT, student.getStudentId());
    }
}
