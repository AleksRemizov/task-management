package com.remizov.brest.repository;

import org.junit.jupiter.api.Test;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class ProjectionTest {

    private ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Test
    void createMapBackedProjection() {

        var task = factory.createProjection(Task.class);
        task.setName("NewTask");
        task.setDescription("NewDescription");
        task.setStartDate(LocalDate.of(2022,4,6));
        task.setEndDate(LocalDate.of(2022,4,7));
        task.setStatus(200);

        // Verify accessors work
        assertThat(task.getName()).isEqualTo("NewTask");
        assertThat(task.getDescription()).isEqualTo("NewDescription");
        assertThat(task.getStartDate()).isEqualTo(LocalDate.of(2022,4,6));
        assertThat(task.getEndDate()).isEqualTo(LocalDate.of(2022,4,7));
        assertThat(task.getStatus()).isEqualTo(200);
    }

    @Test
    void createsProxyForSourceMap() {

        Map<String, Object> backingMap = new HashMap<>();
        backingMap.put("name", "NewTask");
        backingMap.put("description", "NewDescription");
        backingMap.put("startDate", LocalDate.of(2022,4,6));
        backingMap.put("endDate", LocalDate.of(2022,4,7));
        backingMap.put("status", 200);



        var task = factory.createProjection(Task.class, backingMap);

        // Verify accessors work
        assertThat(task.getName()).isEqualTo("NewTask");
        assertThat(task.getDescription()).isEqualTo("NewDescription");
        assertThat(task.getStartDate()).isEqualTo(LocalDate.of(2022,4,6));
        assertThat(task.getEndDate()).isEqualTo(LocalDate.of(2022,4,7));
        assertThat(task.getStatus()).isEqualTo(200);
    }

    interface Task {

        String getName();
        void setName(String name);

        String getDescription();
        void setDescription(String description);

        LocalDate getStartDate();
        LocalDate getEndDate();

        void setStartDate(LocalDate startDate);
        void setEndDate(LocalDate endDate);

        Integer getStatus();
        void setStatus(Integer status);

    }
}
