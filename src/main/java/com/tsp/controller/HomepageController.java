package com.tsp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.tsp.app;
import com.tsp.algorithm.Algorithm;
import com.tsp.context.Context;
import com.tsp.controller.graphView.graphView;
import com.tsp.controller.graphView.vertexView;
import com.tsp.graph.Graph;
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
import javafx.scene.text.TextFlow;


public class HomepageController implements Initializable {

	@FXML

	public AnchorPane drawBoard;

	public TextFlow status;
	public Button codeTraceButton;
	public Label textOfShowStatus;

	public TextFlow codeTrace;
	public Button statusButton;
	public Label textOfShowCodeTrace;

	public graphView graphView = new graphView();

	Robot robot = new Robot();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void graphK4() throws IOException {

		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("expGraph.fxml")));
		Scene newScene = new Scene(root);
		app.stage.setScene(newScene);
		app.stage.show();

		System.out.println(" graph k4");
	}

	public void showStatus(){
		if(status.isVisible()){
			status.setVisible(false);
		} else{
			status.setVisible(true);
		}
	}

	public void showCodeTrace(){
		if(codeTrace.isVisible()){
			codeTrace.setVisible(false);
		} else{
			codeTrace.setVisible(true);
		}
	}

	public void addOrLink(MouseEvent mouseEvent) {
		Node location = mouseEvent.getPickResult().getIntersectedNode();
		//System.out.println(location);
		if (location == drawBoard) {
			vertexView node = new vertexView();
			double x = robot.getMouseX() - drawBoard.localToScreen(drawBoard.getBoundsInLocal()).getMinX() - 22;
			double y = robot.getMouseY() - drawBoard.localToScreen(drawBoard.getBoundsInLocal()).getMinY() - 22;
			x = Math.min(x, drawBoard.getPrefWidth() - 44);
			y = Math.min(y, drawBoard.getPrefHeight() - 44);
			node.setLayoutX(x);
			node.setLayoutY(y);
			node.setLabel(graphView.getVertexViews().size());
			drawBoard.getChildren().add(node);
			refreshVertex(node);

			node.setOnMouseEntered(mouseEvent1 -> node.requestFocus());
			node.setOnKeyPressed(keyEvent -> {
				if (keyEvent.getCode() == KeyCode.DELETE) {
					refreshVertex(node);
				}
			});
		}
		System.out.println(graphView.getGraph().toString());

	}

	private void refreshNode() {
		graphView.getVertexViews().forEach(stackPane -> ((Label) stackPane.getChildren().get(1)).setText(graphView.getVertexViews().indexOf(stackPane) +""));
	}

	private void refreshVertex(vertexView vertex) {
		if (!graphView.getVertexViews().contains(vertex)) {
			graphView.addVertexView(vertex);
			graphView.getEdgeViews().forEach(edgeView -> {
				if (!drawBoard.getChildren().contains(edgeView))
					drawBoard.getChildren().addAll(edgeView, edgeView.getLabel());
			});
		} else {
			graphView.getEdgeViews().removeIf(edgeView -> {
				if (edgeView.getFrom().equals(vertex) || edgeView.getTo().equals(vertex)) {
					graphView.getGraph().removeEdge(edgeView.getEdge().getSource().getId(),
													edgeView.getEdge().getDestination().getId());
					drawBoard.getChildren().removeAll(edgeView, edgeView.getLabel());
					return true;
				} else
					return false;
			});
			drawBoard.getChildren().remove(vertex);
			graphView.getVertexViews().remove(vertex);
			refreshNode();
		}
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