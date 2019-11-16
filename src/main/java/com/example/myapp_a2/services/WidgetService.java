package com.example.myapp_a2.services;
import com.example.myapp_a2.models.Topic;
import com.example.myapp_a2.models.Widget;
import com.example.myapp_a2.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.myapp_a2.repositories.TopicRepository;
import java.util.*;

@Service
public class WidgetService {
    @Autowired
    WidgetRepository wr;

    @Autowired
    TopicRepository tr;

    public List<Widget> createWidget(Widget widget) {
        wr.save(widget);
        return wr.findAllWidgets();
    }

    public List<Widget> findAllWidgets() {
        return wr.findAllWidgets();
    }

    public Widget findWidgetById(Integer wid) {
        return wr.findWidgetById(wid);
    }

    public Widget updateWidget(Integer wid, Widget widget) {
        Widget current = wr.findWidgetById(wid);
        current.setName(widget.getName());
        current.setType(widget.getType());
        current.setTitle(widget.getTitle());
        current.setListType(widget.getListType());
        current.setSrc(widget.getSrc());
        current.setHref(widget.getHref());
        current.setSize(widget.getSize());
        current.setItems(widget.getItems());
        current.setText(widget.getText());
        return wr.save(current);
    }

    public List<Widget> deleteWidget(Integer wid) {
        Widget current = wr.findWidgetById(wid);
        wr.delete(current);
        return wr.findAllWidgets();
    }

    public List<Widget> updateOrder(List<Widget> wts, Integer topicId){
        tr.findTopicById(topicId).setWidgets(wts);
        Topic topic=tr.findTopicById(topicId);
        for(Widget w:wts){
            w.setTopic(topic);
            wr.save(w);
        }
        List<Widget> widgets=wr.findAllWidgetsForTopic(topicId);
        return widgets;
    }

    public List<Widget> findAllWidgetsForTopic(Integer tid){
        return wr.findAllWidgetsForTopic(tid);
    }

}
