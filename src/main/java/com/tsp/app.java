package com.tsp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class app extends Application {

	public static Stage stage;

	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			Parent root = FXMLLoader.load(app.class.getResource("app.fxml"));
			Scene scene = new Scene(root);
			stage= new Stage();
			stage.setScene(scene);
			stage.setTitle("TSP Visualization Application");
			stage.setAlwaysOnTop(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
