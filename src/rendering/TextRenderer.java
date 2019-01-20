package rendering;

import java.util.ArrayList;
import java.util.List;

import events.TextEvent;
import javafx.scene.canvas.GraphicsContext;
import utils.Vector2D;

public class TextRenderer {
	// Rendering events
	List<TextEvent> activeTextEvents = new ArrayList<>();
	private Camera camera;
	private GraphicsContext gc;

	public TextRenderer(Camera camera, GraphicsContext gc) {
		this.camera = camera;
		this.gc = gc;
	}
	
	public void render() {
		renderText();
	}	
	
	/**
	 * Renders ship at new location.
	 */
	private void renderText() {
		for (TextEvent event : activeTextEvents) {
			gc.setFill(event.getColor());
			gc.setFont(event.getFont());
			
			Vector2D coords = null;
			switch (event.getDisplayType()) {
			case INIT_COORD:
				 coords = event.getCoords();
				break;
			case LOCK_COORD:
				coords = event.getCoords();
				break;
			case INIT_ENTITY:
				coords = event.getEntity().getPosition();
				break;
			case LOCK_ENTITY:
				coords = event.getEntity().getPosition();
				break;
			default:
				break;
			}
			Vector2D layoutCoords = camera.toLayoutCoords(coords);
			gc.fillText(event.getText(), layoutCoords.x, layoutCoords.y);
		}
	}
	
	
}
