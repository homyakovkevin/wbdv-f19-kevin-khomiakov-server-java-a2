package com.example.myapp_a2.repositories;

import com.example.myapp_a2.models.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    @Query("select lesson from Lesson lesson")
    public List<Lesson> findAllLessons();

    @Query("select lesson from Lesson lesson where lesson.module.id = :mid")
    public List<Lesson> findAllLessonsForModule(@Param("mid") Integer mid);

    @Query("select lesson from Lesson lesson where lesson.id = :id")
    public Lesson findLessonById(@Param("id") Integer id);

}