package ztp.lista1.model.repository;

import ztp.lista1.model.entity.Course;

import java.util.List;

public interface CourseRepository {
    void createCourse(Course course);
    List<Course> readCourses();
    void updateCourse(Course course);
    void deleteCourse(Course course);
}
