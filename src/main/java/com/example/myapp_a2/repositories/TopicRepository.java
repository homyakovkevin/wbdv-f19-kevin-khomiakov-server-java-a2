package com.example.myapp_a2.repositories;
import com.example.myapp_a2.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TopicRepository  extends CrudRepository<Topic, Integer>{

    @Query("select topic from Topic topic")
    public List<Topic> findAllTopics();

    @Query("select topic from Topic topic where topic.lesson.id = :lid")
    public List<Topic> findAllTopicsForLesson(@Param("lid") Integer lid);

    @Query("select topic from Topic topic where topic.id = :id")
    public Topic findTopicById(@Param("id") Integer id);
}