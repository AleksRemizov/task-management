package com.remizov.brest.repository;


import com.remizov.brest.entity.Element;
import com.remizov.brest.entity.Task;
//import com.remizov.brest.entity.projection.ElementView;
//import com.remizov.brest.entity.projection.TaskVieW;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(excerptProjection = ElementView.class)
public interface ElementRepository extends CrudRepository<Element,Integer> {

    /**
     * Save an {@link Element} to the data store, either inserting or updating it.
     * @param element the {@link Element} to save
     * @return the {@link Element} if found
     */

    Element save(Element element);

    /**
     * Delete an {@link Element} to the data store.
     * @param id the {@link Element} to delete
     */

     void deleteById(Integer id);

    /**
     * Update  {@link Element}to the data store.
     * @return the {@link Element} updated .
     */

    //Element update(Integer id,Element element);

    /**
     * Retrieve all {@link Element}s from the data store.
     * @return a Collection of {@link Element}s.
     */

     List<Element> findAll();

    /**
     * Retrieve all {@link Element}s from the data store which contain specific text in the value.
     * @return a Collection of {@link Element}s.
     */

      //@Query(value = "select e from Element e where e.value like '%:text%'")
      List<Element> findByValueContaining(@Param("text") String text);

    /**
     * Retrieve all {@link Element}s from the data store  associated with the {@link Task} with the specified id.
     * @return a Collection of {@link Element}s.
     */

     //@Query(value = "select e from Element e where e.task_id = :id")
     List<Element> findByTask(@Param("task_id") Integer taskId);
}
