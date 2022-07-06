package com.tsp.controller;

import com.tsp.graph.Graph;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class expGraphController {

    @FXML
    private AnchorPane presentArea;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    private  Graph graph;

    public expGraphController(Graph graph){
        this.graph = graph;
    }

    public void homePage() throws IOException {
        AnchorPane child = FXMLLoader.load(getClass().getResource("Welcomepage.fxml"));
        presentArea.getChildren().setAll(child);
        System.out.println("Welcome Page");
    }

}
