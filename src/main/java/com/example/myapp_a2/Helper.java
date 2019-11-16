package com.example.myapp_a2;
import com.example.myapp_a2.models.*;
import com.example.myapp_a2.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class Helper {
    private CourseService courseService;
    private WidgetService widgetService;
    private ModuleService moduleService;
    private LessonService lessonService;
    private TopicService topicService;


    @Autowired
    public Helper(CourseService courseService, WidgetService widgetService, ModuleService moduleService, LessonService lessonService, TopicService topicService) {
        this.courseService = courseService;
        this.widgetService = widgetService;
        this.moduleService = moduleService;
        this.lessonService = lessonService;
        this.topicService = topicService;

    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        parseInitData();
    }

    private void parseInitData(){
        List<Widget> widgets = new ArrayList<>();

        Widget h = new Widget( "Widget 1", "HEADING");
        h.setSize("h1");
        h.setText("The Document Object Model");
        h.setOrd(1);
        widgets.add(h);

        Widget ls = new Widget( "Widget 2", "LIST");
        ls.setItems("Nodes,Attributes,Tag names,IDs,Styles,Classes");
        ls.setListType("unordered");
        ls.setOrd(2);
        widgets.add(ls);

        Widget p = new Widget( "Widget 3", "PARAGRAPH");
        p.setText("This topic introduces the DOM");
        p.setOrd(3);
        widgets.add(p);

        Widget img = new Widget("Widget 4", "IMAGE");
        img.setSrc("https://picsum.photos/200");
        img.setOrd(4);
        widgets.add(img);

        Widget lnk = new Widget("Widget 5", "LINK");
        lnk.setTitle("The DOM");
        lnk.setHref("https://en.wikipedia.org/wiki/Document_Object_Model");
        lnk.setOrd(5);
        widgets.add(lnk);

        Topic t1 = new Topic("DOM");
        Topic t2 = new Topic( "Tags");
        Topic t3 = new Topic( "Attributes");
        t1.setWidgets(widgets);

        List<Topic> topics = new ArrayList<>();
        topics.add(t1);
        topics.add(t2);
        topics.add(t3);

        List<Lesson> lessons = new ArrayList<>();
        Lesson l1 = new Lesson( "HTML");
        Lesson l2 = new Lesson("CSS");
        l1.setTopics(topics);
        lessons.add(l1);
        lessons.add(l2);

        List<Module> modules = new ArrayList<>();
        Module m1 = new Module( "Week 1");
        Module m2 = new Module( "Week 2");
        m1.setLessons(lessons);
        modules.add(m1);
        modules.add(m2);

        Course c1 = new Course( "CS5610");
        Course c2 = new Course( "CS5200");

        c1.setModules(modules);

        courseService.createCourse(c1);
        courseService.createCourse(c2);

        for(Module module: modules) {
            module.setCourse(c1);
            moduleService.createModule(module);
        }

        for(Lesson lesson: lessons) {
            lesson.setModule(m1);
            lessonService.createLesson(lesson);
        }

        for(Topic topic:topics) {
            topic.setLesson(l1);
            topicService.createTopic(topic);
        }

        for(Widget widget:widgets) {
            widget.setTopic(t1);
            widgetService.createWidget(widget);
        }
    }
}
