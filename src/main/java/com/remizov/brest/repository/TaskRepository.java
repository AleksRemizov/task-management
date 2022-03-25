package com.remizov.brest.repository;


import com.remizov.brest.entity.Task;
//import com.remizov.brest.entity.projection.TaskVieW;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

//@RepositoryRestResource(excerptProjection = TaskVieW.class)
public interface TaskRepository extends CrudRepository<Task,Integer> {

    /**
     * Save an {@link Task} to the data store, either inserting or updating it.
     *
     * @param task the {@link Task} to save,update or either inserting or updating it.
     * @return the {@link Task} saved
     */

    Task save(Task task);

    /**
     * Delete an {@link Task} to the data store.
     *
     * @param id the {@link Task} to delete
     */
     void deleteById(Integer id);

    /**
     * Retrieve all {@link Task}s from the data store.
     * @return a Collection of {@link Task}s.
     */

    List<Task> findAll();

    /**
     * Retrieve all {@link Task}s from the data store that have the specified text in name.
     * @return a Collection of {@link Task}s.
     */

    List<Task>findByNameContaining(String value);

    /**
     * Retrieve all {@link Task}s from the data store that have the specified status.
     * @return a Collection of {@link Task}s.
     */

    List<Task> findByStatusLike(Integer status);

    /**
     * Retrieve all {@link Task}s from the data store that have startDate is greater than or equal
     * to the specified startDate and end date is less than or equal to the specified endDate.
     *
     * @return a Collection of {@link Task}s.
     */

     List<Task> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startDate, LocalDate endDate);

}
