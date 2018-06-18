package entities;

import application.Settings;
import javafx.scene.image.ImageView;
import utils.Direction;
import utils.Vector2D;

public abstract class Entity extends ImageView {
	protected Vector2D force = new Vector2D();
	protected Vector2D pos, vel, acc;
	protected double mass;
	private double width, height;

	protected Entity(Vector2D pos, Vector2D vel, Vector2D acc, double mass, double width, double height) {
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
		this.mass = mass;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Called by render engine upon node movement. Recalculates acceleration, velocity and location vectors.
	 * @param deltaTime - time elapsed since last frame.
	 */
	public void move(double deltaTime) {
		// Update acceleration vector
		acc.set(force.x, force.y).divide(mass);
		
		// Update velocity vector
		vel.add(acc.multiplyNew(deltaTime));
		
		// Update location vector
		pos.add(vel.multiplyNew(deltaTime));
	}

	/**
	 * Applies force caused by gravitational field.
	 */
	protected void applyGravity() {
		force.add(Settings.gravity.get().multiplyNew(mass));
	}
	
	/**
	 * Applies force caused by air resistance.
	 */
	protected void applyAirResistance() {
		Vector2D airResistance = new Vector2D(vel);
		
		if (vel.magnitude() < (Settings.airCoeffLinear.get() / Settings.airCoeffQuadratic.get()))
			airResistance.multiply(-1 * Settings.airCoeffLinear.get());
		else
			airResistance.square().multiply(-1 * Settings.airCoeffQuadratic.get());
		
		force.add(airResistance);
	}
	
	/**
	 * Returns wether this entity intersects with input entity.
	 */
	public boolean intersects(Entity entity) {
		Vector2D pos1 = entity.getPosition();
		return ((Math.abs(pos1.x - pos.x) < (this.width + entity.width) / (2d * Settings.blockSize.get())) &&
				Math.abs(pos1.y - pos.y) < (this.height + entity.height) / (2d * Settings.blockSize.get()));
	}
	
	public Vector2D getPosition() {
		return pos;
	}
	public Vector2D getVelocity() {
		return vel;
	}
	public Vector2D getAcceleration() {
		return acc;
	}
	






	@Override
	public String toString() {
		return String.format("Entity [force=%s, pos=%s, vel=%s, acc=%s, mass=%s]", force, pos, vel, acc, mass);
	}
}
