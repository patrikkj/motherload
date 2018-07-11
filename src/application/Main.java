package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	private static Game game;
	@Override
	public void start(Stage stage) throws Exception {
		Canvas canvas = new Canvas(Settings.sceneWidth.get(), Settings.sceneHeight.get());
		StackPane root = new StackPane(canvas);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		game = new Game(scene, root, canvas);
		new Thread(game).start();
		
		Settings.sceneWidth.bind(stage.widthProperty());
		Settings.sceneHeight.bind(stage.heightProperty());
		canvas.widthProperty().bind(Settings.sceneWidth);
		canvas.heightProperty().bind(Settings.sceneHeight);
		
		ChangeListener<Number> resizeListener = (observable, oldValue, newValue) ->
				game.getRenderEngine().resetCamera();
		canvas.widthProperty().addListener(resizeListener);
		canvas.heightProperty().addListener(resizeListener);
				
	}

	public static void main(String[] args) {
		launch(args);
	}
}
