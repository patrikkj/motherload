package entities;

import java.util.Comparator;

import application.Game;
import application.Settings;
import enums.Material;
import equipment.Engine;
import javafx.scene.image.Image;
import utils.Collision;
import utils.Direction;
import utils.Rectangle2D;
import utils.Vector2D;
import world.World;

public class Ship extends Entity {
	public static Vector2D offset = new Vector2D(Settings.shipWidthGrid.get() / 2d, Settings.shipHeightGrid.get() / 2d);

	
	// Ship constructor
	public Ship(Vector2D position, double mass, Image image) {
		super(position, offset, mass, Settings.shipWidthGrid.get(), Settings.shipHeightGrid.get(), image);
		equipDefault();
	}
	

	private Engine engine;

	// Cached data from last collision event
	private Vector2D oldPos;
	private Rectangle2D oldBounds;
	
	
	// Constructor
	
	
	// Getters & Setters
	public Vector2D getOffset() {
		return offset;
	}
	
	
	// Methods
	/**
	 * Recalculates force vector based on gravitational field and engine power.
	 */
	public void calculateForce(Direction dir) {
		// Clear force vector
		resetForce();

		// Apply engine force
		addForce(dir.getVector().multiplyNew(engine.getPower()).limit(engine.getPower()));		
		
		// Apply force from gravitational field
		addForce(Settings.gravity.get().multiplyNew(getMass()));
		
		// Apply force from air resistance
		addForce(getVelocity().multiplyNew(-1 * Settings.linearDrag.get()));
	}
	
	/**
	 * Called by render engine upon node movement. Recalculates acceleration, velocity and location vectors.
	 * @param deltaTime - time elapsed since last frame.
	 */
	public void move(double deltaTime) {
		// Cache ship parameters prior to collision test
		oldPos = getPosition().copy();
		oldBounds = getBounds().copy();

		// Update acceleration vector
		getAcceleration().set(getForce().x, getForce().y).divide(getMass());
		
		// Update velocity vector
		getVelocity().add(getAcceleration().multiplyNew(deltaTime));
		
		// Update position vector
		getPosition().add(getVelocity().multiplyNew(deltaTime));
		
		// Update collision box
		getBounds().move(getVelocity().multiplyNew(deltaTime));

		// Collision test
		handleCollisions(deltaTime);
	}
	
	
	// Collisions
	private void handleCollisions(double deltaTime) {
		// Colliding entity
		Block block = getCollidingBlock();
		
		// Handle collisions while there are colliding entities
		while (block != null) {
//			System.out.println(++collisionCount);
			Collision collision = createCollision(block, deltaTime);
			applyCollision(collision);
			block = getCollidingBlock();
		}
	}
	
	/**
	 * Returns 
	 * @return
	 */
	private Block getCollidingBlock() {
		// Comparator prioritizing entities that are closer to the ship
		Comparator<Block> incDistance = (b1, b2) -> (int) Math.signum(Entity.distanceBetween(this, b1) - Entity.distanceBetween(this, b2));
		
		// Colliding block, if any
		return World.get().getBlocks(getPosition(), Settings.collisionRadius.get())
				.stream()
				.sorted(incDistance)
				.filter(b -> Entity.intersects(this, b))
				.findFirst()
				.orElse(null);
	}
	
	private Collision createCollision(Block block, double deltaTime) {
		Material material =  block.getMaterial();
		Direction direction;
		
		// Settings
		double shipWidth = Settings.shipWidthGrid.get();
		double shipHeight = Settings.shipHeightGrid.get();
		double blockSize = 1;
		
		// Vector from block to ship
		Vector2D delta = getPosition().subtractNew(block.getPosition());
		
		// Epsilon describing infinitesimally small area of intersection
		final double epsilonX = (delta.x < 0) ? (delta.x + shipWidth) : (blockSize - delta.x);
		final double epsilonY = (delta.y < 0) ? (delta.y + shipHeight) : (blockSize - delta.y);
		
		// Use sides of intersecting rectangle to determine orientation
		if (epsilonX >= epsilonY)
			direction = (delta.y < 0) ? Direction.N : Direction.S;
		else
			direction = (delta.x < 0) ? Direction.W : Direction.E;

		System.out.println("New Collision!");
		System.out.println("Block: " + block.getGlobalID());
		System.out.println("Block BB: " + block.getBounds());
		System.out.println("Ship BB: " + getBounds());
		System.out.println("delta: " + delta);
		System.out.printf("epsilonX: %s %s%n", epsilonX, (delta.x < 0) ? "(1)" : "(2)");
		System.out.printf("epsilonY: %s %s%n", epsilonY, (delta.y < 0) ? "(1)" : "(2)");
		System.out.println(direction);

		return new Collision(material, direction, deltaTime);
	}
	
	private void applyCollision(Collision collision) {
		// Undo current frame
		getPosition().set(oldPos);
		getBounds().set(oldBounds);
//		
//		// Cache ship parameters prior to collision test
		oldPos = getPosition().copy();
		oldBounds = getBounds().copy();
//		
		// Modify velocity vector, apply collision elasticity
		double elasticity = collision.getMaterial().getElasticity();
		switch (collision.getDirection()) {
		case N:
			getVelocity().setY(Math.abs(getVelocity().y) * -elasticity);
			break;
		case S:
			getVelocity().setY(Math.abs(getVelocity().y) * elasticity*0);
			break;
		case W:
			getVelocity().setX(Math.abs(getVelocity().x) * -elasticity*0);
			break;
		case E:
			getVelocity().setX(Math.abs(getVelocity().x) * elasticity*0);
			break;
		default:
		}
		
		// New velocity
		Vector2D velNew = getVelocity().multiplyNew(collision.getDeltaTime());
		
		// Update position vector with new paramters
		getPosition().add(velNew);
		
		// Update collision box with new paramters
		getBounds().move(velNew);
	}
	
	
	/**
	 * Equips ship with default equipment.
	 */
	private void equipDefault() {
		this.engine = Engine.Standard;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" + String.format("Ship [engine=%s]", engine);
	}

}
