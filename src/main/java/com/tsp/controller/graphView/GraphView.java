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

            }

        });
    }

    public void addEdgeView(EdgeView edgeView) {
        EdgeViews.add(edgeView);

        graph.addUndirectedGraphEdge(edgeView.getEdge().getSource().getId(),
                                    edgeView.getEdge().getDestination().getId(),
                                    edgeView.getWeight());
        //System.out.println("edgeview: "+graph.toString());
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
        String id1=edge.getSource().getId();

        String id2=edge.getDestination().getId();

        EdgeViews.forEach(EdgeView -> {
            //System.out.println("edge highlited");
            String id3=EdgeView.getEdge().getSource().getId();

            String id4=EdgeView.getEdge().getDestination().getId();

            if( (id3.equals(id1) && id4.equals(id2)) || (id3.equals(id2) && id4.equals(id1)) ){

                if(isLight){
                    EdgeView.setStroke(Color.ORANGE);
                    EdgeView.setStrokeWidth(4);

                }else{
                    EdgeView.setStroke(Color.BLACK);
                    EdgeView.setStrokeWidth(1);

                }

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


}
