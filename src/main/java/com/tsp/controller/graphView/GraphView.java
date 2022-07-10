package com.tsp.controller.graphView;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.tsp.graph.Graph;
import java.util.ArrayList;
import com.tsp.graph.Edge;
import com.tsp.graph.Vertex;

public class GraphView {
    private final ArrayList<VertexView> VertexViews = new ArrayList<>();
    private final ArrayList<EdgeView> EdgeViews = new ArrayList<>();
    private Graph graph= new Graph();


    public void addVertexView(VertexView vertexView1) {
        VertexViews.add(vertexView1);
        graph.addVertex(vertexView1.getV().getId());
        VertexViews.forEach(vertexView2 -> {

            if(!vertexView2.equals(vertexView1)){
                addEdgeView(new EdgeView(vertexView1, vertexView2));

                addEdgeView(new EdgeView(vertexView2, vertexView1));
            }

        });
        //System.out.println("vertexview: "+graph.toString());
    }

    public void addEdgeView(EdgeView edgeView) {
        EdgeViews.add(edgeView);

        graph.addEdge(edgeView.getEdge().getSource().getId(),
                      edgeView.getEdge().getDestination().getId(),
                      edgeView.getWeight());
        //System.out.println("edgeview: "+graph.toString());
    }

    public EdgeView getEdgeView(int start, int end) {
        for (EdgeView edgeView : EdgeViews) {
            if ((edgeView.getFrom().getIdVertex().equals(Integer.toString(start)) && edgeView.getTo().getIdVertex().equals(Integer.toString(end))) ||
                (edgeView.getTo().getIdVertex().equals(Integer.toString(start))  && edgeView.getFrom().getIdVertex().equals(Integer.toString(end))))
                return edgeView;
        }
        return null;
    }


    public  void highlight(Vertex vertex, boolean isLight) {

        VertexViews.forEach(VertexView -> {
            //System.out.println("vertex highlited");

            if(VertexView.getV().compareTo(vertex) ==0){

                ((Circle) VertexView.getChildren().get(0)).setFill(isLight ? Color.ORANGE : Color.DODGERBLUE);
                System.out.println("vertex highlited");

            }

        });

    }

    public  void highlight(Edge edge, boolean isLight) {

        EdgeViews.forEach(EdgeView -> {
            //System.out.println("edge highlited");

            if(EdgeView.getEdge().compareTo(edge) ==0){
                EdgeView.setStroke(isLight ? Color.ORANGE : Color.BLACK);
                System.out.println("edge highlited");

            }
        });

    }


    public ArrayList<VertexView> getVertexViews() {
        return VertexViews;
    }

    public ArrayList<EdgeView> getEdgeViews() {
        return EdgeViews;
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
