package com.tsp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.tsp.app;
import com.tsp.controller.graphView.GraphView;
import com.tsp.controller.graphView.VertexView;
import com.tsp.graph.Graph;
import com.tsp.graph.Vertex;
import com.tsp.step.Step;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.robot.Robot;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import com.tsp.algorithm.Algorithm;
import com.tsp.algorithm.BruteForce;


public class HomepageController implements Initializable {
	@FXML

	public AnchorPane drawBoard;

	public TextFlow status;
	public Button codeTraceButton;
	public Label textOfShowStatus;

	public TextFlow codeTrace;
	public Button statusButton;
	public Label textOfShowCodeTrace;

	public GraphView graphView = new GraphView();

	Robot robot = new Robot();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
/********************************************Step********************************************************/

	public void showStatus(){
		status.setVisible(!status.isVisible());
	}

	public void showCodeTrace(){
		codeTrace.setVisible(!codeTrace.isVisible());
	}

/************************************Graph*******************************************************/
	public void drawGraph(MouseEvent mouseEvent) {
		Node location = mouseEvent.getPickResult().getIntersectedNode();
		//System.out.println(location);
		if (location == drawBoard) {
			VertexView vertexViewNode = new VertexView();
			double x = robot.getMouseX() - drawBoard.localToScreen(drawBoard.getBoundsInLocal()).getMinX() - 22;
			double y = robot.getMouseY() - drawBoard.localToScreen(drawBoard.getBoundsInLocal()).getMinY() - 22;

			x = Math.min(x, drawBoard.getPrefWidth() - 44);
			y = Math.min(y, drawBoard.getPrefHeight() - 44);

			vertexViewNode.setLayoutX(x);
			vertexViewNode.setLayoutY(y);
			vertexViewNode.setLabel(graphView.getVertexViews().size());

			drawBoard.getChildren().add(vertexViewNode);
			addOrRemoveVertex(vertexViewNode);

			vertexViewNode.setOnMouseEntered(mouseEvent1 -> vertexViewNode.requestFocus());

			vertexViewNode.setOnKeyPressed(keyEvent -> {
				if (keyEvent.getCode() == KeyCode.DELETE) {
					addOrRemoveVertex(vertexViewNode);
				}
			});
		}
		System.out.println(graphView.getGraph().toString());

	}

	private void refreshIdVertex() {
		graphView.getVertexViews().forEach(stackPane -> {

			((Label) stackPane.getChildren().get(1)).setText(graphView.getVertexViews().indexOf(stackPane) +"");

			stackPane.getV().setId(Integer.toString(graphView.getVertexViews().indexOf(stackPane)));

		});

		graphView.getGraph().getVertices().forEach(vertex ->{
			vertex.setId(Integer.toString(graphView.getGraph().getVertices().indexOf(vertex)));
		});
	}

	private void addOrRemoveVertex(VertexView vertexView1) {
		if (!graphView.getVertexViews().contains(vertexView1)) {

			graphView.addVertexView(vertexView1);

			graphView.getEdgeViews().forEach(edgeView -> {

				if (!drawBoard.getChildren().contains(edgeView)){

					drawBoard.getChildren().addAll(edgeView, edgeView.getLabel());

				}
			});
		} else {

			graphView.getVertexViews().removeIf(vertexView2 ->{
				if(vertexView2.getIdVertex().equals(vertexView1.getIdVertex())){

					drawBoard.getChildren().remove(vertexView1);

					graphView.getGraph().removeVertex(vertexView1.getIdVertex());

					return true;

				} else{

					return false;
				}


			});

			graphView.getEdgeViews().removeIf(edgeView -> {
				if (edgeView.getFrom().equals(vertexView1) || edgeView.getTo().equals(vertexView1)) {

					graphView.getGraph().removeEdge(edgeView.getEdge().getSource().getId(),
													edgeView.getEdge().getDestination().getId());
					drawBoard.getChildren().removeAll(edgeView, edgeView.getLabel());

					return true;
				} else{

					return false;
				}

			});

			refreshIdVertex();
		}
	}

/*******************************Algorithm************************************************/
	public void BruteForce() {
		Algorithm bf = new BruteForce();
		bf.setGraph(graphView.getGraph());
		bf.run();
		Task<Void> task = new Task<>() {

			@Override

			public Void call() throws Exception {
				Platform.runLater(() -> codeTrace.getChildren().clear());

				if (!codeTrace.isVisible())
					showCodeTrace();

				if (!status.isVisible())
					showStatus();

				for (int i = 0; i < bf.getPseudoStep().size(); i++) {
					Text text = new Text(bf.getPseudoStep().get(i));
					text.setStyle("-fx-font-size: 16.5px");
					Platform.runLater(() -> codeTrace.getChildren().add(text));
				}

				for (Step step : bf.getStepList()) {
					Platform.runLater(() -> {
						status.getChildren().clear();

						Text text = new Text(step.toString());

						text.setStyle("-fx-font-size: 16.5px");

						status.getChildren().add(text);

						codeTrace.getChildren().forEach(node -> node.setStyle("-fx-font-weight: normal;-fx-font-size: 16.5px;"));

						for (int i = 0; i < bf.getPseudoStep().size(); i++) {
							if (step.getId() == i) {
								codeTrace.getChildren().get(i).setStyle("-fx-font-weight: bold;-fx-font-size: 16.5px");
							}
						}

						if( step.getEdgeStep()!= null &&  step.getEdgeStep().getEdge()!=null){
							graphView.highlight(step.getEdgeStep().getEdge(), step.getEdgeStep().isHighLighted());

						}

						if(step.getVertexStep()!= null && step.getVertexStep().getVertex()!= null){
							graphView.highlight(step.getVertexStep().getVertex(), step.getVertexStep().isHighLighted());

						}




						//Platform.runLater(step::run);
					});
					Thread.sleep(1000);
				}

				return null;
			}


		};

		new Thread(task).start();
	}

	/*******************************Example***************************************************/
	public void graphK4(){

		double[] xlayout = {445.0,	260.0,	630.0,	445.0};
		double[] ylayout = {67.0,	334.0,	334.0,	245.0};

		for(int i=0; i< Graph.graphK4().getVertices().size();i++){
			VertexView vertexViewNode = new VertexView(Graph.graphK4().getVertices().get(i));
			vertexViewNode.setLayoutX(xlayout[i]);
			vertexViewNode.setLayoutY(ylayout[i]);
			drawBoard.getChildren().add(vertexViewNode);
			addOrRemoveVertex(vertexViewNode);
		}
		System.out.println(graphView.getGraph().toString());
	}

	public void graphK5(){

		double[] xlayout = {459.0,	256.0,	328.0,	590.0,	662.0};
		double[] ylayout = {38.0, 136.0, 336.0, 336.0, 136.0};

		for(int i=0; i< Graph.graphK5().getVertices().size();i++){
			VertexView vertexViewNode = new VertexView(Graph.graphK5().getVertices().get(i));
			vertexViewNode.setLayoutX(xlayout[i]);
			vertexViewNode.setLayoutY(ylayout[i]);
			drawBoard.getChildren().add(vertexViewNode);
			addOrRemoveVertex(vertexViewNode);
		}
		System.out.println(graphView.getGraph().toString());
	}

	public void graphK8(){

		double[] xlayout = {327.0, 145.0, 145.0, 327.0, 563.0, 733.0, 733.0, 563.0};
		double[] ylayout = {3.0, 124.0, 356.0, 474.0, 474.0, 356.0,  124.0, 3.0};

		for(int i=0; i< Graph.graphK8().getVertices().size();i++){
			VertexView vertexViewNode = new VertexView(Graph.graphK8().getVertices().get(i));
			vertexViewNode.setLayoutX(xlayout[i]);
			vertexViewNode.setLayoutY(ylayout[i]);
			drawBoard.getChildren().add(vertexViewNode);
			addOrRemoveVertex(vertexViewNode);
		}
		System.out.println(graphView.getGraph().toString());
	}



	public void clear(){
		graphView.getVertexViews().clear();
		graphView.getEdgeViews().clear();
		graphView.getGraph().getVertices().clear();
		graphView.getGraph().getEdges().clear();
		drawBoard.getChildren().clear();
	}



























































	/*ublic void homePage() throws IOException {
		AnchorPane child = FXMLLoader.load(getClass().getResource("Welcomepage.fxml"));
		presentArea.getChildren().setAll(child);
		System.out.println("Welcome Page");
	}

	public void homePage() throws IOException {
		AnchorPane child = FXMLLoader.load(getClass().getResource("Welcomepage.fxml"));
		presentArea.getChildren().setAll(child);
		System.out.println("Welcome Page");
	}


	/*public void queuePage() throws IOException {
		System.out.println("Queue Page");
		AnchorPane child = FXMLLoader.load(getClass().getResource("/application/Queuepage.fxml"));
		presentArea.getChildren().setAll(child);
	}

	public void arrayListPage() throws IOException {
		System.out.println("Array List Page");
		AnchorPane child = FXMLLoader.load(getClass().getResource("/application/Arraylistpage.fxml"));
		presentArea.getChildren().setAll(child);
	}

	public void hashTablePage() throws IOException {
		System.out.println("HashTable Page");
		AnchorPane child = FXMLLoader.load(getClass().getResource("/application/Hashtablepage.fxml"));
		presentArea.getChildren().setAll(child);
	}*/

	/*public void homePage() throws IOException {
		AnchorPane child = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Welcomepage.fxml")));
		presentArea.getChildren().setAll(child);
		System.out.println("Welcome Page");
	}*/

}