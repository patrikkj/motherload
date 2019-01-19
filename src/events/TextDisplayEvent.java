package events;

import entities.Entity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utils.Vector2D;

public abstract class TextDisplayEvent extends Event {
	
	private String text;
	private Font font;
	private Color color;
	private DisplayType displayType;
	private Vector2D coords;
	private Vector2D offset;
	private Entity entity;
	private long startTime;
	private long endTime;
	
	
	// Constructors
	public TextDisplayEvent(String text, Font font, Color color, DisplayType displayType, Vector2D coords, Vector2D offset) {
		this.text = text;
		this.font = font;
		this.color = color;
		this.displayType = displayType;
		this.coords = coords;
		this.offset = offset;
	}
	
	public TextDisplayEvent(String text, Font font, Color color, DisplayType displayType, Entity entity, Vector2D offset) {
		this.text = text;
		this.font = font;
		this.color = color;
		this.displayType = displayType;
		this.offset = offset;
		this.entity = entity;
	}
	
	
	// Getters
	public String getText() {
		return text;
	}
	public Font getFont() {
		return font;
	}
	public Color getColor() {
		return color;
	}
	public DisplayType getDisplayType() {
		return displayType;
	}
	public Vector2D getCoords() {
		return coords;
	}
	public Vector2D getOffset() {
		return offset;
	}
	public Entity getEntity() {
		return entity;
	}
	public long getStartTime() {
		return startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	
	
	
	// Start / End handling
	public abstract boolean startPredicate();
	
	public abstract boolean endPredicate();
	
	public void start() {
		startTime = System.nanoTime();
	}
	
	public void end() {
		startTime = System.nanoTime();
	}
}