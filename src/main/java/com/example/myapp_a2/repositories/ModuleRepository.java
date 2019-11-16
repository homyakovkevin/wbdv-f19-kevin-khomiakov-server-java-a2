package com.example.myapp_a2.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.myapp_a2.models.Module;
import java.util.*;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Integer> {
    @Query("select module from Module module")
    public List<Module> findAllModules();

    // select module from Module m, Course c where m.course_id = c.id
    @Query("select module from Module module where module.course.id = :cid")
    public List<Module> findAllModulesForCourse(@Param("cid") Integer courseId);

    @Query("select module from Module module where module.id = :id")
    public Module findModuleById(@Param("id") Integer id);
}