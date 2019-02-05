package application;

import java.util.Comparator;

import entities.Block;
import entities.Entity;
import entities.Ship;
import enums.Material;
import misc.Settings;
import rendering.Renderer;
import utils.Collision;
import utils.Direction;
import utils.Rectangle2D;
import utils.Vector2D;

public class PhysicsEngine {
	private Ship ship;
	private Controls controls;
	private Renderer renderer;
	
	// Cached data from last collision event
	private Vector2D oldPos;
	private Rectangle2D oldBounds;
	
	
	public PhysicsEngine() {
		this.ship = Game.getShip();
		this.controls = Game.getControls();
		this.renderer = Game.getRenderer();
	}
	
	
	public void updateShip() {
		Direction direction = controls.getDirection();
		calculateForce(direction);
		
		double deltaTime = renderer.getDeltaTime();
		move(deltaTime);
	}
	
	// Methods
	/**
	 * Recalculates force vector based on gravitational field and engine power.
	 */
	public void calculateForce(Direction direction) {
		// Clear force vectors
		ship.resetForce();
		
		// Apply engine force
		ship.addForce(direction.getVector().multiplyNew(ship.getEngine().getPower()).limit(ship.getEngine().getPower()));		
		
		// Apply force from gravitational field
		ship.addForce(Settings.gravity.get().multiplyNew(ship.getMass()));

		// Apply force from air resistance
		ship.addForce(ship.getVelocity().multiplyNew(-1 * Settings.linearDrag.get()));
	}
	
	
	/**
	 * Called by render engine upon node movement. Recalculates acceleration, velocity and location vectors.
	 * @param deltaTime - time elapsed since last frame.
	 */
	public void move(double deltaTime) {
		// Cache ship parameters prior to collision test
		oldPos = ship.getPosition().copy();
		oldBounds = ship.getBounds().copy();

		// Update acceleration vector
		ship.getAcceleration().set(ship.getForce().x, ship.getForce().y).divide(ship.getMass());
		
		// Update velocity vector
		ship.getVelocity().add(ship.getAcceleration().multiplyNew(deltaTime));
		
		// Update position vector
		ship.getPosition().add(ship.getVelocity().multiplyNew(deltaTime));
		
		// Update collision box
		ship.getBounds().move(ship.getVelocity().multiplyNew(deltaTime));

		// Collision test
		handleCollisions(deltaTime);
	}
	
	
	// Collisions
	private void handleCollisions(double deltaTime) {
		// Colliding entity
		Block block = getCollidingBlock();
		
		// Handle collisions while there are colliding entities
		while (block != null) {
			Collision collision = createCollision(block, deltaTime);
			applyCollision(collision);
			block = getCollidingBlock();
		}
		
		
	}
	
	private Block getCollidingBlock() {
		// Comparator prioritizing entities that are closer to the ship
		Comparator<Block> incDistance = (b1, b2) -> (int) Math.signum(Entity.distanceBetween(ship, b1) - Entity.distanceBetween(ship, b2));
		
		// Colliding block, if any
		return Game.getWorld()
				.getBlocks(ship.getPosition(), Settings.collisionRadius.get())
				.stream()
				.filter(b -> b.getMaterial().isCollidable())
				.sorted(incDistance)
				.filter(b -> Entity.intersects(ship, b))
				.findFirst()
				.orElse(null);
	}
	
	private Collision createCollision(Block block, double deltaTime) {
//		System.out.println("Creating collision, Block = " + block.toString());
//		System.out.println("Creating collision, Ship = " + ship.toString());
		Material material =  block.getMaterial();
		Direction direction;
		
		// Settings
		double shipWidth = Settings.shipWidthGrid.get();
		double shipHeight = Settings.shipHeightGrid.get();
		double blockSize = 1;
		
		// Vector from block to ship
		Vector2D delta = ship.getPosition().subtractNew(block.getPosition());
		
		// Epsilon describing infinitesimally small area of intersection
		final double epsilonX = (delta.x < 0) ? (delta.x + shipWidth) : (blockSize - delta.x);
		final double epsilonY = (delta.y < 0) ? (delta.y + shipHeight) : (blockSize - delta.y);
		
		// Use sides of intersecting rectangle to determine orientation
		if (epsilonX >= epsilonY)
			direction = (delta.y < 0) ? Direction.N : Direction.S;
		else
			direction = (delta.x < 0) ? Direction.W : Direction.E;

		return new Collision(material, direction, deltaTime);
	}
	
	private void applyCollision(Collision collision) {
		// Undo current frame
		ship.getPosition().set(oldPos);
		ship.getBounds().set(oldBounds);
//			
//			// Cache ship parameters prior to collision test
		oldPos = ship.getPosition().copy();
		oldBounds = ship.getBounds().copy();
//			
		// Modify velocity vector, apply collision elasticity
//		System.out.println(collision);
		double elasticity = collision.getMaterial().getElasticity();
		switch (collision.getDirection()) {
		case N:
			ship.getVelocity().setY(Math.abs(ship.getVelocity().y) * -elasticity);
			break;
		case S:
			ship.getVelocity().setY(Math.abs(ship.getVelocity().y) * elasticity*0);
			break;
		case W:
			ship.getVelocity().setX(Math.abs(ship.getVelocity().x) * -elasticity*0);
			break;
		case E:
			ship.getVelocity().setX(Math.abs(ship.getVelocity().x) * elasticity*0);
			break;
		default:
		}
		
		// New velocity
		Vector2D velNew = ship.getVelocity().multiplyNew(collision.getDeltaTime());
		
		// Update position vector with new paramters
		ship.getPosition().add(velNew);
		
		// Update collision box with new paramters
		ship.getBounds().move(velNew);
	}
}
