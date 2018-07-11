package application;

import java.util.Collection;

import entities.Block;
import entities.Ship;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.Vector2D;
import world.Chunk;
import world.World;

public class RenderEngine {
	// Game references
	private Game game;
	private Ship ship;
	
	// Camera management
	private Vector2D camera;
	private Vector2D cameraOffset; 
	
	// Framerate
	private double lastFrame, thisFrame = System.nanoTime();
	private double deltaTime, fps;

	// RenderEngine instance
	private static RenderEngine renderEngine;
	
	
	public RenderEngine() {
		this.game = Game.get();
		this.ship = game.getShip();
		
		this.camera = ship.getPivot().copy();
		this.cameraOffset = new Vector2D(Settings.blocksX.get(), Settings.blocksY.get()).divide(2);
	}

	/**
	 * Returns the one instance of this class.
	 */
	public static RenderEngine get() {
		return renderEngine;
	}
	
	/**
	 * Renders all cached entities.
	 */
	public void render(GraphicsContext gc) {
		// Clear screen
		gc.setFill(Settings.sceneColor.get());
		gc.fillRect(0, 0, Settings.sceneWidth.get(), Settings.sceneHeight.get());
		// Debug rendering
		renderGrid(gc);
		// Render chunks
		renderChunks(gc);
		// Render ship
		renderShip(gc);
		// Move camera
		moveCamera();
	}
	
	/**
	 * Renders ship at new location.
	 */
	private void renderShip(GraphicsContext gc) {
		final Vector2D layoutCoords = toLayoutCoords(ship.getPosition());
		final Vector2D debugCoords = toLayoutCoords(ship.getPosition().addNew(new Vector2D(Settings.shipWidthGrid.get(), Settings.shipHeightGrid.get())));
//		gc.drawImage(game.ship.getImage(), layoutCoords.x, layoutCoords.y);
		gc.setFill(Color.BROWN);
		gc.fillRect(layoutCoords.x, layoutCoords.y, Settings.shipWidthPixels.get(), Settings.shipHeightPixels.get());
//		gc.setFill(Color.BLACK);
//		gc.fillOval(layoutCoords.x, layoutCoords.y, 3, 3);
//		gc.fillOval(layoutCoords.x, debugCoords.y - 3, 3, 3);
//		gc.fillOval(debugCoords.x - 3, debugCoords.y - 3, 3, 3);
//		gc.fillOval(debugCoords.x - 3, layoutCoords.y, 3, 3);
	}
	
	/**
	 * Renders all buffered chunks at new loction.
	 */
	private void renderChunks(GraphicsContext gc) {
		final Collection<Chunk> chunks = World.get().getActiveChunks();
		for (Chunk chunk : chunks)
			for (Block block : chunk) {
				final Vector2D layoutCoords = toLayoutCoords(block.getPosition());
//				gc.setFill(Color.CADETBLUE);
//				gc.fillRect(layoutCoords.x, layoutCoords.y, Settings.blockSize.get(), Settings.blockSize.get());
//				gc.setFill(Color.BLACK);
//				gc.fillOval(layoutCoords.x, layoutCoords.y, 3, 3);
				gc.drawImage(block.getImage(), layoutCoords.x, layoutCoords.y);
//				gc.setFill(Color.WHITE);
//				gc.setFont(new javafx.scene.text.Font(18));
//				gc.fillText(Integer.toString(block.getGlobalID()), layoutCoords.x, layoutCoords.y);
				
			}
	}
	
	/**
	 * Renders a grid, mainly used for debugging.
	 */
	private void renderGrid(GraphicsContext gc) {
		Vector2D layoutCamera = toLayoutCoords(camera);
		gc.setFill(Color.AQUA);
		gc.fillOval(layoutCamera.x, layoutCamera.y, 3, 3);
		
		Vector2D screenMin = camera.subtractNew(cameraOffset);
		Vector2D screenMax = camera.addNew(cameraOffset);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(0.3);
		
		for (int x = (int)screenMin.x; x <= (int)screenMax.x; x++) {
			Vector2D coord1 = toLayoutCoords(new Vector2D(x, screenMin.y));
			Vector2D coord2 = toLayoutCoords(new Vector2D(x, screenMax.y));
			gc.strokeLine(coord1.x, coord1.y, coord2.x, coord2.y);
		}
		
		for (int y = (int)screenMin.y; y <= (int)screenMax.y; y++) {
			Vector2D coord1 = toLayoutCoords(new Vector2D(screenMin.x, y));
			Vector2D coord2 = toLayoutCoords(new Vector2D(screenMax.x, y));
			gc.strokeLine(coord1.x, coord1.y, coord2.x, coord2.y);
		}
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
	 * Called at the start of every gameLoop, updating framerate.
	 */
	protected void updateFramerate() {
		lastFrame = thisFrame;
		thisFrame = System.nanoTime();
		deltaTime = (thisFrame - lastFrame) / 1_000_000_000d;
		deltaTime *= Settings.timeScaleFactor.get();
		fps = 1_000_000_000d / (thisFrame - lastFrame);
	}
	
	/**
	 * Gradually moves camera towards ship.
	 */
	private void moveCamera() {
		Vector2D offset = camera.subtractNew(ship.getPosition().addNew(ship.getOffset()));
		camera = camera.subtract(offset.multiply(Settings.cameraSpeed.get() * RenderEngine.get().getDeltaTime()));
//		camera.clamp(lBound, uBound);
	}
	
	/**
	 * Resets rendering components.
	 */
	protected void resetCamera() {
		this.camera = ship.getPosition().copy();
		this.cameraOffset = new Vector2D(Settings.blocksX.get(), Settings.blocksY.get()).divide(2);
	}
	
	public double getDeltaTime() {
		return deltaTime;
	}
	public double getFps() {
		return fps;
	}
	
	@Override
	public String toString() {
		return String.format("Renderer [game=%s, camera=%s, cameraOffset=%s]", game, camera, cameraOffset);
	}
	
}
