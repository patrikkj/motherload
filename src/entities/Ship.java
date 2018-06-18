package entities;

import application.Settings;
import graphics.Loader;
import items.Engine;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import utils.Direction;
import utils.Vector2D;

public class Ship extends Entity {
	private Engine engine;
	
	
	public Ship() {
		this(new Vector2D(0, 0), new Vector2D(), new Vector2D(), 175);
	}
	
	public Ship(Vector2D pos, Vector2D vel, Vector2D acc, double mass) {
		super(pos, vel, acc, mass, 61, 34);
		this.engine = Engine.Standard;
		
		setImage(Loader.images.get("ship.png"));
	}
	
	/**
	 * Recalculates force vector based on gravitational field and engine power.
	 */
	public void applyForces(Direction dir) {
		// Clear force vector
		force.set(0, 0);

		// Apply forces
		applyEngine(dir);
		applyGravity();
		applyAirResistance();
	}
	
	public void applyEngine(Direction dir) {
		force.add(dir.getVector().multiplyNew(engine.getPower() * Settings.engineScaleFactor.get()));
	}
	
	
	/**
	 * Renders ship at new location.
	 */
	public void render(double deltaTime) {
		move(deltaTime);
	}

	
	@Override
	public String toString() {
		return super.toString() + "\n" + String.format("Ship [engine=%s]", engine);
	}
	
	
}
