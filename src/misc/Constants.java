package misc;

import javafx.scene.text.Font;
import utils.Vector2D;

public class Constants {
	// fonts
	public static Font labelfont = new Font("Arial", 11);
	public static Font dfont = new Font("Arial", 14);
	public static Font barfont = new Font("Century", 16);
	public static Font font20 = new Font("Arial", 20);
	public static Font bardraw = new Font("Commerce SF", 15);
	public static Font narrowfont = new Font("Niagara Solid", 17);
	
	// Offset vectors
	public static Vector2D blockOffset = new Vector2D(0.5, 0.5);
	public static Vector2D shipOffset = new Vector2D(Settings.shipWidthGrid.get() / 2d, Settings.shipHeightGrid.get() / 2d);
	
}
