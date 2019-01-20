package entities;

import javafx.scene.image.Image;

public class Sprite {
	private Image image;
	private double width;
	private double height;
	private boolean flipX;
	private boolean flipY;
	
	public Sprite(Image image) {
		this.image = image;
		this.flipX = false;
		this.flipY = false;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getWidth() {
		return image.getWidth();
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return image.getHeight();
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isFlipX() {
		return flipX;
	}

	public void setFlipX(boolean flipX) {
		this.flipX = flipX;
	}

	public boolean isFlipY() {
		return flipY;
	}

	public void setFlipY(boolean flipY) {
		this.flipY = flipY;
	}
	
}
