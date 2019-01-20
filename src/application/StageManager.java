package application;

import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import misc.Settings;
import rendering.Renderer;

public class StageManager {
	// Layout references
	private Canvas canvas;
	private GraphicsContext graphicsContext;
	private StackPane root;
	private Scene scene;
	private Stage stage;
	
	
	public StageManager(Stage stage) {
		// Initialize stage
		this.canvas = new Canvas(Settings.sceneWidth.get(), Settings.sceneHeight.get());
		this.graphicsContext = canvas.getGraphicsContext2D();
		this.root = new StackPane(canvas);
		this.scene = new Scene(root);
		this.stage = stage;
		stage.setScene(scene);
		stage.show();
	}
	

	public void initialize(Renderer renderer) {
		// Setup window resize listener
		Settings.sceneWidth.bind(stage.widthProperty());
		Settings.sceneHeight.bind(stage.heightProperty());
		canvas.widthProperty().bind(Settings.sceneWidth);
		canvas.heightProperty().bind(Settings.sceneHeight);
		ChangeListener<Number> resizeListener = (observable, oldValue, newValue) ->
				renderer.getCamera().resetCamera();
		canvas.widthProperty().addListener(resizeListener);
		canvas.heightProperty().addListener(resizeListener);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}
	public StackPane getRoot() {
		return root;
	}
	public Scene getScene() {
		return scene;
	}
	public Stage getStage() {
		return stage;
	}
	
}
