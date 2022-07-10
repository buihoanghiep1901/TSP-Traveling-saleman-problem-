package com.tsp.step;

import com.tsp.graph.Vertex;

public class VertexViewStep {
    private Vertex vertex;

    private boolean Highlighted;

    public VertexViewStep( Vertex vertex,boolean Highlighted) {
        this.vertex = vertex;
        this.Highlighted = Highlighted;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public boolean isHighlighted() {
        return Highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        Highlighted = highlighted;
    }

}
