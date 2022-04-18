package com.remizov.brest.entity;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Simple JavaBean domain object representing an element.
 *
 * @author Alex Remizov
 */

@Entity
@Table(name = "element")
@Api(value = "Class representing a task's element in the application")
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "element_id")
    private Integer id;



    @Schema(name = "trackName", description = "name of track", example = "New name")
    @NotBlank(message = "Name should not be empty")
    private String name;

    @Size(min = 0, max = 1000, message = "Element description must be between {min} and {max} characters.")
    private String description;

    @NotBlank(message = "Please provide status")
    private String value;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    protected Element(){
    }

    public Element(String name, String description, String value, Task task) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.task = task;
    }

    public Integer getId() {
        return id;
    }

    public Element setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Element setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Element setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Element setValue(String value) {
        this.value = value;
        return this;
    }
    public Task getTask() {
        return task;
    }

    public Element setTask(Task task) {
        this.task = task;
        return this;
    }
}
