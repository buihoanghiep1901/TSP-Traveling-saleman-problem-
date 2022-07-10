package com.tsp.step;

import com.tsp.graph.Vertex;

public class VertexViewStep {
    private Vertex vertex;
    private boolean Highlighted;


    public VertexViewStep( Vertex vertex,boolean Highlighted) {
        this.vertex = vertex;
        this.Highlighted = Highlighted;
    }

}
