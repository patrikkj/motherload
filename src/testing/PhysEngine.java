package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.Vector2D;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class PhysEngine {
	public static final double GRATIVY = 10d;
	public static final double TERMINAL_VELOCITY= 100;
	
	private static final Map<Node, Timeline> rendered = new HashMap<>();
	
	private static final List<Node> background = new ArrayList<>();
	private static final List<Node> foreground = new ArrayList<>();
	private static final Node player = new ImageView();
	
	private static double framerate;

	private Vector2D vec;
}
