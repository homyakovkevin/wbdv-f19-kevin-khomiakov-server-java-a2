package com.example.myapp_a2.services;
import com.example.myapp_a2.models.Module;
import com.example.myapp_a2.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ModuleService {
    @Autowired
    ModuleRepository mr;

    public void createModule(Module m) {
        mr.save(m);
    }
    public List<Module> findAllModules(){
        return  mr.findAllModules();
    }
    public List<Module> findAllModulesForCourse(Integer cid){
        return mr.findAllModulesForCourse(cid);
    }

    public Module findModuleById(Integer id){
        return mr.findModuleById(id);
    }

    public Module updateModule(Integer mid, Module module) {
        Module current=mr.findModuleById(mid);
        current.setLessons(module.getLessons());
        current.setTitle(module.getTitle());
        return mr.save(current);
    }

    public void deleteModule(Integer mid) {
        Module m=mr.findModuleById(mid);
        mr.delete(m);
    }
}
