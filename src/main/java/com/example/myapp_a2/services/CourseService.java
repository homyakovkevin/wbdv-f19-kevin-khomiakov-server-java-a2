package com.example.myapp_a2.services;
import com.example.myapp_a2.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.myapp_a2.repositories.CourseRepository;
import java.util.*;

@Service
public class CourseService {
    @Autowired
    CourseRepository cr;

    public void createCourse(Course course) {
        cr.save(course);
    }

    public List<Course> findAllCourses() {
        return cr.findAllCourses();
    }

    public Course findCourseById(Integer cid) {
        return cr.findCourseById(cid);
    }

    public Course updateCourse(Integer cid, Course course) {
        Course current = cr.findCourseById(cid);
        current.setModules(course.getModules());
        current.setTitle(course.getTitle());
        return cr.save(current);
    }

    public void deleteCourse(Integer cid) {
        Course course=cr.findCourseById(cid);
        cr.delete(course);
    }

}
