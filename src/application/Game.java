package application;

import entities.Ship;
import enums.Material;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import utils.Vector2D;
import world.World;

public class Game implements Runnable {
	// Game instance
	private static Game game;
		
	// References
	private World world;
	private Controls controls;
	private RenderEngine renderEngine;
	
	// Entity references
	private Ship ship;

	// Rendering

	
	// Layout references
	private Scene scene;
	private StackPane root;
	private Canvas canvas;
	private GraphicsContext gc;
	
	
	
	
	// Constructor
	public Game(Scene scene, StackPane root, Canvas canvas) {
		Game.game = this;
		
		this.world = new World();
		this.controls = new Controls(); 
		
		this.scene = scene;
		this.root = root;
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
		
		this.ship = new Ship(new Vector2D(), 200, null);
		this.renderEngine = new RenderEngine();
		
		initialize();
	}
	
	private void initialize() {
		controls.setKeyListeners();
		
	}
	
	
	/**
	 * Returns the one instance of this class.
	 */
	public static Game get() {
		return game;
	}
	
	
	
	
	//// Game logic ////
	/**
	 * Main game loop.
	 */
	private void gameLoop() {
		// Updates
		renderEngine.updateFramerate();
		controls.updateUserInput();
		world.updateActiveChunks(ship.getPosition());
		
		if (controls.getActionKey() == KeyCode.CONTROL) {
			System.out.println("Control pressed");
			world.getBlocks(World.vectorToGlobalID(ship.getPosition().addNew(ship.getOffset())), Settings.digRadius.get()).forEach(b -> b.setBlock(Material.AIR));
		}
		// Entity movement
		ship.calculateForce(controls.getDirection());
		ship.move(renderEngine.getDeltaTime());
		
		// Rendering
		renderEngine.render(gc);
		
//		System.out.println(this);
//		System.out.println("Ship: " + ship.getPos());
//		System.out.println(ship.getBounds());
	}
	
	/**
	 * Initializes game and starts game loop.
	 */
	public void run() {
		Timeline timeline = new Timeline(200); // 200
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(8), e -> {	 // 1
			gameLoop(); 
		}));
		timeline.play();
	}

	
	

	
	
	
	//// Getters ////
	public Ship getShip() {
		return ship;
	}
	public Scene getScene() {
		return scene;
	}
	public StackPane getRoot() {
		return root;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public World getWorld() {
		return world;
	}
	public Controls getControls() {
		return controls;
	}
	public RenderEngine getRenderEngine() {
		return renderEngine;
	}

	
	//// Other ////
	

}
