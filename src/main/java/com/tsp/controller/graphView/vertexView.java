package com.tsp.controller.graphView;
import  com.tsp.graph.Vertex;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class vertexView extends StackPane {

    private Vertex v;
    private final Label label = new Label();


    public vertexView() {
        this.setPrefSize(2, 14);
        Circle circle = new Circle();
        circle.setRadius(22);
        circle.setFill(Color.DODGERBLUE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.INSIDE);
        this.getChildren().addAll(circle, label);
    }

    public vertexView(Vertex v) {
        this.setPrefSize(2, 14);
        this.v=v;
        Circle circle = new Circle();
        circle.setRadius(22);
        circle.setFill(Color.DODGERBLUE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.INSIDE);
        label.setText(this.v.getId() + "");
        this.getChildren().addAll(circle, label);
    }

    public String getIdVertex(){
        return this.v.getId();
    }

    public Label getLabel() {
        return this.label;
    }

    public void setLabel(int id) {
        this.v.setId(Integer.toString(id));
        this.label.setText(this.v.getId() + "");
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }
}
