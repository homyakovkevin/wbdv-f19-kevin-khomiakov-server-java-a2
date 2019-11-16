package com.example.myapp_a2.controller;
import com.example.myapp_a2.models.Course;
import com.example.myapp_a2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("*")
public class CourseController {
    @Autowired
    private CourseService cs;

    @PostMapping("/api/courses")
    public void createCourse(
            @RequestBody Course course) {
        cs.createCourse(course);
    }

    @GetMapping("/api/courses")
    public List<Course> findAllCourses() {
        return this.cs.findAllCourses();
    }

    @GetMapping("/api/courses/{courseId}")
    public Course findCourseById(
            @PathVariable("courseId") Integer id) {
        return this.cs.findCourseById(id);
    }

    @PutMapping("/api/courses/{courseId}")
    public Course updateCourse(
            @PathVariable("courseId") Integer id,
            @RequestBody Course newCourse) {

        return this.cs.updateCourse(id, newCourse);
    }

    @DeleteMapping("/api/courses/{courseId}")
    public void deleteCourse(@PathVariable("courseId") @RequestBody Integer id) {
        this.cs.deleteCourse(id);
    }
}