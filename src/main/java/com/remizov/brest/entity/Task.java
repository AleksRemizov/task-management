package com.remizov.brest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Simple JavaBean domain object representing a task.
 *
 * @author Alex Remizov
 */
@Entity
@Table(name = "task")
@Schema(name="Task", description = "Class representing a task in the application.")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer id;

    @Schema(name = "Name", description = "name of the client")
    @NotBlank(message = "Name should not be empty")
    private String name;

    @JsonIgnore
    private String password;

    @Size(min = 0, max = 1000, message = "Task description must be between {min} and {max} characters.")
    private String description;

    @NotNull
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;


    @NotNull
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Incorrect value. Date can not be future")
    private LocalDate endDate;

    @NotNull(message = "Please provide status")
    private Integer status;

    @RestResource(exported = false)
    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Element> elements = new HashSet<>();

    protected Task(){
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, String password, String description, LocalDate startDate, LocalDate endDate,
                Integer status) {
        this.name = name;
        this.password = password;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Task setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Task setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Task setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Task setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Task setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public void addElement(Element element) {
        this.elements.add(element);
        element.setTask(this);
    }

    public void removeElement(Element element) {
        this.elements.remove(element);
        element.setTask(this);
    }

    public Set<Element> getElements() {
        return elements;
    }

    public void setElement(Element element) {
        elements.add(element);
    }
}
