package com.tsp.controller.graphView;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.tsp.graph.Graph;
import java.util.ArrayList;
import com.tsp.graph.Edge;
import com.tsp.graph.Vertex;

public class graphView {
    private final ArrayList<vertexView> vertexViews = new ArrayList<>();
    private final ArrayList<edgeView> edgeViews = new ArrayList<>();
    private Graph graph= new Graph();


    public void addVertexView(vertexView vertexView1) {
        vertexViews.add(vertexView1);
        graph.addVertex(vertexView1.getV().getId());
        vertexViews.forEach(vertexView2 -> {

            if(!vertexView2.equals(vertexView1)){
                addEdgeView(new edgeView(vertexView1, vertexView2));
            }

        });
        //System.out.println("vertexview: "+graph.toString());
    }

    public void addEdgeView(edgeView edgeView) {
        edgeViews.add(edgeView);
        graph.addUndirectedGraphEdge(edgeView.getEdge().getSource().getId(),
                                     edgeView.getEdge().getDestination().getId(),
                                     edgeView.getWeight());
        //System.out.println("edgeview: "+graph.toString());
    }

    public edgeView getEdgeView(int start, int end) {
        for (edgeView edgeView : edgeViews) {
            if ((edgeView.getFrom().getIdVertex().equals(Integer.toString(start)) && edgeView.getTo().getIdVertex().equals(Integer.toString(end))) ||
                (edgeView.getTo().getIdVertex().equals(Integer.toString(start))  && edgeView.getFrom().getIdVertex().equals(Integer.toString(end))))
                return edgeView;
        }
        return null;
    }




    public  void highlight(Vertex vertex, boolean isLight) {

        vertexViews.forEach(vertexView -> {

            if(vertexView.getV().equals(vertex)){

                ((Circle) vertexView.getChildren().get(0)).setFill(isLight ? Color.ORANGE : Color.DODGERBLUE);
            }

        });

    }

    public  void highlight(Edge edge, boolean isLight) {

        edgeViews.forEach(edgeView -> {

            if(edgeView.getEdge().equals(edge)){
                edgeView.setStroke(isLight ? Color.ORANGE : Color.DODGERBLUE);
            }
        });

    }


    public ArrayList<vertexView> getVertexViews() {
        return vertexViews;
    }

    public ArrayList<edgeView> getEdgeViews() {
        return edgeViews;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }


    public static Graph example() {
        Graph graph = new Graph();
        return graph;
    }

}
