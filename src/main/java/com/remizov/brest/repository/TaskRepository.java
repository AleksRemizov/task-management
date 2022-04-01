package com.remizov.brest.repository;


import com.remizov.brest.entity.Element;
import com.remizov.brest.entity.Task;
//import com.remizov.brest.entity.projection.TaskVieW;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * Update  {@link Task}to the data store.
     */
//    @Modifying
//    @Query(value = "update task \n" +
//            "set name = case when :name is not null then :name else name end,\n" +
//            "    password = case when :password is not null then :password else password end,\n" +
//            "    description = case when :description is not null then :description else description end, \n" +
//            "    start_date = case when :start_date is not null then :start_date else start_date end, \n" +
//            "    end_date = case when :end_date is not null then :end_date else end_date end, \n" +
//            "    status = case when :status is not null then :status else status end \n" +
//            " where task_id in :id",nativeQuery = true)
//    Integer update(@Param("id")Integer id, @Param("name")String name,
//                                        @Param("password")String password,
//                                        @Param("description")String description,
//                                        @Param("start_date")LocalDate startDate,
//                                        @Param("end_date")LocalDate endDate,
//                                        @Param("status")Integer status);

    /**
     * Retrieve all {@link Task}s from the data store.
     * @return a Collection of {@link Task}s.
     */
    List<Task> findAll();

    /**
     * Retrieve all {@link Task}s from the data store that have the specified text in name.
     * @return a Collection of {@link Task}s.
     */
    List<Task>findByNameContainingIgnoreCase(String value);

    /**
     * Retrieve all {@link Task}s from the data store that have the specified status.
     * @return a Collection of {@link Task}s.
     */
    List<Task> findByStatus(Integer status);

    /**
     * Retrieve all {@link Task}s from the data store that have startDate is greater than or equal
     * to the specified startDate and end date is less than or equal to the specified endDate.
     *
     * @return a Collection of {@link Task}s.
     */
    @Query (value = "select * from task where start_date >= :start and end_date <= :end ", nativeQuery = true)
     List<Task> searchBetweenDates(@Param("start") LocalDate startDate, @Param("end") LocalDate endDate);

}
