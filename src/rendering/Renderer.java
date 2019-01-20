package rendering;

import application.Game;
import application.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.scene.canvas.GraphicsContext;
import misc.Settings;
import utils.Vector2D;

public class Renderer {
	// Game reference
	private Game game;
	
	// Submodules
	private TextRenderer textRenderer;
	private TextureRenderer textureRenderer;
	private EntityRenderer entityRenderer;
	private GUIRenderer GUIRenderer;
	
	// Framerate
	private double thisFrame = System.nanoTime();
	private double lastFrame; 
	private double deltaTime; 
	private double fps;
	
	// Camera management
	private Camera camera;
	private GraphicsContext gc;

	
	public Renderer(StageManager stageManager) {
		this.game = Game.get();
		
		this.gc = stageManager.getGraphicsContext();
		this.camera = new Camera(Game.getShip());
		
		this.textRenderer = new TextRenderer(camera, gc);
		this.textureRenderer = new TextureRenderer(camera, gc);
		this.entityRenderer = new EntityRenderer(camera, gc);
		this.GUIRenderer = new GUIRenderer(camera, gc);
		
		stageManager.initialize(this);
	}
	
	
	/**
	 * Renders all cached entities.
	 */
	public void render() {
		// Clear screen
		gc.setFill(Settings.sceneColor.get());
		gc.fillRect(0, 0, Settings.sceneWidth.get(), Settings.sceneHeight.get());
		
		// Render submodules
		textureRenderer.render();
		entityRenderer.render();
		
		camera.moveCamera(deltaTime);
	}
	
	
	/**
	 * Called at the start of every gameLoop, updating framerate.
	 */
	public void updateFramerate() {
		lastFrame = thisFrame;
		thisFrame = System.nanoTime();
		deltaTime = (thisFrame - lastFrame) / 1_000_000_000d;
		deltaTime *= Settings.timeScaleFactor.get();
		fps = 1_000_000_000d / (thisFrame - lastFrame);
	}
	
	public Camera getCamera() {
		return camera;
	}
	public double getDeltaTime() {
		return deltaTime;
	}
	public double getFps() {
		return fps;
	}
	
}
