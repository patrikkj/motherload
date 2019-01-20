package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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

	public Vector2D multiply(Vector2D vec) {
		x *= vec.x;
		y *= vec.y;
		return this;
	}
	
	public Vector2D divide(Vector2D vec) {
		x /= vec.x;
		y /= vec.y;
		return this;
	}
	
	// On-vector operations - Vector2D
	public Vector2D set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vector2D setX(double x) {
		this.x = x;
		return this;
	}
	
	public Vector2D setY(double y) {
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

	public Vector2D clamp(Vector2D min, Vector2D max) {
		x = (x < min.x) ? min.x : x;
		x = (x > max.x) ? max.x : x;
		y = (y < min.y) ? min.y : y;
		y = (y > max.y) ? max.y : y;
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
	
	
	// Static methods
	public static Vector2D add(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.x + v2.x, v1.y + v2.y);
	}
	
	public static Vector2D subtract(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.x - v2.x, v1.y - v2.y);
	}
	
	public static Vector2D multiply(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.x * v2.x, v1.y * v2.y);
	}
	
	public static Vector2D divide(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.x / v2.x, v1.y / v2.y);
	}
	
	public static Vector2D combine(Collection<Vector2D> vectors) {
		return vectors.stream().reduce((v1, v2) -> v1.add(v2)).orElse(null);
	}
	
	// Calculations
	public double magnitude() {
		return Math.sqrt(x*x + y*y);
	}
	
	public double angle() {
		return -1 * Math.atan2(-y, x);
	}
	public String toString2() {
		return String.format("[%.2f, %.2f]", x, y).replace(',', '.').replace(". ", ", ");
	}
	
	@Override
	public String toString() {
		return String.format("[%.1f, %.1f]", x, y).replace(',', '.').replace(". ", ", ");
	}
}

