package com.example.myapp_a2.models;
import java.util.List;
import java.util.ArrayList;

public class Lesson {
    private Integer id;
    private String title;
    private List<Topic> topics = new ArrayList<>();

    public Lesson(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Topic> getTopics() {
        return this.topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

}

