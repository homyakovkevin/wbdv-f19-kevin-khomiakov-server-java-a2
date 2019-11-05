package com.example.myapp_a2.models;

public class Widget {
    private Integer id;
    private String name;
    private String type;
    private String text;
    private String items;
    private String size;
    private String src;
    private String href;
    private String title;
    private String listType;

    public Widget(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;

    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListType() {
        return this.listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }
}
