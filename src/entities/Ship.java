package entities;


import equipment.Engine;
import misc.Constants;
import misc.Settings;
import utils.Vector2D;

public class Ship extends DynamicEntity {
	private Engine engine;

	
	public Ship() {
		this(new Vector2D(0, -10));
		System.out.println("pos call 1 const");
	}
	
	public Ship(Vector2D position) {
		super(position, Settings.shipWidthGrid.get(), Settings.shipHeightGrid.get(), null, 200);
		System.out.println("pos call 2 const");
		equipDefault();
	}
	
	
	/**
	 * Equips ship with default equipment.
	 */
	private void equipDefault() {
		this.engine = Engine.Standard;
	}
	
	public Engine getEngine() {
		return engine;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
