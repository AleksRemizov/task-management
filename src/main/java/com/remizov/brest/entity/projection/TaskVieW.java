package com.remizov.brest.entity.projection;

import com.remizov.brest.entity.Element;
import com.remizov.brest.entity.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

/**
 * Simple TaskView projection with hidden password field, added getter count amount of elements.
 *
 * @author Alex Remizov
 */


@Projection(name = "taskView",types = Task.class)
public interface TaskVieW {

    String getName();
    String getDescription();
    LocalDate getStartDate();
    LocalDate getEndDate();
    Integer getStatus();

}
