package utils;

public class Vector2D {
	// Fields
	public double x;
	public double y;
	
	
	// Constructors
	public Vector2D() {
		
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Vector2D v) {
		this.x = v.x;
		this.y = v.y;
	}
	
	
	// Vector input operations
	public void set(Vector2D vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public Vector2D add(Vector2D v) {
		x += v.x;
		y += v.y;
		return this;
	}
	
	public Vector2D subtract(Vector2D v) {
		x -= v.x;
		y -= v.y;
		return this;
	}
	
	
	// On-vector operations - Vector2D
	public Vector2D set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vector2D toUnit() {
		double m = magnitude();
		if (m != 0 && m != 1)
			divide(m);
		return this;
	}
	
	public Vector2D limit(double max) {
		if (magnitude() > max) {
			toUnit();
			multiply(max);
		}
		return this;
	}
	
	public Vector2D add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2D subtract(double x, double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public Vector2D multiply(double n) {
		x *= n;
		y *= n;
		return this;
	}
	
	public Vector2D divide(double n) {
		x /= n;
		y /= n;
		return this;
	}
	
	public Vector2D square() {
		multiply(magnitude());
		return this;
	}
	
	public Vector2D copy() {
		return new Vector2D(x, y);
	}

	
	// Operations - create new vector
	public Vector2D addNew(Vector2D v) {
		return copy().add(v);
	}
	
	public Vector2D subtractNew(Vector2D v) {
		return copy().subtract(v);
	}
	
	public Vector2D multiplyNew(double n) {
		return copy().multiply(n);
	}
	
	public Vector2D divideNew(double n) {
		return copy().divide(n);
	}
	
	
	// Calculations
	public double magnitude() {
		return Math.sqrt(x*x + y*y);
	}
	
	public double angle() {
		return -1 * Math.atan2(-y, x);
	}
	
	@Override
	public String toString() {
		return String.format("[%.3f, %.3f]", x, y).replace(',', '.').replace(". ", ", ");
	}
}

