package com.example.myapp_a2.services;

import com.example.myapp_a2.models.Widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WidgetService {
    private List<Widget> widgets;

    public WidgetService() {
        this.widgets = new ArrayList<>(seed());
    }


    public List<Widget> createWidget(Widget widget) {
        widget.setId((new Random()).nextInt());
        this.widgets.add(widget);
        return this.widgets;
    }

    public List<Widget> findAllWidgets() {
        return this.widgets;
    }

    public Widget findWidgetById(Integer widgetId) {
        return this.widgets.stream().filter(w -> w.getId().equals(widgetId)).collect(Collectors.toList()).get(0);
    }

    public Widget updateWidget(Integer widgetId, Widget widget) {
        this.widgets = this.widgets.stream().map(w -> w.getId().equals(widgetId) ? widget : w).collect(Collectors.toList());
        return this.findWidgetById(widgetId);
    }

    public List<Widget> deleteWidget(Integer widgetId) {
        this.widgets = this.widgets.stream().filter(w -> !w.getId().equals(widgetId)).collect(Collectors.toList());
        return this.widgets;
    }

    public List<Widget> updateOrder(List<Widget> widgets){
        this.widgets=widgets;
        return this.widgets;
    }

    private List<Widget> seed() {

        List<Widget> widgets = new ArrayList<>();

        Widget h1 = new Widget(123, "Widget 1", "HEADING");
        h1.setSize("h1");
        h1.setText("The Document Object Model");
        widgets.add(h1);

        Widget l1 = new Widget(234, "Widget 2", "LIST");
        l1.setItems("Nodes,Attributes,Tag names,IDs,Styles,Classes");
        l1.setListType("unordered");
        widgets.add(l1);

        Widget p1 = new Widget(345, "Widget 3", "PARAGRAPH");
        p1.setText("This topic introduces the DOM");
        widgets.add(p1);

        Widget i1 = new Widget(456, "Widget 4", "IMAGE");
        i1.setSrc("https://picsum.photos/200");
        widgets.add(i1);

        Widget link1 = new Widget(567, "Widget 5", "LINK");
        link1.setTitle("The DOM");
        link1.setHref("https://en.wikipedia.org/wiki/Document_Object_Model");
        widgets.add(link1);


        return widgets;
    }

}
