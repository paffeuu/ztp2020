package ztp.lista1.model.jdbc;

public class SQLUtils {
    public final static String INSERT_STUDENT[] = {"INSERT INTO Student VALUES('", "')"};
    public final static String SELECT_STUDENT = "SELECT * FROM Student WHERE StudentId = ";
    public final static String UPDATE_STUDENT[] = {
            "UPDATE Student SET FirstName = '",
            "', LastName = '",
            "', Gender = '",
            "' WHERE StudentId = "};
    public final static String DELETE_STUDENT = "DELETE FROM Student WHERE StudentId = ";

    public final static String INSERT_COURSE[] = {"INSERT INTO Course VALUES ('", ")"};
    public final static String SELECT_COURSE[] = {"SELECT * FROM Course WHERE Name = '", "'"};
    public final static String UPDATE_COURSE[] = {
            "UPDATE Course SET Semester = ",
            ", TeacherName = '",
            "' WHERE Name = '",
            "'"};
    public final static String DELETE_COURSE[] = {"DELETE FROM Course WHERE Name = '", "'"};

    public final static String INSERT_COURSE_STUDENT[] = {"INSERT INTO CourseStudent VALUES ", "(", ")"};
    public final static String SELECT_COURSE_STUDENT_BY_ID = "SELECT StudentId FROM CourseStudent WHERE CourseId = ";
    public final static String SELECT_COURSE_STUDENT_BY_NAME[] = {
            "SELECT StudentId FROM CourseStudent JOIN Course ON Course.CourseId = CourseStudent.CourseId" +
                    " WHERE Name = '", "'"};
    public final static String DELETE_COURSE_STUDENT = "DELETE FROM CourseStudent WHERE CourseId = ";
    public final static String DELETE_COURSE_STUDENT_BY_STUDENTID[] = {
            "DELETE FROM CourseStudent WHERE CourseId = ",
            " AND StudentId = "
    };

    public final static String SELECT_COURSE_ID_BY_NAME[] = {"SELECT CourseId FROM Course WHERE Name = '", "'"};


}