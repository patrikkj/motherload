package items;

public enum Engine {
	Standard(200);
	
	public final int FORCE;
	
	Engine(int FORCE) {
		this.FORCE = FORCE;
	}
}
