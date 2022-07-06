package com.tsp.graph;


public class Vertex implements Comparable<Vertex> {
    private String id; // id of the vertex

    public Vertex(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.id.compareTo(o.getId());
    }

}
