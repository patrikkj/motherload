package enums;

import javafx.scene.paint.Color;


public enum Material {
	//				Drillable	Collidable	Hardness	Friction	Elasticity	Name
//	NONE			(false,		false,		0, 			0, 			0, 			"None"),
	AIR				(false,		false,		0, 			0, 			0, 			"Air", 		Color.TRANSPARENT),
	GRASS			(true, 		true, 		80, 		0.6,		0.5, 		"Grass", 	Color.DARKGREEN),
	DIRT			(true, 		true, 		80, 		0.6,		0.5, 		"Dirt",		Color.BURLYWOOD),
	ORE_EMERALD		(true,  	true,		280, 		0.4, 		0.1, 		"Emerald",	Color.GREEN),
	ORE_RUBY		(true,  	true,		300, 		0.4, 		0.1, 		"Ruby",		Color.RED),
	ORE_AMETHYST	(true,  	true,		320, 		0.4, 		0.1, 		"Amethyst", Color.BLUEVIOLET),
	ORE_DIAMOND		(true,  	true,		340, 		0.4, 		0.1, 		"Diamond",	Color.CORNFLOWERBLUE),
	DEFAULT			(false,		true,		0,			0,			1,			"Default",	Color.BLACK);
	
	private final boolean drillable;
	private final boolean collidable;
	private final int hardness;
	private final double friction;
	private final double elasticity;
	private final String name;
	private final Color color;
	
	
	private Material(boolean drillable, boolean collidable, int hardness, double friction, 
			double elasticity, String name, Color color) {
		this.drillable = drillable;
		this.collidable = collidable;
		this.hardness = hardness;
		this.friction = friction;
		this.elasticity = elasticity;
		this.name = name;
		this.color = color;
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
	
	public Color getColor() {
		return color;
	}
}