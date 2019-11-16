package com.example.myapp_a2.services;
import com.example.myapp_a2.models.Lesson;
import com.example.myapp_a2.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LessonService {
    @Autowired
    LessonRepository lr;

    public Lesson createLesson(Lesson lesson) {
        return lr.save(lesson);
    }

    public List<Lesson> findAllLessons() {
        return lr.findAllLessons();
    }

    public List<Lesson> findAllLessonsForModule(Integer mid) {
        return lr.findAllLessonsForModule(mid);
    }

    public Lesson findLessonById(Integer id) {
        return lr.findLessonById(id);
    }

    public void deleteLesson(Integer lid) {
        Lesson lesson = lr.findLessonById(lid);
        lr.delete(lesson);
    }

    public Lesson updateLesson(Integer lid, Lesson lesson) {

        Lesson current = lr.findLessonById(lid);
        current.setTopics(lesson.getTopics());
        current.setTitle(lesson.getTitle());
        return lr.save(current);
    }

}
