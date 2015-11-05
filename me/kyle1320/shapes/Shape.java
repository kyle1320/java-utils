package me.kyle1320.shapes;

public class Shape {
	protected Vector[] vertices;
	protected Vector[] normals;

	protected Vector center;

	public Shape(Vector... vertices) {
		this.vertices = vertices;
		normals = new Vector[vertices.length];
		//generateNormals();
	}

	public Shape(Shape copy) {
		this(copy.vertices, copy.normals);
	}

	private Shape(Vector[] vertices, Vector[] normals) {
		this.vertices = vertices;
		this.normals = normals;
	}

	private void generateNormal(int side) {
		normals[side] = getSide(side).normal(false).unit();
	}

	public boolean overlaps(Vector point) {
		Vector normal;

		for (int i=0; i < vertices.length; i++) {
			normal = getNormal(i);
			if (normal.dot(point) > normal.dot(vertices[i]))
				return false;
		}

		return true;
	}

	public boolean overlaps(Shape other) {
		Vector normal;
		double dist, minDist;

		for (int i=0; i < vertices.length; i++) {
			minDist = Double.POSITIVE_INFINITY;
			normal = getNormal(i);
			
			for (int j=0; j < other.vertices.length; j++) {
				dist = other.vertices[j].dot(normal);

				if (dist < minDist)
					minDist = dist;
			}

			if (minDist > vertices[i].dot(normal)) // separating axis
				return false;
		}

		for (int i=0; i < other.vertices.length; i++) {
			minDist = Double.POSITIVE_INFINITY;
			normal = other.getNormal(i);
			
			for (int j=0; j < vertices.length; j++) {
				dist = vertices[j].dot(normal);

				if (dist < minDist)
					minDist = dist;
			}

			if (minDist > other.vertices[i].dot(normal)) // separating axis
				return false;
		}

		return true;
	}

	public boolean contains(Circle other) {
		Vector normal;
		double dist, maxDist;

		for (int i=0; i < vertices.length; i++) {
			normal = getNormal(i);
			maxDist = vertices[i].dot(normal);
			dist = other.getCenter().dot(normal) + other.getRadius();

			if (dist > maxDist)
				return false;
		}

		return true;
	}

	public boolean contains(Shape other) {
		Vector normal;
		double dist, maxDist;

		for (int i=0; i < vertices.length; i++) {
			normal = getNormal(i);
			maxDist = vertices[i].dot(normal);
			
			for (int j=0; j < other.vertices.length; j++) {
				dist = other.vertices[j].dot(normal);

				if (dist > maxDist) // lies outside the edge
					return false;
			}
		}

		return true;
	}

	public boolean contains(Vector point) {
		Vector normal;
		double dist, maxDist;

		for (int i=0; i < vertices.length; i++) {
			normal = getNormal(i);
			maxDist = vertices[i].dot(normal);

			dist = point.dot(normal);

			if (dist >= maxDist) // lies outside the edge
				return false;
		}

		return true;
	}

	public double pointDistance(Vector point) {
		int closestFace = 0;
		double dist, closestDist = Double.NEGATIVE_INFINITY;
		Vector normal = Vector.zero;

		for (int i=0; i < vertices.length; i++) {
			normal = getNormal(i);
			dist = normal.dot(point);

			if (dist > closestDist) {
				closestDist = dist;
				closestFace = i;
			}
		}

		closestDist -= normal.dot(vertices[closestFace]);

		Vector s1 = vertices[closestFace];
		Vector s2 = closestFace < vertices.length - 1 ? vertices[closestFace+1] : vertices[0];
		Vector side = s1.subtract(s1);

		Vector r1 = point.subtract(s1);
		Vector r2 = point.subtract(s2);

		double d1 = r1.dot(side);
		double d2 = r2.dot(side.flip());
		
		if (d1 <= 0)
			return r1.getLength();
		else if (d2 <= 0)
			return r2.getLength();
		else
			return closestDist;
	}

	/**	Applies some operation to each Vector in the vertices, and returns the Shape from the resulting set of vertices
	*/
	public Shape apply(UnaryOperation op) {
		Vector[] newVertices = new Vector[vertices.length];

		for (int i=0; i < vertices.length; i++) {
			newVertices[i] = op.apply(vertices[i]);
		}

		return new Shape(newVertices);
	}

	public int numVertices() {
		return vertices.length;
	}

	public Vector[] getVertices() {
		return vertices;
	}

	public Vector getVertex(int vertex) {
		return vertices[vertex];
	}

	public Vector getSide(int side) {
		if (side == vertices.length - 1)
			return vertices[0].subtract(vertices[side]);
		else
			return vertices[side+1].subtract(vertices[side]);
	}

	public Vector getNormal(int side) {
		if (normals[side] == null)
			generateNormal(side);
		return normals[side];
	}

	public Vector getCenter() {
		if (center == null) {
			Vector accum = Vector.zero;
			for (Vector v : vertices)
				accum = accum.add(v);
			center = accum.divide(vertices.length);
		}

		return center;
	}

	public static interface UnaryOperation {
		public Vector apply(Vector a);
	}
}