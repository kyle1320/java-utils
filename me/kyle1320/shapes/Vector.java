package me.kyle1320.shapes;

import java.io.Serializable;

/**
 * An immutable 2D vector with an x value, y value and length.
 * 
 * @author 	Kyle Cutler
 */
public class Vector implements Serializable {
	public static final Vector zero = new Vector();
	public static final Vector up = new Vector(0, 1, 1);
	public static final Vector down = new Vector(0, -1, 1);
	public static final Vector left = new Vector(-1, 0, 1);
	public static final Vector right = new Vector(1, 0, 1);

	private final double x;
	private final double y;

	// we only calculate length when it is asked for, to make operations like vector.add(vector2).flip().rotate(theta) less costly.
	// this means that length should always be accessed using getLength().
	private boolean calcLength = true;
	private double length = 0.0;
	
	/**
	 * Creates a zero vector (with x value, y value, and length of 0).
	 */
	public Vector() {
		this(0.0, 0.0, 0.0);
	}
	
	/**
	 * Initializes the Vector using x and y values.
	 * 
	 * @param 	x 	The x value to initialize the Vector with
	 * @param 	y 	The y value to initialize the Vector with
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a unit vector with the given angle
	 * 
	 * @param 	angle 	The angle for the result vector
	 */
	public Vector(double angle) {
		this(Math.cos(angle), Math.sin(angle));
	}

	/**
	 * Private constructor used to minimize length calculations. 
	 * If the length of this Vector is guaranteed to be the same as another, methods can use this constructor to copy their length. 
	 * The length is only copied if it has previously been calculated in the other vector, otherwise this constructor is the same as Vector(double x, double y).
	 * 
	 * @param 	x 	The x value to initialize the Vector with
	 * @param 	y 	The y value to initialize the Vector with
	 * @param 	v 	The vector whose length should be copied if it has been calculated
	 */
	private Vector(double x, double y, Vector v) {
		this(x, y);

		if (!v.calcLength) {
			this.calcLength = false;
			this.length = v.length;
		}
	}

	/**
	 * Vector values are initialized to the given values. Use only if the length is guaranteed to be correct. 
	 * 
	 * @param x The x value to initialize the Vector with
	 * @param y The y value to initialize the Vector with
	 * @param length The length to initialize the Vector with
	 */
	private Vector(double x, double y, double length) {
		this(x, y);

		this.calcLength = false;
		this.length = length;
	}
	
	/**
	 * Adds the x and y elements of this vector and another, and returns a new Vector of the sums.
	 * 
	 * @param 	v 	The Vector to add
	 */
	public Vector add(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y);
	}
	
	/**
	 * Subtracts the x and y elements of another vector from this one, and returns a new Vector of the differences.
	 * 
	 * @param 	v 	The Vector to subtract
	 * 
	 * @return 		The result Vector
	 */
	public Vector subtract(Vector v) {
		return new Vector(this.x - v.x, this.y - v.y);
	}
	
	/**
	 * Multiplies this vector's x and y values by a given value, and returns a new Vector of the products.
	 * 
	 * @param 	v 	The value to multiply by
	 * 
	 * @return 		The result Vector
	 */
	public Vector multiply(double v) {
		return new Vector(this.x * v, this.y * v);
	}

	/**
	 * Multiplies this vector's x and y values by another vector's respective values, and returns a new Vector of the products.
	 * 
	 * @param 	v 	The vector to multiply by
	 * 
	 * @return 		The result Vector
	 */
	public Vector multiply(Vector v) {
		return new Vector(this.x * v.x, this.y * v.y);
	}
	
	/**
	 * Divides this vector's x and y values by a given value, and returns a new Vector of the quotients.
	 * 
	 * @param 	v 	The value to divide by
	 * 
	 * @return 		The result Vector
	 */
	public Vector divide(double v) {
		return new Vector(this.x / v, this.y / v);
	}

	/**
	 * Divides this vector's x and y values by another vector's respective values, and returns a new Vector of the quotients.
	 * 
	 * @param 	v 	The vector to divide by
	 * 
	 * @return 		The result Vector
	 */
	public Vector divide(Vector v) {
		return new Vector(this.x / v.x, this.y / v.y);
	}

	/**
	 * Returns the vector that is this vector scaled toward another by a certain amount. Scaling by 1 will return the other vector.
	 * 
	 * @param 	to 		The Vector to scale towards
	 * @param 	amount 	The amount to scale by
	 * 
	 * @return 			The scaled Vector
	 */
	public Vector scale(Vector to, double amount) {
		return new Vector((to.x - this.x)*amount + this.x, (to.y - this.y)*amount + this.y);
		//this.add(to.subtract(this).multiply(amount));
	}
	
	/**
	 * Returns the distance between this vector's values and another's.
	 * 
	 * @param 	v 	The Vector to calculate this vector's distance from
	 * 
	 * @return 		The calculated distance between the two vectors
	 */
	public double distance(Vector v) {
		return Math.hypot(this.x - v.x, this.y - v.y); // slower than sqrt, but no overflow
	}
	
	/**
	 * Returns the unit or normalized vector of this vector, meaning the x and y values are scaled so that the length is 1. 
	 * If this vector is a zero vector (x == y == 0), a zero vector is returned. 
	 * The resulting vector is not guaranteed to have length 1, only to be a good approximation.
	 * 
	 * @return 	The normalized Vector
	 */
	public Vector unit() {
		if (isZero())
			return this;

		if (getLength() == 0)
			return new Vector();
		return new Vector(x/getLength(), y/getLength());
	}
	
	/**
	 * Flips the sign of this vector's values, and returns a new Vector of the values.
	 * 
	 * @return 	A Vector that is the opposite of this vector
	 */
	public Vector flip() {
		return new Vector(-x, -y, this);
	}
	
	/**
	 * Flips the sign of this vector's x value, and returns a new Vector with the new x value and the same y value.
	 * 
	 * @return The flipped Vector
	 */
	public Vector flipX() {
		return new Vector(-x, y, this);
	}
	
	/**
	 * Flips the sign of this vector's y value, and returns a new Vector with the same x value and the new y value.
	 * 
	 * @return The flipped Vector
	 */
	public Vector flipY() {
		return new Vector(x, -y, this);
	}
	
	/**
	 * Returns true if this vector's x and y values are equal to those of another given Vector.
	 * 
	 * @param 	v 	A Vector to test equality against 
	 * 
	 * @return 		True if this vector's values are the same as the other's
	 */
	public boolean equals(Vector v) {
		return this.x == v.x && this.y == v.y;
	}
	
	/**
	 * Returns the dot product of this vector and another.
	 * 
	 * @param 	v 	A Vector to calculate the dot product against
	 * 
	 * @return 		The calculated dot product of the two vectors
	 */
	public double dot(Vector v) {
		return (v.x * this.x) + (v.y * this.y);
	}

	/**
	 * Returns the 2D scalar cross product between this vector and another. That is, this ^ v.
	 * 
	 * @param 	v 	The Vector to take the cross product against
	 * 
	 * @return 		The calculated cross product
	 */
	public double cross(Vector v) {
		return this.x*v.y - this.y*v.x;
	}

	/**
	 * Returns the Vector cross product between a vector and a scalar. That is, v ^ s
	 * 
	 * @param 	v 	The vector to take the cross product with
	 * @param 	s 	The scalar value to take the cross product against
	 * 
	 * @return 		The Vector cross product result
	 */
	public static Vector cross(Vector v, double s) {
		return new Vector(s*v.y, -s*v.x);
	}

	/**
	 * Returns the Vector cross product between a vector and a scalar. That is, s ^ v
	 * 
	 * @param 	s 	The scalar value to take the cross product with
	 * @param 	v 	The vector to take the cross product against
	 * 
	 * @return 		The Vector cross product result
	 */
	public static Vector cross(double s, Vector v) {
		return new Vector(-s*v.y, s*v.x);
	}

	/**
	 * Returns a copy of this vector with its x value replaced by the given value.
	 * 
	 * @param 	x 	The new x value
	 * 
	 * @return 		A new Vector with the values (x, this.y)
	 */
	public Vector withX(double x) {
		return new Vector(x, this.y);
	}

	/**
	 * Returns a copy of this vector with its y value replaced by the given value.
	 * 
	 * @param 	y 	The new y value
	 * 
	 * @return 		A new Vector with the values (this.x, y)
	 * 
	 */
	public Vector withY(double y) {
		return new Vector(this.x, y);
	}
	
	/**
	 * Returns this vector's x value.
	 * 
	 * @return 	The x value of this vector
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Returns this vector's y value.
	 * 
	 * @return 	The y value of this vector
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Returns this vector's length
	 * 
	 * @return 	The calculated length of this vector
	 */
	public double getLength() {
		// calculate the length if we need to
		if (calcLength) {
			calculateLengthFast();
			calcLength = false;
		}

		return this.length;
	}

	/**
	 * Calculates the length of this vector quickly, but with a chance of overflow inbetween.
	 */
	@SuppressWarnings("unused")
	private void calculateLengthFast() {
		this.length = Math.sqrt(x*x + y*y);
	}

	/**
	 * Calculates the length of this vector with no chance of overflow, but takes more time.
	 */
	@SuppressWarnings("unused")
	private void calculateLengthExact() {
		this.length = Math.hypot(x, y);
	}

	/**
	 * Returns this vector's length squared. This can be faster than calling getLength() * getLength(). 
	 * If this method is to be used repeatedly, it could be faster to call getLength() first.
	 * 
	 * @return 	The calculated squared length of this vector
	 */
	public double getLengthSquared() {
		// if the length isn't calculated yet, just use distance formula
		if (calcLength)
			return x*x + y*y;

		return length * length;
	}
	
	/**
	 * Returns the normal vector to this vector. The normal can either be facing to the left or right of this vector.
	 * 
	 * @param 	left 	True if the normal should be on the left side, false for the right side
	 * 
	 * @return 			The normal Vector
	 */
	public Vector normal(boolean left) {
		if (left)
			return new Vector(-y, x, this); // assuming the standard orientation
		else
			return new Vector(y, -x, this);
	}

	/**
	 * Rotates this vector a certain angle around (0, 0).
	 * 
	 * @param 	theta 	the angle to rotate by
	 * 
	 * @return 			The rotated Vector
	 */
	public Vector rotate(double theta) {
		double sin = Math.sin(theta);
		double cos = Math.cos(theta);

		return new Vector(x * cos - y * sin,
						  x * sin + y * cos);
	}

	/**
	 * Rotates this vector a certain angle around a certain point.
	 * 
	 * @param 	around 	The Vector (point) to rotate around
	 * @param 	theta 	the angle to rotate by
	 * 
	 * @return 			The rotated Vector
	 */
	public Vector rotate(Vector around, double theta) {
		Vector translated = this.subtract(around);
		double sin = Math.sin(theta);
		double cos = Math.cos(theta);

		return new Vector(	translated.x * cos - translated.y * sin + around.x,
							translated.x * sin + translated.y * cos + around.y);
	}

	/**
	 * Scales this vector's values so that its length is the given length. 
	 * Accuracy is not guaranteed; the returned vector will only have a length approximately equal to the given value. 
	 * If this is a zero vector, this method returns a zero vector.
	 * 
	 * @param 	length 	The desired length of this vector
	 * 
	 * @return 			A Vector in the same direction as this vector and scaled to the given length
	 */
	public Vector makeLength(double length) {
		if (isZero())
			return this;

		double scale = length/getLength();
		return new Vector(x*scale, y*scale);
	}

	/**
	 * Creates a new Vector in the same direction as this vector with a length the same as this vector's plus a given value. 
	 * If this is a zero vector, a zero vector is returned.
	 * 
	 * @param 	amount 	The amount longer the return vector should be than this one
	 * 
	 * @return 			The lengthened Vector
	 */
	public Vector lengthen(double amount) {
		if (isZero())
			return this;

		double scale = amount / getLength() + 1;
		return new Vector(x*scale, y*scale);
	}

	/**
	 * Compares the angle between the two vectors.
	 * 
	 * @param 	v 	The other vector to compare against
	 * 
	 * @return 		1 if the other vector is clockwise to this, 
	 * 				-1 if the other vector is counter-clockwise to this, 
	 * 				0 if the angles are the same.
	 */
	public int compareAngle(Vector v) {
		return (int)Math.signum(this.x * (this.y - v.y) - this.y * (this.x - v.x));
	}

	/**
	 * Returns this vector's angle.
	 * 
	 * @return 	This vector's angle in the range (-Math.PI, Math.PI]
	 */
	public double angle() {
		return Math.atan2(y, x);
	}

	/**
	 * Returns the angle between the this vector and another.
	 * 
	 * @param 	v 	The other vector to compare
	 * 
	 * @return 		The angle between the two vectors, in the range [0, Math.PI]
	 */
	public double angleBetween(Vector v) {
		return Math.acos(this.dot(v) / (getLength() * v.getLength()));
	}

	/**
	 * Returns the sine value of this vector's angle.
	 * 
	 * @return 	The calculated sine value of this vector's angle
	 */
	public double sin() {
		/*if (getLength() == 0.0)
			return 0.0;*/
		return y/getLength();
	}

	/**
	 * Returns the cosine value of this vector's angle.
	 * 
	 * @return 	The calculated cosine value of this vector's angle
	 */
	public double cos() {
		/*if (getLength() == 0.0)
			return 0.0;*/
		return x/getLength();
	}

	/**
	 * Returns the tangent value of this vector's angle.
	 * 
	 * @return 	The calculated tangent value of this vector's angle
	 */
	public double tan() {
		/*if (x == 0.0)
			return 0.0;*/
		return y/x;
	}

	/**
	 * Returns a unit vector with a random direction
	 * 
	 * @return 	A unit vector with a random direction
	 */
	public static Vector random() {
		double angle = Math.random()*Math.PI*2;

		return new Vector(angle);
	}

	/**
	 * Returns true if this Vector is invalid, meaning on or both of its values is NaN. This is useful to check after performing certain operations, and can result from division by zero.
	 * 
	 * @return 	True if this Vector is invalid
	 */
	public boolean isInvalid() {
		return Double.isNaN(x) || Double.isNaN(y);
	}

	/**
	 * Returns true if this vector's x and y values are 0.
	 * 
	 * @return 	True if this vector's x and y values are 0
	 */
	public boolean isZero() {
		return x == 0 && y == 0;
	}

	/**
	 * Overrides the Object equals method
	 * 
	 * @param 	other 	The other Object to test equality against
	 * 
	 * @return 			True if the other Object is a Vector and has values exactly equal to this Vector's values
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Vector))
			return false;
		Vector v = (Vector)other;
		return v.x == this.x && v.y == this.y;
	}

	/**
	 * An additional equality test that uses a tolerance value to determine whether this vector and another are "equal"
	 * For the two to be equal, |v1.x - v2.x| ≤ tolerance and |v1.y - v2.y| ≤ tolerance
	 * 
	 * @param 	other 	The other Object to test equality against
	 * @param 	other 	The tolerance value to use for testing
	 * 
	 * @return 			True if the other Object is a Vector and has values closer than the tolerance to this Vector's values
	 */
	public boolean equals(Object other, double tolerance) {
		if (other == null || !(other instanceof Vector))
			return false;
		Vector v = (Vector)other;
		return 	Math.abs(v.x - this.x) <= tolerance && 
				Math.abs(v.y - this.y) <= tolerance;
	}
	
	/**
	 * Overrides the Object toString method
	 * 
	 * @return 	A String representation of this Vector
	 */
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}