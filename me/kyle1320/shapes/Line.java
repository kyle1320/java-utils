package me.kyle1320.shapes;

import java.io.Serializable;

/**	An immutable 2D line with no specific direction.
	@author Kyle Cutler
	@version 3/2/14
*/
public class Line implements Serializable {
	private final Vector normal; // a unit vector perpendicular to the line
	private final double distance; // closest distance to the origin along the normal

	/**	Creates a new Line with the given properties.
		@param normal The normal vector to this line, that is a unit vector perpendicular to this line
		@param distance The closest distance this line is from the origin in the direction of the normal
	*/
	public Line(Vector normal, double distance) {
		this.normal = normal;
		this.distance = distance;
	}

	/**	Creates a new Line that passes through the given point and through the origin. Any particular direction of the normal is not guaranteed.
		@paran thgough The point that this Line should pass through
	*/
	public Line(Vector through) {
		this(through.unit().normal(true), 0.0);
	}

	/**	Creates a new Line that crosses the two given points. Any particular direction of the normal is not guaranteed.
		@param p1 The first point to use when defining this line
		@param p2 The second point to use when defining this line
	*/
	public Line(Vector p1, Vector p2) {
		Vector tangent = p2.subtract(p1).unit(); // tangent along the line pointing from p1 to p2
		
		normal = tangent.normal(true); // pick a side, any side
		distance = p2.dot(normal); // p1 should also work
	}

	/**	Creates a new Line from slope-intercept form.
		@param slope The slope of the line
		@param intercept The y-intercept of the line
	*/
	public Line(double slope, double intercept) {
		normal = new Vector(-1/slope); // we want the perpendicular vector
		distance = -intercept * normal.getX() / slope; // after some basic algebra using the algorithms from slope() and yIntercept()
	}

	/**	Returns the point where this line and another intersect. If the lines are prefectly parallel, null is returned.
		@param other The other Line to intersect with
		@return a Vector that is the point this line and the other intersect
	*/
	public Vector intersect(Line other) {

		/**********************************************************************************/

		// Va = this normal
		// Vb = other normal
		// da = this distance
		// db = other distance
		// S = solution vector
		// * is the dot product
		// ^ is the cross product

		// solution equations
		// Va * S = da
		// Vb * S = db

		// rewrite as matrix multiplication
		// [Va_x, Va_y ; Vb_x, Vb_y] * [S_x ; S_y] = [da ; db]
		// A						 * S		   = b
		// A^-1 = [Vb_y, -Va_y ; -Vb_x, Va_x] / (Va_x * Vb_y - Va_y * Vb_x)
		// A^-1 * A * S = A^-1 * b
		// S = A^-1 * b
		// S = [Vb_y * da - Va_y * db ; Va_x * db - Vb_x * da] / (Va_x * Vb_y - Va_y * Vb_x)

		// convert back to vectors
		// S = {Vb_y * da - Va_y * db, Va_x * db - Vb_x * da} / (Va ^ Vb)

		/**********************************************************************************/

		// determinant of A
		double det = this.normal.cross(other.normal);

		if (det == 0) // lines are perfectly parallel
			return null;

		return new Vector(	other.normal.getY() * this.distance - this.normal.getY() * other.distance, 
							this.normal.getX() * other.distance - other.normal.getX() * this.distance).divide(det);
	}

	/**	Returns a copy of this line "moved" a certain amount.
		@param move The amount to move this line by
		@return The new Line
	*/
	public Line move(Vector move) {
		return new Line(normal, distance + move.dot(normal));
	}

	/**	Returns the absolute distance the given point is from this line.
		@param point The Vector point to test
		@return The absolute minimum distance the point is from this line
	*/
	public double distance(Vector point) {
		return Math.abs(point.dot(normal) - distance);
	}

	/**	Rotates this line around the origin by a given angle.
		@param theta The angle to rotate by
		@return The rotated Line
	*/
	public Line rotate(double theta) {
		return new Line(normal.rotate(theta), distance);
	}

	/**	Returns the slope of this line, ignoring possible division by zero.
		@return The slope of this line
	*/
	public double slope() {
		return -normal.getX()/normal.getY();
	}

	/**	Returns the y-intercept of this line, ignoring possible parallelism.
		@return The y-intercept of this line
	*/
	public double yIntercept() {
		return distance / normal.getY(); // amazing what it simplifies down to
	}

	/**	Returns true if this line is perfectly parallel to another. 
		This method should not be relied upon, as it does not account for errors in precision. 
		It will therefore often return false even if the two lines are effectively parallel.
		@param other The Line to test for parallelism against
		@return True if this line and the other are perfectly parallel
	*/
	public boolean parallel(Line other) {
		return normal.cross(other.normal) == 0;
	}

	/**	Returns the line perpendicular to this line which also passes through the given point. Any particular direction of the normal is not guaranteed.
		@param ref The reference point, that is the Vector the resulting line should pass through
		@return A Line perpendicular to this line which also passes through the given point
	*/
	public Line perpendicular(Vector ref) {
		Vector normal = this.normal.normal(true); // looks good to me.
		return new Line(normal, normal.dot(ref));
	}

	/**	Returns the normal to this line. The normal is a unit vector perpendicular to this line.
		@return The normal Vector to this line
	*/
	public Vector getNormal() {
		return normal;
	}

	/**	Returns the distance along the normal that this line is from the origin. The normal vector times this distance should 
		@return The distance this 
	*/
	public double getDistance() {
		return distance;
	}

	/**	Returns a Line that passes through the origin in a random direction.
		@return A Line that passes through the origin in a random direction
	*/
	public static Line random() {
		return new Line(Vector.random(), 0.0);
	}

	/**	Returns a Line that passes through the given point in a random direction.
		@param through The point to pass through
		@return A Line that passes through the given point in a random direction
	*/
	public static Line random(Vector through) {
		Vector normal = Vector.random();
		return new Line(normal, normal.dot(through));
	}

	/**	Returns a Line with a random direction that is a given distance from the origin.
		@param dist The distance that the returned line should be from the origin
		@return A Line with a random direction that is the given distance from the origin
	*/
	public static Line random(double dist) {
		return new Line(Vector.random(), dist);
	}

	/**	Overrides the Object toString method
		@return A String representation of this Line
	*/
	public String toString() {
		return "(" + normal + ", " + distance + ")";
	}
}