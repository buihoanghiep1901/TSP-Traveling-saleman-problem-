package com.tsp.step;

import com.tsp.graph.Edge;

public class EdgeViewStep {
    private Edge edge;

    private boolean highLighted;

    public EdgeViewStep(Edge edge, boolean highLighted){

        this.edge = edge;

        this.highLighted = highLighted;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public boolean isHighLighted() {
        return highLighted;
    }

    public void setHighLighted(boolean highLighted) {
        this.highLighted = highLighted;
    }
}
