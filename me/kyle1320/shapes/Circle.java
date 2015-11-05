package me.kyle1320.shapes;

public class Circle {
	private final Vector center;
	private final double radius;

	public Circle(Vector center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle move(Vector move) {
		return new Circle(center.add(move), radius);
	}

	public Circle grow(double amount) {
		return new Circle(center, radius+amount);
	}

	public boolean intersects(Line line) {
		return line.distance(center) <= radius;
	}

	public Line tangent(double angle) {
		return new Line(new Vector(angle), radius).move(center);
	}

	public Vector getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}
}