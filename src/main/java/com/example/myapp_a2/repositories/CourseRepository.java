package com.example.myapp_a2.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.myapp_a2.models.Course;
import java.util.*;

public interface CourseRepository
        extends CrudRepository<Course, Integer> {

    @Query("select course from Course course")
    public List<Course> findAllCourses();

    @Query("select course from Course course where course.id=:cid")
    public Course findCourseById(@Param("cid") Integer id);
}
