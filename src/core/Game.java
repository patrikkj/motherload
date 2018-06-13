package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Game {
	private Scene scene;
	private Pane root;
	private Rectangle r1;
	private Ship ship;
	private static final double GRAVITY = 10;
	public static Map<String, Image> imageMap;
	private long nanoTime = System.nanoTime();
	private double frameTime;
	private double width, height;
	public double fps;
	public static boolean UP, DOWN, LEFT, RIGHT, CONTROL, SHIFT;
	

	
	public Game(Scene scene, Pane root) {
		this.scene = scene;
		this.root = root;
		width = scene.getWidth();
		height = scene.getHeight();
	}
	
	
	/**
	 * Main game loop.
	 */
	private void gameLoop() {
		updateFramerate();
		move();
	}

	/**
	 * Moves player.
	 */
	private void move() {
		double speed = 5;
		
		double dx = 0;
		double dy = 0;
		
		if (UP == true) dy -= speed;
		if (DOWN == true) dy += speed;
		if (LEFT == true) dx -= speed;
		if (RIGHT == true) dx += speed;
		
		dy += 3.8;
		
		ship.setLayoutX(ship.getLayoutX() + dx);
		ship.setLayoutY(ship.getLayoutY() + dy);
		
		
	}
	
	/////////////////////
	//// Game launch ////
	/////////////////////
	
	private void initializeImageMap() {
		// Initialize
		imageMap = new HashMap<>();
		
    	File importFolder = new File(getClass().getResource("../graphics/ship").getPath());
    	FilenameFilter fileFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".png");
			}
		};
		
    	for (File imageFile : importFolder.listFiles(fileFilter))
			try {
				imageMap.put(imageFile.getName(), new Image(new FileInputStream(imageFile)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	/**
	 * Initializes game and starts game loop.
	 */
	public void run() {
		initialize();
		
		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gameLoop();
			}
		};
		animationTimer.start();
	}
	
	/**
	 * Initializes game.
	 */
	private void initialize() {
		initializeImageMap();
		ship = new Ship();
		root.getChildren().setAll(ship);
		setKeyListeners();
	}
	
	/**
	 * Called at the start of every gameLoop, updating framerate.
	 */
	private void updateFramerate() {
		long oldNanoTime = nanoTime;
		nanoTime = System.nanoTime();
		frameTime = (nanoTime - oldNanoTime) / 1_000_000_000d;
		fps = 1_000_000_000d / (nanoTime - oldNanoTime);
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
				case UP:
					UP = true;
					break;
				case DOWN:
					DOWN = true;
					break;
				case LEFT:
					LEFT = true;
					break;
				case RIGHT:
					RIGHT = true;
					break;
				case CONTROL:
					CONTROL = true;
					break;
				case SHIFT:
					SHIFT = true;
					break;
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
				case UP:
					UP = false;
					break;
				case DOWN:
					DOWN = false;
					break;
				case LEFT:
					LEFT = false;
					break;
				case RIGHT:
					RIGHT = false;
					break;
				case CONTROL:
					CONTROL = false;
					break;
				case SHIFT:
					SHIFT = false;
					break;
				default:
					System.out.println("Key not supported: " + key.getName());
					return;
				}
			}
		});
	}
	
	
}
