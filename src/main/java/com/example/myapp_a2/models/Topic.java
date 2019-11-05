package com.example.myapp_a2.models;
import java.util.List;
import java.util.ArrayList;

public class Topic {
    private Integer id;
    private String title;
    private List<Widget> widgets = new ArrayList<>();

    public Topic(Integer id, String title) {
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

    public List<Widget> getWidgets() {
        return this.widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }
}
