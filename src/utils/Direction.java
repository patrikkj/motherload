package utils;

/**
 * Constants for describing directions.
 */
public enum Direction {
	N 	(0, -1),
	S 	(0, 1),
	E 	(1, 0),
	W 	(-1, 0),
	NE 	(1, -1),
	NW 	(-1, -1),
	SE 	(1, 1),
	SW 	(-1, 1),
	NONE (0, 0);
	
	// Fields
	private final int x, y;
	private final Vector2D vector;
	
	// Constructor
	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
		this.vector = new Vector2D(x, y);
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public Vector2D getVector() {return vector;}
	
	// Returns the inverse Direction object
	public Direction getInverse() {
		switch (this) {
		case N:
			return S;
		case S:
			return N;
		case E:
			return W;
		case W:
			return E;
		case NE:
			return SW;
		case NW:
			return SE;
		case SE:
			return NW;
		case SW:
			return NE;
		default:
			return NONE;
		}
	}
}