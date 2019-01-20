package equipment;

import misc.Settings;

public enum Engine {
	Standard(5000);
	
	private final double power;
	
	Engine(double power) {
		this.power = power;
	}
	
	public double getPower() {
		return power * Settings.engineScaleFactor.get();
	}
}
