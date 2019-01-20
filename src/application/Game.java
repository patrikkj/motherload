package application;

import entities.Ship;
import events.EventManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
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
		controls.setKeyListeners();

//		System.out.println("1renderer" + renderer.toString());
//		System.out.println("1controls" + controls.toString());
//		System.out.println("1eventManager" + eventManager.toString());
//		System.out.println("1worldManager" + worldManager.toString());
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	/**
	 * Main game loop.
	 */
	private void gameLoop() {
		// Updates
		renderer.updateFramerate();
		controls.updateUserInput();
		eventManager.checkEvents();
		
		// World rendering
		worldManager.updateActiveChunks(ship.getPosition());
		
//		System.out.println("2renderer" + renderer.toString());
//		System.out.println("2controls" + controls.toString());
//		System.out.println("2eventManager" + eventManager.toString());
//		System.out.println("2worldManager" + worldManager.toString());
//		System.out.println("2physicsEngine" + physicsEngine.toString());
		// Entity movement
		physicsEngine.updateShip();
//		System.out.println(ship.toString());
		// Rendering
		renderer.render();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	/**
	 * Runs game loop.
	 */
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
