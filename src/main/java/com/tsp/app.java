package com.tsp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class app extends Application {

	public static Stage stage;

	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			Parent root = FXMLLoader.load(app.class.getResource("app.fxml"));
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);

			root.setOnMousePressed(mouseEvent -> {
				xOffset = mouseEvent.getSceneX();
				yOffset = mouseEvent.getSceneY();
			});
			root.setOnMouseDragged(mouseEvent -> {
				stage.setX(mouseEvent.getScreenX() - xOffset);
				stage.setY(mouseEvent.getScreenY() - yOffset);
			});

			stage.setTitle("Visualization Application");
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
