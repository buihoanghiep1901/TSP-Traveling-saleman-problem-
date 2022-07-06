package com.tsp.step;

public class Step {
    private Integer id;
    private String description;

    public Step(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public String toString() {
        return "Step " + id + ": " + description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
