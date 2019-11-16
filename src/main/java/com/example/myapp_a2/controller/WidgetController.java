package com.example.myapp_a2.controller;
import com.example.myapp_a2.models.Topic;
import com.example.myapp_a2.models.Widget;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.myapp_a2.services.TopicService;
import com.example.myapp_a2.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("*")
public class WidgetController {

    @Autowired
    WidgetService ws;
    @Autowired
    TopicService ts;

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return ws.findAllWidgets();
    }

    @GetMapping("/api/topics/{tId}/widgets")
    public List<Widget> findAllWidgetsForTopic(
            @PathVariable("tId") Integer tId) {
        return ws.findAllWidgetsForTopic(tId);
    }

    @PostMapping("/api/topics/{tId}/widgets")
    public List<Widget> addWidgetToTopic(
            @PathVariable("tId") Integer tId,
            @RequestBody Widget newWidget
    ) {
        Topic topic = ts.findTopicById(tId);
        newWidget.setTopic(topic);
        ws.createWidget(newWidget);

        return ws.findAllWidgetsForTopic(tId);
    }

    @GetMapping("/api/widgets/{wid}")
    public Widget findWidgetById(@PathVariable("wid") Integer id) {
        return ws.findWidgetById(id);
    }


    @PutMapping("/api/widgets/{widgetId}")
    public Widget updateWidget(
            @PathVariable("widgetId") Integer wid,
            @RequestBody Widget newWidget) {
        return ws.updateWidget(wid, newWidget);
    }

    @PutMapping("/api/topics/{tId}/widgets")
    public List<Widget> updateOrder(
            @PathVariable("tId") Integer tId,
            @RequestBody List<Widget> wts) {
        return ws.updateOrder(wts,tId);
    }

    @DeleteMapping("/api/widgets/{widgetId}")
    public List<Widget> deleteWidget(@PathVariable("widgetId") Integer wid) {
        return this.ws.deleteWidget(wid);

    }
}
