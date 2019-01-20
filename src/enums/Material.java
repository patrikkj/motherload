package enums;

public enum Material {
	//				Drillable	Collidable	Hardness	Friction	Elasticity	Name
//	NONE			(false,		false,		0, 			0, 			0, 			"None"),
	AIR				(false,		false,		0, 			0, 			0, 			"Air"),
	GRASS			(true, 		true, 		80, 		0.6,		0.5, 		"Grass"),
	DIRT			(true, 		true, 		80, 		0.6,		0.5, 		"Dirt"),
	ORE_EMERALD		(true,  	true,		280, 		0.4, 		0.1, 		"Emerald"),
	ORE_RUBY		(true,  	true,		300, 		0.4, 		0.1, 		"Ruby"),
	ORE_AMETHYST	(true,  	true,		320, 		0.4, 		0.1, 		"Amethyst"),
	ORE_DIAMOND		(true,  	true,		340, 		0.4, 		0.1, 		"Diamond"),
	DEFAULT			(false,		true,		0,			0,			1,			"Default");
	
	private final boolean drillable;
	private final boolean collidable;
	private final int hardness;
	private final double friction;
	private final double elasticity;
	private final String name;
	private final Resource resource;
	
	
	private Material(boolean drillable, boolean collidable, int hardness, double friction, 
			double elasticity, String name) {
		this.drillable = drillable;
		this.collidable = collidable;
		this.hardness = hardness;
		this.friction = friction;
		this.elasticity = elasticity;
		this.name = name;
		this.resource = Resource.valueOf(this.name());
	}

	
	public boolean isDrillable() {
		return drillable;
	}
	
	public boolean isCollidable() {
		return collidable;
	}
	
	public int getHardness() {
		return hardness;
	}

	public double getFriction() {
		return friction;
	}

	public double getElasticity() {
		return elasticity;
	}
	
	public String getName() {
		return name;
	}
	
	public Resource getResource() {
		return resource;
	}
	
}