package rendering;

import entities.Ship;
import misc.Settings;
import utils.Vector2D;

public class Camera {
	private Ship ship;
	private Vector2D coords;
	private Vector2D offset; 
	
	
	public Camera(Ship ship) {
		this.ship = ship;
		this.coords = ship.getPosition().copy();
		this.offset = new Vector2D(Settings.blocksX.get(), Settings.blocksY.get()).divide(2);
	}
	
	
	/**
	 * Gradually moves camera towards ship.
	 */
	public void moveCamera(double deltaTime) {
		Vector2D offset = coords.subtractNew(ship.getPosition());
		coords = coords.subtract(offset.multiply(Settings.cameraSpeed.get() * deltaTime));
//		camera.clamp(lBound, uBound);
	}
	
	/**
	 * Resets rendering components.
	 */
	public void resetCamera() {
		this.coords = ship.getPosition().copy();
		this.offset = new Vector2D(Settings.blocksX.get(), Settings.blocksY.get()).divide(2);
	}
	
	/**
	 * Converts world coordinates to screen pixel coordinates.
	 */
	public Vector2D toLayoutCoords(Vector2D pos) {
		Vector2D screenOrigo = coords.subtractNew(offset);
		Vector2D coords = pos.subtractNew(screenOrigo);
		return coords.multiply(Settings.blockSize.get());
	}
	
	public Vector2D getCoords() {
		return coords;
	}
	public Vector2D getOffset() {
		return offset;
	}
	
}
