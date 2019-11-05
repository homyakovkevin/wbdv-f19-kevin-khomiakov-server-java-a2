package com.example.myapp_a2.services;

import com.example.myapp_a2.models.*;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private List<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>(setUpCourses());
    }

    public void createCourse(Course course) {
        course.setId((new Random()).nextInt());
        this.courses.add(course);
    }

    public List<Course> findAllCourses() {
        return this.courses;
    }

    public Course findCourseById(Integer courseId) {
        return this.courses.stream()
                .filter(c -> c.getId().equals(courseId))
                .collect(Collectors.toList()).get(0);
    }

    public Course updateCourse(Integer courseId, Course course) {
        this.courses = this.courses.stream()
                .map(c -> c.getId().equals(courseId) ? course : c)
                .collect(Collectors.toList());
        return this.findCourseById(courseId);
    }

    public void deleteCourse(Integer courseId) {
        this.courses = this.courses.stream()
                .filter(c -> !c.getId().equals(courseId))
                .collect(Collectors.toList());
    }


    private List<Course> setUpCourses() {
        List<Module> modules = setUpModules();
        Course c1 = new Course(123, "CS5610");
        Course c2 = new Course(234, "CS5200");

        c1.setModules(modules);

        List<Course> courses = new ArrayList<>();

        courses.add(c1);
        courses.add(c2);

        return courses;
    }

    private List<Topic> setUpTopics() {
        WidgetService widgetService = new WidgetService();
        List<Widget> widgets = widgetService.findAllWidgets();

        Topic t1 = new Topic(1, "DOM");
        Topic t2 = new Topic(2, "Tags");
        Topic t3 = new Topic(3, "Attributes");

        t1.setWidgets(widgets);
        List<Topic> topics = new ArrayList<>();

        topics.add(t1);
        topics.add(t2);
        topics.add(t3);

        return topics;
    }

    private List<Lesson> setUpLessons() {
        List<Topic> topics = setUpTopics();
        Lesson l1 = new Lesson(1, "HTML");
        Lesson l2 = new Lesson(2, "CSS");

        l1.setTopics(topics);

        List<Lesson> lessons = new ArrayList<>();

        lessons.add(l1);
        lessons.add(l2);

        return lessons;
    }

    private List<Module> setUpModules() {
        List<Lesson> lessons = setUpLessons();
        Module m1 = new Module(123, "Week 1");
        Module m2 = new Module(234, "Week 2");

        m1.setLessons(lessons);

        List<Module> modules = new ArrayList<>();

        modules.add(m1);
        modules.add(m2);

        return modules;
    }


}
