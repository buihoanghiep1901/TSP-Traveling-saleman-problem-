package com.tsp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.tsp.app;
import com.tsp.algorithm.Algorithm;
import com.tsp.context.Context;
import com.tsp.graph.Graph;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

public class HomepageController implements Initializable {

	@FXML

	public Button codeTraceButton;
	public Button statusButton;

	public Label textOfShowStatus;
	public Label textOfShowCodeTrace;
	public AnchorPane main;

	public TextFlow status;
	public TextFlow codeTrace;

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