package application;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.Ship;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import map.Chunk;
import utils.Direction;

public class Game {
	private Renderer renderer;
	protected Scene scene;
	protected Pane root;
	protected Ship ship;
	protected Direction direction;
	public static boolean UP, DOWN, LEFT, RIGHT, CONTROL, SHIFT;
	protected List<Entity> entities = new ArrayList<>();
	protected List<Chunk> chunks = new ArrayList<>();
	
	
	// Fields for estimating framerate
	private double thisFrame = System.nanoTime();
	private double lastFrame;
	protected double deltaTime;
	@Override
	public String toString() {
		return String.format("Game [deltaTime=%s, fps=%s]", deltaTime, fps);
	}

	protected double fps;

	
	public Game(Scene scene, Pane root) {
		this.scene = scene;
		this.root = root;
	}
	

	/**
	 * Initializes game.
	 */
	private void initialize() {
		ship = new Ship();
		entities.add(ship);
		
		chunks.add(new Chunk(0, 0));
		renderer = new Renderer(this);
		setKeyListeners();
		
		root.getChildren().addAll(chunks);
		root.getChildren().addAll(entities);
		
		
	}
	
	/**
	 * Main game loop.
	 */
	private void gameLoop() {
		// Updates
		updateFramerate();
		updateDirection();
		
		// Entity movement
		ship.applyForces(direction);
		entities.forEach(e -> e.move(deltaTime));
		
		// Rendering
		renderer.render();
		if (chunks.stream().flatMap(c -> c.getBlocks().stream()).anyMatch(e -> ship.intersects(e)))
			ship.getVelocity().set(0, 0);
//		System.out.println();
		System.out.println(this);
	}

	
	/////////////////////
	//// Game launch ////
	/////////////////////
	/**
	 * Initializes game and starts game loop.
	 */
	public void run() {
		initialize();
		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				long start = System.nanoTime();
				gameLoop();
				System.out.println(1_000_000_000d / (System.nanoTime() - start));
//				long start2 = System.nanoTime();
//				gameLoop();
//				System.out.println(1_000_000_000d / (System.nanoTime() - start2));
//				long start3 = System.nanoTime();
//				gameLoop();
//				System.out.println(1_000_000_000d / (System.nanoTime() - start3));
			}
		};
		animationTimer.start();
	}
	
	
	

	
	////////////////////
	//// User input ////
	////////////////////
	
	/**
	 * Assigns key listners.
	 */
	private void setKeyListeners() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				switch (key) {
				case UP: UP = true; break;
				case DOWN: DOWN = true; break;
				case LEFT: LEFT = true; break;
				case RIGHT: RIGHT = true; break;
				case CONTROL: CONTROL = true; break;
				case SHIFT: SHIFT = true; break;
				default:
					System.out.println("Key not supported: " + key.getName());
					return;
				}
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				switch (key) {
				case UP: UP = false; break;
				case DOWN: DOWN = false; break;
				case LEFT: LEFT = false; break;
				case RIGHT: RIGHT = false; break;
				case CONTROL: CONTROL = false; break;
				case SHIFT: SHIFT = false; break;
				default:
					System.out.println("Key not supported: " + key.getName());
					return;
				}
			}
		});
	}
	
	/**
	 * Checks user input and updates direction
	 */
	private void updateDirection() {
		int dir = 0;
		
		dir += (UP) ? dir + 1 : dir;
		dir += (DOWN) ? dir + 1 : dir;
		dir += (RIGHT) ? dir + 1 : dir;
		dir += (LEFT) ? dir + 1 : dir;
		
		switch (dir) {
		case 10: direction = Direction.NE; break;
		case 9: direction = Direction.NW; break;
		case 8: direction = Direction.N; break;
		case 6: direction = Direction.SE; break;
		case 5: direction = Direction.SW; break;
		case 4: direction = Direction.S; break;
		case 2: direction = Direction.E; break;
		case 1: direction = Direction.W; break;
		case 0: direction = Direction.NONE; break;
		}
	}
	
	
	/**
	 * Called at the start of every gameLoop, updating framerate.
	 */
	private void updateFramerate() {
		lastFrame = thisFrame;
		thisFrame = System.nanoTime();
		deltaTime = (thisFrame - lastFrame) / 1_000_000_000d;
//		deltaTime *= Settings.timeScaleFactor.get();
		fps = 1_000_000_000d / (thisFrame - lastFrame);
	}
	
	
	
	public Ship getShip() {
		return ship;
	}
}
