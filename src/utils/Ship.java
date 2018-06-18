

import application.Settings;
import graphics.Loader;
import items.Engine;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import utils.Direction;
import utils.Vector2D;

public class Ship extends Region {
	private Vector2D location;
	private Vector2D velocity;
	private Vector2D acceleration;
	
	private Vector2D force = new Vector2D();
	
	private ImageView imageView;
	
	private double mass;
	private double width, height;
	private double offsetX, offsetY;
	private double angle;
	
	private Engine engine;
	
	public Ship() {
		this(new Vector2D(16, 12), new Vector2D(), new Vector2D(), 175, 32, 32);
	}
	
	public Ship(Vector2D location, Vector2D velocity, Vector2D acceleration, double mass, double width, double height) {
		this.location = location;
		this.velocity = velocity;
		this.acceleration = acceleration;
		
		this.mass = mass;
		
		this.width = width;
		this.height = height;

		this.offsetX = width / 2;
		this.offsetY = height / 2;
		
		this.engine = Engine.Standard;
		
		setPrefSize(width, height);
		imageView = new ImageView(Loader.images.get("ship.png"));
		
		getChildren().setAll(imageView);
	}
	
	/**
	 * Recalculates force vector based on gravitational field and engine power.
	 */
	public void applyForces(Direction dir) {
		// Reset force and apply engine force
		force.set(dir.getVector());
		force.multiply(engine.getPower() * Settings.engineScaleFactor.get());

		// Apply gravitational force
		force.add(Settings.gravityX.get() * mass, Settings.gravityY.get() * mass);
		
		// Apply air resistance
		Vector2D airResistance = new Vector2D(velocity);
		airResistance.multiply(-1 * Settings.airCoeff.get());
		force.add(airResistance);
	}
	
	/**
	 * Called by render engine upon node movement. Recalculates acceleration, velocity and location vectors.
	 * @param deltaTime - time elapsed since last frame.
	 */
	public void move(double deltaTime) {
		deltaTime *= Settings.timeScaleFactor.get();
		
		// Update acceleration vector
		acceleration.set(force.x, force.y);
		acceleration.divide(mass);
		
		// Update velocity vector
		velocity.add(Vector2D.multiply(acceleration, deltaTime));
		
		// Update location vector
		location.add(velocity.x * deltaTime, velocity.y * deltaTime);
	}
	
	/**
	 * Renders ship at new location.
	 */
	public void render(double deltaTime) {
		move(deltaTime);
		
		setLayoutX(location.x * Settings.blockSize.get());
		setLayoutY(location.y * Settings.blockSize.get());
	}

	
	@Override
	public String toString() {
		return String.format(
				"Ship [location=%s, velocity=%s, acceleration=%s, force=%s, mass=%s, width=%s, height=%s, offsetX=%s, offsetY=%s, angle=%s, engine=%s]",
				location, velocity, acceleration, force, mass, width, height, offsetX, offsetY, angle, engine);
	}
	
	
}
