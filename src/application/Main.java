package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	private static Game game;
	@Override
	public void start(Stage stage) throws Exception {

		Pane root = new Pane();
		Scene scene = new Scene(root, Settings.sceneWidth.get(), Settings.sceneHeight.get(), Settings.sceneColor.get());

		stage.setScene(scene);
		stage.show();
		
		game = new Game(scene, root);
		game.run();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
