package entities;

import enums.Material;
import enums.Resource;
import javafx.scene.image.Image;
import utils.Collision;
import utils.Event;
import utils.Rectangle2D;
import utils.Vector2D;

public class Entity {
	/*
	 *	Entity 
	 */
	private Body body;
	private Material material;
	private Resource resource;
	private CollisionType collisionType;
	private Rectangle2D bounds;
	
	/*
	 * Sprite rendering
	 */
	private double width;
	private double height;
	private boolean flipX;
	private boolean filpY;
	
	/*
	 * Statics
	 */
	private Vector2D position;
	private Vector2D offset;
	private double angle;

	/*
	 * Kinematics
	 */
	private Vector2D velocity;
	private Vector2D acceleration;
	
	/*
	 * Dynamics
	 */
	private double mass;
	private Vector2D force;
	private boolean lockAxisX;
	private boolean lockAxisY;
	private boolean lockRotation;
	private double gravityScale;
	private double linearDrag;
	private double angularDrag;
	
	/*
	 * Collision events
	 */
//	private List<Entity> activeCollisions;
	private Event<Collision> onCollisionStart;
	private Event<Collision> onCollisionStay;
	private Event<Collision> onCollisionEnd;
	
	
	/**
	 * Constructor for {@code STATIC} entities.
	 */
	protected Entity(Vector2D position, Vector2D offset, 
			double width, double height, Material material) 
	{
		this.position = position;
		this.offset = offset;
		
		this.bounds = new Rectangle2D(position, width, height);
		this.width = width;
		this.height = height;
		
		this.material = material;
		this.resource = material.getResource();

		this.body = Body.STATIC;
		initDefault(body);
	}
	
	/**
	 * Constructor for {@code KINEMATIC} entities.
	 */
	protected Entity(Vector2D position, Vector2D offset, 
			double width, double height, Image image) 
	{
		// Static constructor
		this(position, offset, width, height, Material.DEFAULT);
		
		// Body type
		this.body = Body.KINEMATIC;
		initDefault(body);
	}
	
	/**
	 * Constructor for {@code DYNAMIC} entities.
	 */
	protected Entity(Vector2D position, Vector2D offset, 
			double mass, double width, double height, Image image) 
	{
		// Kinematic constructor
		this(position, offset, width, height, image);
		
		// Dynamic properties
		this.mass = mass;
		
		// Body type
		this.body = Body.DYNAMIC;
		initDefault(body);
	}

	/**
	 * Default initialization.
	 * For non-static bodies, this method will be called recursively.
	 */
	private void initDefault(Body body) {
		switch (body) {
		case DYNAMIC:
			// Dynamics
			force = new Vector2D();
			lockRotation = true;
			break;
		case KINEMATIC:
			// Kinematics
			velocity = new Vector2D();
			acceleration = new Vector2D();
			break;
		case STATIC:
			// Collisions
			collisionType = CollisionType.RIGID;
//			activeCollisions = new ArrayList<>();
			onCollisionStart = (collision, e1, e2) -> {};
			onCollisionStay = (collision, e1, e2) -> {};
			onCollisionEnd = (collision, e1, e2) -> {};
			break;
		}
	}
	
	
	/*
	 * Getters
	 */
	// Entity
	public Body getBody() {
		return body;
	}
	public Material getMaterial() {
		return material;
	}
	public Resource getResource() {
		return resource;
	}
	public CollisionType getCollisionType() {
		return collisionType;
	}
	public Rectangle2D getBounds() {
		return bounds;
	}
	// Rendering
	public Image getImage() {
		return resource.getImage();
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	public boolean isFlipX() {
		return flipX;
	}
	public boolean isFilpY() {
		return filpY;
	}
	// Physics
	public Vector2D getPosition() {
		return position;
	}
	public Vector2D getPivot() {
		return Vector2D.add(getPosition(), getOffset());
	}
	public Vector2D getVelocity() {
		return velocity;
	}
	public Vector2D getAcceleration() {
		return acceleration;
	}
	public Vector2D getOffset() {
		return offset;
	}
	public Vector2D getForce() {
		return force;
	}
	public double getMass() {
		return mass;
	}
	public double getAngle() {
		return angle;
	}
	public double getGravityScale() {
		return gravityScale;
	}
	public double getLinearDrag() {
		return linearDrag;
	}
	public double getAngularDrag() {
		return angularDrag;
	}
	public boolean isLockAxisX() {
		return lockAxisX;
	}
	public boolean isLockAxisY() {
		return lockAxisY;
	}
	public boolean isLockRotation() {
		return lockRotation;
	}
	// Collisions
	public Event<Collision> getOnCollisionStart() {
		return onCollisionStart;
	}
	public Event<Collision> getOnCollisionStay() {
		return onCollisionStay;
	}
	public Event<Collision> getOnCollisionEnd() {
		return onCollisionEnd;
	}
	
	/*
	 * Setters
	 */
	// Entity
	public void setBody(Body body) {
		this.body = body;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public void setCollisionType(CollisionType collisionType) {
		this.collisionType = collisionType;
	}
	public void setBounds(Rectangle2D bounds) {
		this.bounds = bounds;
	}
	// Rendering
	public void setWidth(double width) {
		this.width = width;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public void setFlipX(boolean flipX) {
		this.flipX = flipX;
	}
	public void setFilpY(boolean filpY) {
		this.filpY = filpY;
	}
	// Physics
	public void setPosition(Vector2D position) {
		this.position = position;
	}
	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}
	public void setAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
	}
	public void setOffset(Vector2D offset) {
		this.offset = offset;
	}
	public void setForce(Vector2D force) {
		this.force = force;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public void setGravityScale(double gravityScale) {
		this.gravityScale = gravityScale;
	}
	public void setLinearDrag(double linearDrag) {
		this.linearDrag = linearDrag;
	}
	public void setAngularDrag(double angularDrag) {
		this.angularDrag = angularDrag;
	}
	public void setLockAxisX(boolean lockAxisX) {
		this.lockAxisX = lockAxisX;
	}
	public void setLockAxisY(boolean lockAxisY) {
		this.lockAxisY = lockAxisY;
	}
	public void setLockRotation(boolean lockRotation) {
		this.lockRotation = lockRotation;
	}
	// Collisions
	public void setOnCollisionStart(Event<Collision> onCollisionStart) {
		this.onCollisionStart = onCollisionStart;
	}
	public void setOnCollisionStay(Event<Collision> onCollisionStay) {
		this.onCollisionStay = onCollisionStay;
	}
	public void setOnCollisionEnd(Event<Collision> onCollisionEnd) {
		this.onCollisionEnd = onCollisionEnd;
	}
	
	/**
	 * Repositions this entity
	 */
	public void move() {
		
	}
	
	
	// Dynamics
	public void resetForce() {
		this.force.set(0, 0);
	}
	
	public void addForce(Vector2D force) {
		this.force.add(force);
	}
	
	// Geometry
	/**
	 * Returns wheter this entity intersects / overlaps given entity.
	 */
	public boolean intersects(Entity entity) {
		return intersects(this, entity);
	}
	
	/**
	 * Returns wheter given entities overlap / intersect.
	 */
	public static boolean intersects(Entity e1, Entity e2) {
		return Rectangle2D.intersects(e1.getBounds(), e2.getBounds());
	}

	/**
	 * Returns the absolute distance between two entities.
	 */
	public static double distanceBetween(Entity e1, Entity e2) {
		Vector2D pos1 = e1.getPosition().addNew(e1.getOffset());
		Vector2D pos2 = e2.getPosition().addNew(e2.getOffset());
		
		return pos2.subtract(pos1).magnitude();
	}

//	public static void main(String[] args) {
//		Entity e1 = new Entity(new Vector2D(), new Vector2D(), 100, 128, 128, null);
//	}
}


/**
 * Enumeration for different entity body types.
 * <li>Dynamic - Entities that are affected by the environment, 
 * 			required to have physical properties such as mass and drag coefficients.
 * <li>Kinematic - Entities that are moved by explicit user control. No physical properties, nor affected by fields.
 * <li>Static - Entities that are not supposed to move, but can trigger collisions (eg. Blocks).
 */
enum Body {DYNAMIC, KINEMATIC, STATIC;}

/**
 * Enumeration for different collision types.
 * <li>Rigid - Entity acts as a solid block, collision properties defined by {@code material}.
 * <li>Field - Entity acts as a triggerable field, events occuring upon start, stay and end of collision.
 * <li>None - No actions are invoked upon collision.
 */
enum CollisionType {RIGID, FIELD, NONE;}