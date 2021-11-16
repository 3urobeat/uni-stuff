package de.uniluebeck.itm.schiffeversenken.engine;

/**
 * A 2 dimensional vector based on integers. Keep in mind that vectors are immutable.
 * @author leondietrich
 * @see Vec2D for a double based 2 dimensional vector
 *
 */
public final class Vec2 {
	private int x;
	private int y;
	
	/**
	 * Construct a new Vector using the given components
	 * @param x The x part
	 * @param y The y part
	 */
	public Vec2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Use this method in order to retrieve the X component of this vector.
	 * @return The X component.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Use this method in order to retrieve the Y component of this vector.
	 * @return The Y component.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Add another vector onto this one.
	 * @param other The other vector to add.
	 * @return a new Vec2 containing the sum of the two given vectors.
	 */
	public Vec2 add(Vec2 other) {
		return new Vec2(other.getX() + this.getX(), other.getY() + this.getY());
	}
	
	/**
	 * Add another vector onto this one.
	 * @param x The X component of the vector to add.
	 * @param y The Y component of the vector to add.
	 * @return a new Vec2 containing the sum of the two given vectors.
	 */
	public Vec2 add(int x, int y) {
		return new Vec2(this.getX() + x, this.getY() + y);
	}
	
	/**
	 * Perform a scalar multiplication.
	 * @param b The scalar to use.
	 * @return A new scaled vector.
	 */
	public Vec2 multiply(int b) {
		return new Vec2(this.getX() * b, this.getY() * b);
	}
	
	@Override
	public String toString() {
		return "Vec2: " + this.getX() + ", " + this.getY();
	}
}
