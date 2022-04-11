package com.remizov.brest.repository;


import com.remizov.brest.entity.Element;
import com.remizov.brest.entity.Task;
import com.remizov.brest.entity.projection.TaskVieW;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Task", description = "The Task API")
@RepositoryRestResource(excerptProjection = TaskVieW.class)
public interface TaskRepository extends CrudRepository<Task,Integer> {

    /**
     * Save , Update   an {@link Task} to the data store, either inserting or updating it.
     *
     * @param task the {@link Task} to save,update or either inserting or updating it.
     * @return the {@link Task} saved
     */
    @Operation(summary = "Save task into the database",
            responses = {
                    @ApiResponse(description = "Successfully saved",
                            content = @Content(schema = @Schema(implementation = Task.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid task")})
    Task save(Task task);

    /**
     * Delete an {@link Task} to the data store.
     *
     * @param id the {@link Task} to delete
     */
    @Operation(summary = "Delete task",
            description = "This can only be done by the logged in user.")
    @ApiResponse(responseCode = "200", description = "Task deleted")
    @ApiResponse(responseCode = "400", description = "Invalid id supplied")
    @ApiResponse(responseCode = "404", description = "Task not found")
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
    @ApiOperation("Find tasks that have the specified text in name")
    List<Task>findByNameContainingIgnoreCase(String value);

    /**
     * Retrieve all {@link Task}s from the data store that have the specified status.
     * @return a Collection of {@link Task}s.
     */
    @ApiOperation("Find tasks that have the specified status")
    List<Task> findByStatus(Integer status);

    /**
     * Retrieve all {@link Task}s from the data store that have startDate is greater than or equal
     * to the specified startDate and end date is less than or equal to the specified endDate.
     *
     * @return a Collection of {@link Task}s.
     */
    @ApiOperation("Find tasks that have startDate is greater than or equal\n" +
            "     * to the specified startDate and end date is less than or equal to the specified endDate")
    @Query (value = "select * from task where start_date >= :start and end_date <= :end ", nativeQuery = true)
     List<Task> searchBetweenDates(@Param("start") LocalDate startDate, @Param("end") LocalDate endDate);

}
