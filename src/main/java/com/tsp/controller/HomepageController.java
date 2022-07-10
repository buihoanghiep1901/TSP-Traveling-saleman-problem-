package com.tsp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.tsp.app;
import com.tsp.controller.graphView.graphView;
import com.tsp.controller.graphView.vertexView;
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
import com.tsp.algorithm.DynamicProgramming;
import com.tsp.algorithm.Approximation;



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
		status.setVisible(!status.isVisible());
	}

	public void showCodeTrace(){
		codeTrace.setVisible(!codeTrace.isVisible());
	}

	public void drawGraph(MouseEvent mouseEvent) {
		Node location = mouseEvent.getPickResult().getIntersectedNode();
		//System.out.println(location);
		if (location == drawBoard) {
			vertexView vertexViewNode = new vertexView();
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

	private void addOrRemoveVertex(vertexView vertexView1) {
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

	public void run() {
		Algorithm bf = new BruteForce();
		bf.setGraph(graphView.getGraph());
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
					text.setStyle("-fx-font-size: 16px");
					Platform.runLater(() -> codeTrace.getChildren().add(text));
				}
				/*for (PseudoStep step : bf.getPseudoSteps()) {
					Platform.runLater(() -> {
						codeTrace.getChildren().forEach(node -> node.setStyle("-fx-font-weight: normal"));
						int idPseudo = Integer.parseInt(step.getText());
						if (idPseudo != -1)
							codeTrace.getChildren().get(idPseudo).setStyle("-fx-font-weight: bold");
					});

					for (Step detail : bf.getStepList()) {
						Platform.runLater(() -> {
							if (detail.getText().length() > 0) {
								status.getChildren().clear();
								status.getChildren().add(new Text(detail.getText()));
							}
							Platform.runLater(detail::run);
						});
					}*/
				Platform.runLater(detail::run);
					Thread.sleep(2000);
				}
				return null;

		}

		new Thread(task).start();
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