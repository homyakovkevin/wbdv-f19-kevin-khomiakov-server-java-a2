package com.example.myapp_a2.controller;
import com.example.myapp_a2.models.Lesson;
import com.example.myapp_a2.models.Module;
import com.example.myapp_a2.services.LessonService;
import com.example.myapp_a2.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("*")
public class LessonController {
    @Autowired
    LessonService ls;
    @Autowired
    ModuleService ms;

    @GetMapping("/api/lessons")
    public List<Lesson> findAllLessons() {
        return ls.findAllLessons();
    }

    @GetMapping("/api/modules/{mId}/lessons")
    public List<Lesson> findAllLessonsForModule(
            @PathVariable("mId") Integer mId) {
        return ls.findAllLessonsForModule(mId);
    }

    @PostMapping("/api/modules/{mId}/lessons")
    public List<Lesson> addLessonToModule(
            @PathVariable("mId") Integer mId,
            @RequestBody Lesson newLesson
    ) {
        Module module = ms.findModuleById(mId);
        newLesson.setModule(module);
        ls.createLesson(newLesson);
        return ls.findAllLessonsForModule(mId);
    }

    @GetMapping("/api/lessons/{lid}")
    public Lesson findLessonById(@PathVariable("lid") Integer id) {
        return ls.findLessonById(id);
    }

    @DeleteMapping("/api/lessons/{lId}")
    public void deleteLesson(@PathVariable("lId") Integer id) {
        this.ls.deleteLesson(id);
    }

    @PutMapping("/api/lessons/{lid}")
    public Lesson updateLesson(
            @PathVariable("lid") Integer id,
            @RequestBody Lesson lesson) {

        return ls.updateLesson(id, lesson);
    }
}