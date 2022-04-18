package com.remizov.brest.repository;


import com.remizov.brest.entity.Element;
import com.remizov.brest.entity.Task;
import com.remizov.brest.entity.projection.ElementView;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
@Validated
@Tag(name = "Element", description = "The Element API")
@RepositoryRestResource(excerptProjection = ElementView.class)
public interface ElementRepository extends CrudRepository<Element,Integer> {

    /**
     * Save an {@link Element} to the data store, either inserting or updating it.
     * @param element the {@link Element} to save
     * @return the {@link Element} if found
     */
    @ApiOperation("Save element")
    Element save(@Valid Element element);

    /**
     * Delete an {@link Element} to the data store.
     * @param id the {@link Element} to delete
     */
    @ApiOperation("Delete element depend on id")
    @Modifying
    @Query(value = "Delete Element WHERE element_id =:id")
     void removeById(@Param("id")Integer id);

    /**
     * Retrieve all {@link Element}s from the data store.
     * @return a Collection of {@link Element}s.
     */
    @ApiOperation("Find all elements")
     List<Element> findAll();

    /**
     * Retrieve all {@link Element}s from the data store which contain specific text in the value.
     * @return a Collection of {@link Element}s.
     */
    @ApiOperation("Find elements which contain specific text in the value")
      List<Element> findByValueContainingIgnoreCase(String text);

    /**
     * Retrieve all {@link Element}s from the data store  associated with the {@link Task} with the specified id.
     * @return a Collection of {@link Element}s.
     */
    @ApiOperation("Find elements which contain specific text in the value")
     List<Element> findByTaskId(Integer taskId);
}

