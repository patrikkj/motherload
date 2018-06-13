package core;

public class Vector2D {
	
	private double x;
	private double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D() {}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double magnitude() {
		return Math.sqrt(x*x + y*y);
	}
	
	public void toUnit() {
		double m = magnitude();
		if (m != 0 && m != 1)
			divide(m);
	}
	
	public void limit(double max) {
		if (magnitude() > max) {
			toUnit();
			multiply(max);
		}
	}
	
	public void add(Vector2D v) {
		x += v.x;
		y += v.y;
	}
	
	public void subtract(Vector2D v) {
		x -= v.x;
		y -= v.y;
	}

	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void subtract(double x, double y) {
		this.x -= x;
		this.y -= y;
	}
	
	public void multiply(double n) {
		x *= n;
		y *= n;
	}
	
	public void divide(double n) {
		x /= n;
		y /= n;
	}
	
	public double angle() {
		return -1 * Math.atan2(-y, x);
	}
}

