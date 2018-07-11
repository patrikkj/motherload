package enums;

import javafx.scene.image.Image;

public enum Resource {
	//				Name			Loadable		Reference
	DEFAULT			("Default",		false,			),
	AIR				("Air",			true,			Material.AIR),
	GRASS			(),
	DIRT			(),
	ORE_EMERALD		(),
	ORE_RUBY		(),
	ORE_AMETHYST	(),
	ORE_DIAMOND		();

	
	private final Image image;
	
	
	private Resource() {
		this.image = null;
//		this.image = Loader.getImage(this);
	}
	
	public Image getImage() {
		return image;
	}
	
	
}