package com.example.myapp_a2.services;
import com.example.myapp_a2.models.Topic;
import com.example.myapp_a2.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    TopicRepository tr;

    public Topic createTopic(Topic topic) {
        return tr.save(topic);
    }

    public List<Topic> findAllTopics() {
        return tr.findAllTopics();
    }
    public List<Topic> findAllTopicsForLesson(Integer lessonId){
        return tr.findAllTopicsForLesson(lessonId);
    }

    public Topic findTopicById(Integer id){
        return tr.findTopicById(id);
    }

    public void deleteTopic(Integer tid) {
        Topic topic = tr.findTopicById(tid);
        tr.delete(topic);
    }

    public Topic updateTopic(Integer id,Topic topic) {
        Topic current=tr.findTopicById(id);
        current.setWidgets(topic.getWidgets());
        current.setTitle(topic.getTitle());
        return tr.save(current);
    }
}
