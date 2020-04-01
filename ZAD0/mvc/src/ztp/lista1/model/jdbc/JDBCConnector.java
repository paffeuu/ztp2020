package ztp.lista1.model.jdbc;

import ztp.lista1.model.entity.Course;
import ztp.lista1.model.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class JDBCConnector {
    private final String url;
    private Random random;

    public  JDBCConnector(String url) {
        this.url = url;
        this.random = new Random();
    }

    public Object executeQuery(SQLQueryType queryType, Object object) {
        Connection conn = null;
        try {
            // db parameters
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            switch (queryType) {
                case INSERT_STUDENT:
                    insertStudent((Student) object, conn);
                    return null;
                case SELECT_STUDENT:
                    return selectStudent((int) object, conn);
                case SELECT_STUDENTS:
                    return selectStudents(conn);
                case UPDATE_STUDENT:
                    updateStudent((Student) object, conn);
                    return null;
                case DELETE_STUDENT:
                    deleteStudent((int) object, conn);
                    return null;
                case INSERT_COURSE:
                    insertCourse((Course) object, conn);
                    return null;
                case SELECT_COURSE:
                    return selectCourse((String) object, conn);
                case SELECT_COURSES:
                    return selectCourses(conn);
                case UPDATE_COURSE:
                    updateCourse((Course) object, conn);
                    return null;
                case DELETE_COURSE:
                    deleteCourse((String) object, conn);

            }


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    private void insertStudent(Student student, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.INSERT_STUDENT[0]);
        queryBuilder.append(student.getFirstName());
        queryBuilder.append("', '");
        queryBuilder.append(student.getLastName());
        queryBuilder.append("', '");
        queryBuilder.append(student.getStudentId());
        queryBuilder.append("', '");
        queryBuilder.append(student.getGender());
        queryBuilder.append(SQLUtils.INSERT_STUDENT[1]);

        String query = queryBuilder.toString();

        stmt.executeUpdate(query);
    }

    private Student selectStudent(int studentId, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs;

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.SELECT_STUDENT);
        queryBuilder.append(studentId);

        String query = queryBuilder.toString();

        rs = stmt.executeQuery(query);
        if (rs.next()) {
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            char gender = rs.getString("Gender").charAt(0);
            return new Student(firstName, lastName, studentId, gender);
        }
        return null;
    }

    private List<Student> selectStudents(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs;

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.SELECT_STUDENTS);

        String query = queryBuilder.toString();

        List<Student> students = new ArrayList<>();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            int studentId = rs.getInt("StudentId");
            char gender = rs.getString("Gender").charAt(0);
            students.add(new Student(firstName, lastName, studentId, gender));
        }
        return students;
    }

    private void updateStudent(Student student, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.UPDATE_STUDENT[0]);
        queryBuilder.append(student.getFirstName());
        queryBuilder.append(SQLUtils.UPDATE_STUDENT[1]);
        queryBuilder.append(student.getLastName());
        queryBuilder.append(SQLUtils.UPDATE_STUDENT[2]);
        queryBuilder.append(student.getGender());
        queryBuilder.append(SQLUtils.UPDATE_STUDENT[3]);
        queryBuilder.append(student.getStudentId());
        String query = queryBuilder.toString();

        stmt.executeUpdate(query);
    }

    private void deleteStudent(int id, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.DELETE_STUDENT);
        queryBuilder.append(id);
        String query = queryBuilder.toString();

        stmt.executeUpdate(query);
    }

    private void insertCourse(Course course, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        int courseId = random.nextInt(Integer.MAX_VALUE);

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.INSERT_COURSE[0]);
        queryBuilder.append(course.getName());
        queryBuilder.append("', '");
        queryBuilder.append(course.getSemester());
        queryBuilder.append("', '");
        queryBuilder.append(course.getTeacherName());
        queryBuilder.append("', ");
        queryBuilder.append(courseId);
        queryBuilder.append(SQLUtils.INSERT_COURSE[1]);

        String query = queryBuilder.toString();
        stmt.executeUpdate(query);

        if (!course.getSignedUpStudents().isEmpty()) {
            List<Integer> studentIds =
                    course.getSignedUpStudents()
                            .stream()
                            .map(Student::getStudentId)
                            .collect(Collectors.toList());
            insertCourseStudent(studentIds, courseId, conn);
        }
    }

    private Course selectCourse(String name, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs;

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.SELECT_COURSE[0]);
        queryBuilder.append(name);
        queryBuilder.append(SQLUtils.SELECT_COURSE[1]);

        String query = queryBuilder.toString();

        Course course = null;
        rs = stmt.executeQuery(query);
        int courseId = -1;
        if (rs.next()) {
            courseId = rs.getInt("CourseId");
            int semester = rs.getInt("Semester");
            String teacherName = rs.getString("TeacherName");
            course =  new Course(name, semester, teacherName);
        }

        if (course != null) {
            List<Integer> studentIds = selectCourseStudentByCourseId(courseId, conn);
            for (Integer studentId : studentIds) {
                Student student = selectStudent(studentId, conn);
                course.addStudent(student);
            }
        }
        return course;
    }

    private List<Course> selectCourses(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs;

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.SELECT_COURSES);

        String query = queryBuilder.toString();

        List<Course> courses = new ArrayList<>();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("Name");
            int courseId = rs.getInt("CourseId");
            int semester = rs.getInt("Semester");
            String teacherName = rs.getString("TeacherName");
            Course course =  new Course(name, semester, teacherName);
            List<Integer> studentIds = selectCourseStudentByCourseId(courseId, conn);
            for (Integer studentId : studentIds) {
                Student student = selectStudent(studentId, conn);
                course.addStudent(student);
            }
            courses.add(course);
        }
        return courses;
    }

    private void updateCourse(Course course, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.UPDATE_COURSE[0]);
        queryBuilder.append(course.getSemester());
        queryBuilder.append(SQLUtils.UPDATE_COURSE[1]);
        queryBuilder.append(course.getTeacherName());
        queryBuilder.append(SQLUtils.UPDATE_COURSE[2]);
        queryBuilder.append(course.getName());
        queryBuilder.append(SQLUtils.UPDATE_COURSE[3]);
        String query = queryBuilder.toString();

        stmt.executeUpdate(query);

        if (!course.getSignedUpStudents().isEmpty()) {
            List<Integer> studentIds = selectCourseStudentByCourseName(course.getName(), conn);
            List<Integer> signedUpStudentsIds =
                    course.getSignedUpStudents()
                            .stream()
                            .map(Student::getStudentId)
                            .collect(Collectors.toList());
            List<Integer> studentIdsToInsert = new ArrayList<>();
            List<Integer> studentIdsToDelete = new ArrayList<>();
            for (int studentId : studentIds) {
                if (!signedUpStudentsIds.contains(studentId)) {
                    studentIdsToDelete.add(studentId);
                }
            }
            for (int studentId : signedUpStudentsIds) {
                if (!studentIds.contains(studentId)) {
                    studentIdsToInsert.add(studentId);
                }
            }
            int courseId = selectCourseIdByName(course.getName(), conn);
            insertCourseStudent(studentIdsToInsert, courseId, conn);
            deleteCourseStudentByStudentId(studentIdsToDelete, courseId, conn);
        }
    }

    private void deleteCourse(String name, Connection conn) throws SQLException {
        int courseId = selectCourseIdByName(name, conn);
        deleteCourseStudent(courseId, conn);

        Statement stmt = conn.createStatement();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.DELETE_COURSE[0]);
        queryBuilder.append(name);
        queryBuilder.append(SQLUtils.DELETE_COURSE[1]);
        String query = queryBuilder.toString();

        stmt.executeUpdate(query);
    }

    private void insertCourseStudent(List<Integer> studentIds, int courseId, Connection conn) throws SQLException {
        if (!studentIds.isEmpty()) {
            Statement stmt = conn.createStatement();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLUtils.INSERT_COURSE_STUDENT[0]);
            boolean first = true;
            for (int studentId : studentIds) {
                if (!first) {
                    queryBuilder.append(", ");
                }
                first = false;
                queryBuilder.append(SQLUtils.INSERT_COURSE_STUDENT[1]);
                queryBuilder.append(studentId);
                queryBuilder.append(", ");
                queryBuilder.append(courseId);
                queryBuilder.append(SQLUtils.INSERT_COURSE_STUDENT[2]);
            }

            String query = queryBuilder.toString();
            stmt.executeUpdate(query);
        }
    }

    private List<Integer> selectCourseStudentByCourseId(int courseId, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs;

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.SELECT_COURSE_STUDENT_BY_ID);
        queryBuilder.append(courseId);

        String query = queryBuilder.toString();
        rs = stmt.executeQuery(query);
        List<Integer> studentIds = new ArrayList<>();
        while (rs.next()) {
            int studentId = rs.getInt("StudentId");
            studentIds.add(studentId);
        }
        return studentIds;
    }

    private List<Integer> selectCourseStudentByCourseName(String name, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs;

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.SELECT_COURSE_STUDENT_BY_NAME[0]);
        queryBuilder.append(name);
        queryBuilder.append(SQLUtils.SELECT_COURSE_STUDENT_BY_NAME[1]);

        String query = queryBuilder.toString();
        rs = stmt.executeQuery(query);
        List<Integer> studentIds = new ArrayList<>();
        while (rs.next()) {
            int studentId = rs.getInt("StudentId");
            studentIds.add(studentId);
        }
        return studentIds;
    }

    private void deleteCourseStudent(int courseId, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.DELETE_COURSE_STUDENT);
        queryBuilder.append(courseId);
        String query = queryBuilder.toString();

        stmt.executeUpdate(query);
    }

    private void deleteCourseStudentByStudentId(List<Integer> studentIds, int courseId, Connection conn) throws SQLException {
        for (int studentId : studentIds) {
            Statement stmt = conn.createStatement();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLUtils.DELETE_COURSE_STUDENT_BY_STUDENTID[0]);
            queryBuilder.append(courseId);
            queryBuilder.append(SQLUtils.DELETE_COURSE_STUDENT_BY_STUDENTID[1]);
            queryBuilder.append(studentId);
            String query = queryBuilder.toString();

            stmt.executeUpdate(query);
        }
    }

    private int selectCourseIdByName(String name, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs;

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SQLUtils.SELECT_COURSE_ID_BY_NAME[0]);
        queryBuilder.append(name);
        queryBuilder.append(SQLUtils.SELECT_COURSE_ID_BY_NAME[1]);

        String query = queryBuilder.toString();
        rs = stmt.executeQuery(query);
        Integer courseId = null;
        if (rs.next()) {
            courseId = rs.getInt("CourseId");
        }
        return courseId;
    }


}
