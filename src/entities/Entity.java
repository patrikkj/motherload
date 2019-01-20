package entities;

import javafx.scene.image.Image;
import misc.Constants;
import misc.Settings;
import utils.Rectangle2D;
import utils.Vector2D;

public abstract class Entity {
	private Vector2D position;
	private Rectangle2D bounds;
	private Sprite sprite;

	
	protected Entity(Vector2D position, double width, double height, Image image) {
		this.position = position;
		this.bounds = new Rectangle2D(position, width, height);
		this.sprite = new Sprite(image);
	}
	
	
	// Getters
	public Vector2D getPosition() {
		return position;
	}
	public Rectangle2D getBounds() {
		return bounds;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public Vector2D getCenter() {
//		return Vector2D.add(position, bounds.getDiagonal().divide(2));
		return Vector2D.add(position, Constants.blockOffset);
	}
	
	
	// Setters
	public void setPosition(Vector2D position) {
		this.position = position;
	}
	public void setBounds(Rectangle2D bounds) {
		this.bounds = bounds;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	
	// Intersection
	/**
	 * Returns wheter this entity intersects / overlaps given entity.
	 */
	public boolean intersects(Entity entity) {
		return intersects(this, entity);
	}
	
	/**
	 * Returns wheter given entities overlap / intersect.
	 */
	public static boolean intersects(Entity e1, Entity e2) {
		return Rectangle2D.intersects(e1.getBounds(), e2.getBounds());
	}

	/**
	 * Returns the absolute distance between two entities.
	 */
	public static double distanceBetween(Entity e1, Entity e2) {
		Vector2D pos1 = e1.getCenter();
		Vector2D pos2 = e2.getCenter();
		return pos2.subtract(pos1).magnitude();
	}


	@Override
	public String toString() {
		return "Entity [position=" + position + ", bounds=" + bounds + ", getCenter()=" + getCenter() + "]";
	}

}
