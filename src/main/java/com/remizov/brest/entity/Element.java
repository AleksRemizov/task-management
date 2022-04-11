package com.remizov.brest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
/**
 * Simple JavaBean domain object representing an element.
 *
 * @author Alex Remizov
 */
@ApiModel(description = "Class representing a task's element in the application.")
@Entity
@Table(name = "element")
@Schema(name="Element", description = "Element")
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "element_id")
    private Integer id;

    private String name;

    private String description;

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
