package ztp.lista1.model.repository.database;

import ztp.lista1.model.entity.Course;
import ztp.lista1.model.jdbc.JDBCConnector;
import ztp.lista1.model.jdbc.SQLQueryType;
import ztp.lista1.model.repository.CourseRepository;

import java.util.List;

public class CourseDbRepostitory implements CourseRepository {
    private final JDBCConnector connector;

    public CourseDbRepostitory() {
        this.connector = new JDBCConnector("jdbc:sqlite:mvc.sqlite3");
    }

    @Override
    public void createCourse(Course course) {
        connector.executeQuery(SQLQueryType.INSERT_COURSE, course);
    }

    @Override
    public List<Course> readCourses() {
        return (List<Course>) connector.executeQuery(SQLQueryType.SELECT_COURSES, null);
    }

    @Override
    public void updateCourse(Course course) {
        connector.executeQuery(SQLQueryType.UPDATE_COURSE, course);
    }

    @Override
    public void deleteCourse(Course course) {
        connector.executeQuery(SQLQueryType.DELETE_COURSE, course.getName());
    }
}
