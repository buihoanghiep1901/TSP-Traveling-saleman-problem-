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

    public boolean isHighLighted() {
        return highLighted;
    }


}
