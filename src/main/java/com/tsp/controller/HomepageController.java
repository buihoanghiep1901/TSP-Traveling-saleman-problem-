package com.tsp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.tsp.GraphApplication;
import com.tsp.algorithm.Algorithm;
import com.tsp.context.Context;
import com.tsp.graph.Graph;
import com.tsp.controller.expGraphController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class HomepageController implements Initializable {

	@FXML
	private AnchorPane presentArea;

	private expGraphController exp;

	private static Graph graph = new Graph();
	private static Algorithm algorithm;
	private static final Context context = new Context();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void homePage() throws IOException {
		AnchorPane child = FXMLLoader.load(getClass().getResource("Welcomepage.fxml"));
		presentArea.getChildren().setAll(child);
		System.out.println("Welcome Page");
	}

	public void graphK4() throws IOException {

		try{
			System.out.println(" graph k4");
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/tsp/controller/expGraph.fxml")));

			//exp= loader.getController();
			//exp.setGraph(graph.graphK4());

			Scene newScene = new Scene(root);

			//newScene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
			GraphApplication.primaryStage.setScene(newScene);

		} catch(Exception e){
			e.printStackTrace();
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

}