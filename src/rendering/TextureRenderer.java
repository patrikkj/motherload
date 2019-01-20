package rendering;

import java.util.Collection;

import application.Game;
import entities.Block;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import misc.Settings;
import terrain.Chunk;
import terrain.WorldManager;
import utils.Vector2D;

public class TextureRenderer {
	private WorldManager worldManager;
	private Camera camera;
	private GraphicsContext gc;

	public TextureRenderer(Camera camera, GraphicsContext gc) {
		this.worldManager = Game.getWorldManager();
		this.camera = camera;
		this.gc = gc;
	}
	
	public void render() {
		renderChunks();
		renderGrid();
		
	}	
	
	/**
	 * Renders all buffered chunks at new loction.
	 */
	private void renderChunks() {
		final Collection<Chunk> chunks = worldManager.getActiveChunks();
		for (Chunk chunk : chunks)
			for (Block block : chunk) {
				// Block position text
				Vector2D layoutCoords = camera.toLayoutCoords(block.getPosition());
				gc.setFill(Color.BLACK);
				gc.setFont(new javafx.scene.text.Font(10));
				gc.fillText(block.getPosition().toString(), layoutCoords.x, layoutCoords.y);
				gc.fillText(block.getMaterial().getName(), layoutCoords.x-15, layoutCoords.y-15);

				// Block sprite
				gc.setFill(Color.CADETBLUE);
				gc.fillRect(layoutCoords.x, layoutCoords.y, Settings.blockSize.get(), Settings.blockSize.get());
				
//				gc.fillOval(layoutCoords.x, layoutCoords.y, 3, 3);
//				gc.drawImage(block.getImage(), layoutCoords.x, layoutCoords.y);
//				gc.setFont(new javafx.scene.text.Font(18));
			}
	}
	

	/**
	 * Renders a grid, mainly used for debugging.
	 */
	private void renderGrid() {
		Vector2D layoutCamera = camera.toLayoutCoords(camera.getCoords());
		
		gc.setFill(Color.BLACK);
		gc.setFont(new javafx.scene.text.Font(10));
		gc.fillText("Cam: "+ camera.getCoords().toString2(), layoutCamera.x, layoutCamera.y - 15);
		
		gc.setFill(Color.AQUA);
		gc.fillOval(layoutCamera.x, layoutCamera.y, 3, 3);
		
		Vector2D screenMin = camera.getCoords().subtractNew(camera.getOffset());
		Vector2D screenMax = camera.getCoords().addNew(camera.getOffset());
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(0.3);
		
		for (int x = (int)screenMin.x; x <= (int)screenMax.x; x++) {
			Vector2D coord1 = camera.toLayoutCoords(new Vector2D(x, screenMin.y));
			Vector2D coord2 = camera.toLayoutCoords(new Vector2D(x, screenMax.y));
			gc.strokeLine(coord1.x, coord1.y, coord2.x, coord2.y);
		}
		
		for (int y = (int)screenMin.y; y <= (int)screenMax.y; y++) {
			Vector2D coord1 = camera.toLayoutCoords(new Vector2D(screenMin.x, y));
			Vector2D coord2 = camera.toLayoutCoords(new Vector2D(screenMax.x, y));
			gc.strokeLine(coord1.x, coord1.y, coord2.x, coord2.y);
		}
	}
}
