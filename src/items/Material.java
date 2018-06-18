package items;

import graphics.Loader;
import javafx.scene.image.Image;

public enum Material {
	GRASS		(true, 80, 0.6, "Grass", "grass.png"),
	DIRT		(true, 80, 0.6, "Dirt", "dirt.png"),
	DIRT_BRIGHT (true, 80, 0.4, "Dirt (Bright)", "dirt_bright.png"),
	DIRT_DARK	(true, 80, 0.4, "Dirt (Dark)", "dirt_dark.png"),
	GRAVEL		(true, 40, 0.3, "Gravel", "gravel.png"),
	LAVA		(false, 1000, 0, "Lava", "lava.png"),
	MAGMA		(true, 600, 0.2, "Magma", "magma.png"),
	ORE_1		(true, 240, 0.4, "Ore 1", "ore_1.png"),
	ORE_2		(true, 260, 0.4, "Ore 2", "ore_2.png"),
	ORE_3		(true, 280, 0.4, "Ore 3", "ore_3.png"),
	ORE_4		(true, 300, 0.4, "Ore 4", "ore_4.png"),
	ORE_5		(true, 320, 0.4, "Ore 5", "ore_5.png"),
	ORE_6		(true, 340, 0.4, "Ore 6", "ore_6.png");
	
	private final boolean drillable;
	private final int hardness;
	private final double friction;
	private final String name;
	private final Image image;
	
	private Material() {
		this(true, 0, 0, "Not set", "Not set");
	}
	
	private Material(boolean drillable, int hardness, double friction, String name, String filename) {
		this.drillable = drillable;
		this.hardness = hardness;
		this.friction = friction;
		this.name = name;
		this.image = Loader.images.get(filename);
	}

	public boolean isDrillable() {
		return drillable;
	}

	public int getHardness() {
		return hardness;
	}

	public double getFriction() {
		return friction;
	}

	public String getName() {
		return name;
	}

	public Image getImage() {
		return image;
	}
	
	
}