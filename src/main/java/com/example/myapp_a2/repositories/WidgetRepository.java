package com.example.myapp_a2.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.myapp_a2.models.Widget;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface WidgetRepository extends CrudRepository<Widget, Integer> {
    @Query("select widget from Widget widget")
    public List<Widget> findAllWidgets();

    @Query("select widget from Widget widget where widget.topic.id = :tid")
    public List<Widget> findAllWidgetsForTopic(@Param("tid") Integer tid);

    @Query("select widget from Widget widget where widget.id=:wid")
    public Widget findWidgetById(@Param("wid") Integer id);
}
