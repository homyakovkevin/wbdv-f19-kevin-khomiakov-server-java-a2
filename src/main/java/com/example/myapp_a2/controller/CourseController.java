package com.example.myapp_a2.controller;

import com.example.myapp_a2.models.*;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class CourseController {

    static List<Course> courses = new ArrayList<Course>();
    static List<Lesson>lessons=new ArrayList<>();
    static List<Module> modules=new ArrayList<>();
    static List<Topic>topics=new ArrayList<>();

    static Course course1 =new Course(123, "CS4500");
    static Course course2 =new Course(234, "CS5610");
    static Module module1 =new Module(123,"Week 1");
    static Module module2=new Module(234,"Week 2");

    static Lesson lesson1=new Lesson(123,"Lesson 1");
    static Topic topic1 =new Topic(123,"Topic 1");
    static List<Widget> wgts=new WidgetController().findAllWidgets();

    {
        topic1.setWidgets(wgts);
        topics.add(topic1);

        lesson1.setTopics(topics);
        lessons.add(lesson1);

        module1.setLessons(lessons);
        modules.add(module1);
        modules.add(module2);

        course1.setModules(modules);
        courses.add(course2);
        courses.add(course1);

    }

    @PostMapping("/api/courses")
    public Course createCourse(
            @RequestBody Course course) {
        courses.add(course);
        return course;
    }

    @DeleteMapping("/api/courses/{courseId}")
    public void deleteCourse(@PathVariable("courseId") @RequestBody Integer id) {
        courses.removeIf(course -> course.getId().equals(id));
    }

    @GetMapping("/api/courses")
    public List<Course> findAllCourses() {
        return courses;
    }

    @GetMapping("/api/courses/{courseId}")
    public Course findCourseById(
            @PathVariable("courseId") Integer id) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @PutMapping("/api/courses/{courseId}")
    public Course updateCourse(
            @PathVariable("courseId") Integer id,
            @RequestBody Course newCourse) {

        for (Course c : courses) {
            if (c.getId().equals(id)) {
                c.setTitle(newCourse.getTitle());
                return c;
            }
        }
        return null;
    }
}