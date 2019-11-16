package com.example.myapp_a2.controller;
import com.example.myapp_a2.models.Course;
import com.example.myapp_a2.models.Module;
import com.example.myapp_a2.services.CourseService;
import com.example.myapp_a2.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("*")
public class ModuleController {
    @Autowired
    ModuleService ms;
    @Autowired
    CourseService cs;

    @PostMapping("/api/modules")
    public void createModule(
            @RequestBody Module m) {
        ms.createModule(m);
    }
    @GetMapping("/api/modules")
    public List<Module> findAllModules(){
        return ms.findAllModules();
    }

    @GetMapping("/api/courses/{cid}/modules")
    public List<Module> findAllModulesForCourse(
            @PathVariable("cid") Integer courseId) {
        return ms.findAllModulesForCourse(courseId);
    }

    @PostMapping("/api/courses/{cid}/modules")
    public List<Module> addModuleToCourse(
            @PathVariable("cid") Integer courseId,
            @RequestBody Module newModule
    ) {
        Course course = cs.findCourseById(courseId);
        newModule.setCourse(course);
        ms.createModule(newModule);

        return ms.findAllModulesForCourse(courseId);
    }

    @PutMapping("/api/modules/{mid}")
    public Module updateModule(
            @PathVariable("mid") Integer id,
            @RequestBody Module module) {

        return ms.updateModule(id, module);
    }

    @GetMapping("/api/modules/{mid}")
    public Module findModuleById(@PathVariable("mid") Integer id) {
        return ms.findModuleById(id);
    }

    @DeleteMapping("/api/modules/{mId}")
    public void deleteModule(@PathVariable("mId") @RequestBody Integer id) {
        this.ms.deleteModule(id);
    }
}
