package com.tsp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class GraphApplication extends Application {

	public static Stage stage;

	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			Parent root = FXMLLoader.load(GraphApplication.class.getResource("/com/tsp/controller/Homepage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/tsp/controller/application.css")).toExternalForm());
			stage = new Stage();
			stage.setScene(scene);
			stage.show();
			stage.setTitle("Visualization Application");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
