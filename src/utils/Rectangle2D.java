package utils;

public class Rectangle2D {
	private Vector2D position; 
	private double width, height;
	
	
	public Rectangle2D(Vector2D position, double width, double height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	
	// Getters
	public double getMinX() {
		return position.x;
	}
	public double getMinY() {
		return position.y;
	}
	public double getMaxX() {
		return position.x + width;
	}
	public double getMaxY() {
		return position.y + height;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	public Vector2D getDiagonal() {
		return new Vector2D(width, height);
	}
	
	// Setters
	public void set(Rectangle2D r) {
		this.position = r.position;
		this.width = r.width;
		this.height = r.height;
	}
	public void setX(double x) {
		position.setX(x);
	}
	public void setY(double y) {
		position.setY(y);
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void move(Vector2D vector) {
		position.add(vector);
	}
	
	/**
	 * Returns a weak copy of this Rectangle2D object.
	 */
	public Rectangle2D copy() {
		return new Rectangle2D(position, width, height);
	}
	
	/**
	 * Returns wheter this rectangle intersects / overlaps given rectangle.
	 */
	public boolean intersects(Rectangle2D r2) {
		return intersects(this, r2);
	}
	
	/**
	 * Returns wheter given rectangles overlap / intersect.
	 */
	public static boolean intersects(Rectangle2D r1, Rectangle2D r2) {
		// If one rectangle is to the left of the other
	    if (r1.getMinX() > r2.getMaxX() || r2.getMinX() > r1.getMaxX())
	        return false;
	    
	    // If one rectangle is above the other
	    if (r1.getMinY() > r2.getMaxY() || r2.getMinY() > r1.getMaxY())
	    	return false;
	    
	    return true;
	}


	
	@Override
	public String toString() {
		return "Rectangle2D [position=" + position + ", width=" + width + ", height=" + height + "]";
	}
}
