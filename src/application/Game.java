package application;

import entities.Ship;
import enums.Material;
import events.EventManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import misc.Settings;
import rendering.Renderer;
import terrain.World;
import terrain.WorldManager;

public class Game implements Runnable {
	private static Game game;
	private static Ship ship;
	private static World world;
	private static WorldManager worldManager;
	private static EventManager eventManager;
	private static PhysicsEngine physicsEngine;
	private static Controls controls;
	private static Renderer renderer;
	private static StageManager stageManager;
	
	// Constructor
	public Game(Stage stage) {
		
		Game.game = this;
		
		ship = new Ship();
		world = new World();
		worldManager = new WorldManager(world);
		eventManager = new EventManager();
		stageManager = new StageManager(stage);
		renderer = new Renderer(stageManager);
		controls = new Controls();
		physicsEngine = new PhysicsEngine();
	}
	
	
	/**
	 * Main game loop.
	 */
	private void gameLoop() {
		// Updates
		renderer.updateFramerate();
		controls.updateUserInput();
		eventManager.checkEvents();
		
		if (controls.getActionKey() == KeyCode.CONTROL) {
			System.out.println("Control pressed");
			world.getBlocks(World.vectorToGlobalID(ship.getCenter()), Settings.digRadius.get()).forEach(b -> b.setMaterial(Material.AIR));
		}
		
		// World rendering
		worldManager.updateActiveChunks(ship.getPosition());
		
		// Entity movement
		physicsEngine.updateShip();

		// Rendering
		renderer.render();
	}
	
	/**
	 * Runs game loop.
	 */
	@Override
	public void run() {
		Timeline timeline = new Timeline(200); // 200
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(8), e -> {
			gameLoop(); 
		}));
		timeline.play();
	}

	
	public static Game get() {
		return game;
	}
	public static Game getGame() {
		return game;
	}
	public static Ship getShip() {
		return ship;
	}
	public static World getWorld() {
		return world;
	}
	public static WorldManager getWorldManager() {
		return worldManager;
	}
	public static EventManager getEventManager() {
		return eventManager;
	}
	public static PhysicsEngine getPhysicsEngine() {
		return physicsEngine;
	}
	public static Controls getControls() {
		return controls;
	}
	public static Renderer getRenderer() {
		return renderer;
	}
	public static StageManager getStageManager() {
		return stageManager;
	}

}
