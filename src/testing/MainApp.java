package testing;

import java.io.IOException;

import core.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainApp extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Circle circle = new Circle(300, 200, 20);
		AnchorPane root = new AnchorPane(circle);
		Scene scene = new Scene(root, 600, 400);
		
		root.setPrefSize(600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		root.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				// Get key pressed
				KeyCode key = event.getCode();
				
				// Perform action based on pressed key
				switch (key) {
				case UP:
					circle.setTranslateY(circle.getTranslateY() - 5);
					break;
				case DOWN:
					circle.setTranslateY(circle.getTranslateY() + 5);
					break;
				case LEFT:
					circle.setTranslateX(circle.getTranslateX() - 5);
					break;
				case RIGHT:
					circle.setTranslateX(circle.getTranslateX() + 5);
					break;
				default:
					System.out.println("Key not supported: " + key);
					return;
				}
			}
		});
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
