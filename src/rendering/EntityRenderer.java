package rendering;

import application.Game;
import entities.Ship;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import misc.Settings;
import utils.Vector2D;

public class EntityRenderer {
	private Ship ship;
	private Camera camera;
	private GraphicsContext gc;
	
	
	public EntityRenderer(Camera camera, GraphicsContext gc) {
		this.ship = Game.getShip();
		this.camera = camera;
		this.gc = gc;
	}
	
	public void render() {
		renderShip();
	}
	
	/**
	 * Renders ship at new location.
	 */
	private void renderShip() {
		// Ship position text
		Vector2D layoutCoords = camera.toLayoutCoords(ship.getPosition());
		gc.setFill(Color.BLACK);
		gc.setFont(new javafx.scene.text.Font(10));
		gc.fillText("Ship: " + ship.getCenter().toString2(), layoutCoords.x, layoutCoords.y);
		
		Vector2D lCoords = camera.toLayoutCoords(ship.getPosition());
		
		gc.setFill(Color.BROWN);
		gc.fillRect(lCoords.x, lCoords.y, Settings.shipWidthPixels.get(), Settings.shipHeightPixels.get());
		gc.fillText("Ship", lCoords.x, lCoords.y);
		
//		final Vector2D layoutCoords = camera.toLayoutCoords(ship.getCenter());
////		gc.drawImage(game.ship.getImage(), layoutCoords.x, layoutCoords.y);
//		gc.setFill(Color.BROWN);
//		gc.fillRect(layoutCoords.x, layoutCoords.y, Settings.shipWidthPixels.get(), Settings.shipHeightPixels.get());
	}
}
