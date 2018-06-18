package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import utils.Vector2D;

public class Settings {
	// Constants
	public static final double G = 9.81;
	
	// Scene details
	public static final DoubleProperty sceneWidth = new SimpleDoubleProperty(1024);
	public static final DoubleProperty sceneHeight = new SimpleDoubleProperty(768);
	public static final ObjectProperty<Color> sceneColor = new SimpleObjectProperty<>(Color.DARKGRAY);

	// Gravity
	
	public static final ObjectProperty<Vector2D> gravity = new SimpleObjectProperty<>(new Vector2D(0*G, 1*G));
	public static final DoubleProperty gravityX = new SimpleDoubleProperty(0 * G);
	public static final DoubleProperty gravityY = new SimpleDoubleProperty(1 * G);

	// Air resistance
	public static final DoubleProperty airCoeffLinear = new SimpleDoubleProperty(60);
	public static final DoubleProperty airCoeffQuadratic = new SimpleDoubleProperty(60);

	// Blocks
	public static final IntegerProperty chunkSize = new SimpleIntegerProperty(16);
	public static final DoubleProperty blockSize = new SimpleDoubleProperty(64);
	public static final DoubleProperty blocksX = new SimpleDoubleProperty(sceneWidth.get() / blockSize.get());
	public static final DoubleProperty blocksY = new SimpleDoubleProperty(sceneHeight.get() / blockSize.get());
	
	// Ship
	public static final DoubleProperty shipWitdh = new SimpleDoubleProperty(61);
	public static final DoubleProperty shipHeight = new SimpleDoubleProperty(34);
	public static final DoubleProperty timeScaleFactor = new SimpleDoubleProperty(1);
	public static final DoubleProperty engineScaleFactor = new SimpleDoubleProperty(2.5);
}
