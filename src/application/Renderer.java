package application;

import entities.Entity;
import entities.Ship;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import utils.Vector2D;

public class Renderer {
	private final Game game;
	
	// Camera management
	private Vector2D camera; // [16, 16]
	private Vector2D cameraOffset;  // [8, 6]
	
	
	public Renderer(Game game) {
		this.game = game;
		this.camera = new Vector2D(8, 6);
		this.cameraOffset = new Vector2D(Settings.blocksX.get(), Settings.blocksY.get()).divide(2); // [16, 12]s
	}

	
	/**
	 * Renders all cached entities.
	 */
	public void render() {
//		renderAnimated(game.ship);
		game.entities.forEach(e -> renderAnimated(e));
		game.chunks.forEach(c -> c.forEach(e -> render(e)));
		moveCamera();
//		System.out.printf("Layout coords: [%s, %s]%n", game.ship.getLayoutX(), game.ship.getLayoutY());
//		System.out.printf("Translate coords: [%s, %s]%n", game.ship.getTranslateX(), game.ship.getTranslateY());
	}
	
	/**
	 * Updates visual representation of given entity.
	 */
	public void render(Entity entity) {
		Vector2D coords = toLayoutCoords(entity.getPosition());
		entity.relocate(coords.x, coords.y);
	}
	
	/**
	 * Updates visual representation of given entity, animated.
	 */
	public void renderAnimated(Entity entity) {
		Vector2D coords = toLayoutCoords(entity.getPosition());
//		entity.relocate(coords.x, coords.y);
//		Vector2D oldCoords = new Vector2D(entity.getLayoutX(), entity.getLayoutY());
//		Vector2D offset = coords.subtractNew(oldCoords);
//		
		// Transition properties
		TranslateTransition translateTransition = new TranslateTransition();
		translateTransition.setNode(entity);
		translateTransition.setToX(coords.x);
		translateTransition.setToY(coords.y);
		translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				entity.relocate(coords.x, coords.y);
			}
		});
		translateTransition.setInterpolator(Interpolator.LINEAR);
		translateTransition.setDuration(Duration.millis(game.deltaTime*4000));
//		translateTransition.setRate(game.fps*0.1);
		translateTransition.play();
	}

	/**
	 * Converts world coordinates to screen pixel coordinates.
	 */
	private Vector2D toLayoutCoords(Vector2D pos) {
		Vector2D screenOrigo = camera.subtractNew(cameraOffset);
		Vector2D coords = pos.subtractNew(screenOrigo);
		return coords.multiply(Settings.blockSize.get());
	}
	
	/**
	 * Gradually moves camera towards ship.
	 */
	private void moveCamera() {
		Ship ship = game.ship;
//		Vector2D offset = game.ship.getVelocity().copy();
		Vector2D offset = camera.subtractNew(game.ship.getPosition());
		camera = camera.subtract(offset.multiply(0.02));
		
				
				// Play Transition

	}
	
	@Override
	public String toString() {
		return String.format("Renderer [game=%s, camera=%s, cameraOffset=%s]", game, camera, cameraOffset);
	}
	
}
