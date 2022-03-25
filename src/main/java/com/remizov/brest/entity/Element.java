package com.remizov.brest.entity;

import javax.persistence.*;
/**
 * Simple JavaBean domain object representing an element.
 *
 * @author Alex Remizov
 */

@Entity
@Table(name = "element")
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

    public Task getTask() {
        return task;
    }

    public Element(){
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

}
