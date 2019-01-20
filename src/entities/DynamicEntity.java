package entities;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import utils.Vector2D;

public class DynamicEntity extends Entity {
	private Vector2D velocity = new Vector2D();
	private Vector2D acceleration = new Vector2D();
	private List<Vector2D> forces = new ArrayList<>();
	private double mass;
	
	
	public DynamicEntity(Vector2D position, double width, double height, Image image) {
		super(position, width, height, image);
	}
	
	public DynamicEntity(Vector2D position, double width, double height, Image image, double mass) {
		super(position, width, height, image);
		this.mass = mass;
	}
	
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public Vector2D getAcceleration() {
		return acceleration;
	}
	
	public double getMass() {
		return mass;
	}
	
	public List<Vector2D> getForces() {
		return forces;
	}
	
	public Vector2D getForce() {
		return Vector2D.combine(forces);
	}

	public void addForce(Vector2D force) {
		forces.add(force);
	}
	
	
	public void resetForce() {
		forces.clear();
	}
}
