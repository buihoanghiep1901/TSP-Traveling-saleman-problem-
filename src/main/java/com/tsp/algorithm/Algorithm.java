package com.tsp.algorithm;

import com.tsp.graph.Graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tsp.step.EdgeViewStep;
import com.tsp.step.Step;
import com.tsp.step.VertexViewStep;

public abstract class Algorithm {

    protected Graph graph;

    protected List<Step> stepList = new ArrayList<>(); //current step
    protected HashMap<Integer,String> pseudoStep = new HashMap<>(); // codetrace


    public abstract void run();

    public abstract void showStep();

    public Graph getGraph() {
        return graph;
    }
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }

    public HashMap<Integer, String> getPseudoStep() {
        return pseudoStep;
    }

    public void setPseudoStep(HashMap<Integer, String> pseudoStep) {
        this.pseudoStep = pseudoStep;
    }
}
