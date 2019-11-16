package com.example.myapp_a2.controller;
import com.example.myapp_a2.models.Lesson;
import com.example.myapp_a2.models.Topic;
import com.example.myapp_a2.services.LessonService;
import com.example.myapp_a2.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("*")
public class TopicController {
    @Autowired
    TopicService ts;
    @Autowired
    LessonService ls;

    @PostMapping("/api/topics")
    public void createTopic(
            @RequestBody Topic m) {
        ts.createTopic(m);
    }
    @GetMapping("/api/topics")
    public List<Topic> findAllTopics(){
        return ts.findAllTopics();
    }
    @GetMapping("/api/lessons/{id}/topics")
    public List<Topic> findAllTopicsForLesson(
            @PathVariable("id") Integer Id) {
        return ts.findAllTopicsForLesson(Id);
    }

    @PostMapping("/api/lessons/{id}/topics")
    public List<Topic> addTopicToLesson(
            @PathVariable("id") Integer lId,
            @RequestBody Topic newTopic
    ) {
        Lesson lesson = ls.findLessonById(lId);
        newTopic.setLesson(lesson);
        ts.createTopic(newTopic);

        return ts.findAllTopicsForLesson(lId);
    }

    @PutMapping("/api/topics/{id}")
    public Topic updateTopic(
            @PathVariable("id") Integer id,
            @RequestBody Topic topic) {

        return ts.updateTopic(id, topic);
    }

    @GetMapping("/api/topics/{id}")
    public Topic findTopicById(@PathVariable("id") Integer id) {
        return ts.findTopicById(id);
    }

    @DeleteMapping("/api/topics/{Id}")
    public void deleteTopic(@PathVariable("Id") Integer id) {
        this.ts.deleteTopic(id);
    }
}