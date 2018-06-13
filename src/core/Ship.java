package core;

import items.Engine;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class Ship extends Region {
	private Vector2D location;
	private Vector2D velocity;
	private Vector2D acceleration;
	
	private ImageView imageView;
	
	private double maxVelocity;
	private double width, height;
	private double offsetX, offsetY;
	private double angle;
	
	private Engine engine;
	
	public Ship() {
		this(new Vector2D(), new Vector2D(), new Vector2D(), 32, 32);
	}
	
	public Ship(Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
		this.location = location;
		this.velocity = velocity;
		this.acceleration = acceleration;
		
		this.width = width;
		this.height = height;

		this.offsetX = width / 2;
		this.offsetY = height / 2;
		
		setPrefSize(width, height);
		
		imageView = new ImageView(Game.imageMap.get("ship.png"));
		
		getChildren().setAll(imageView);
	}
	
}
