package me.kyle1320.shapes;

public class Segment {
	private final Vector a, b;

	public Segment(Vector a, Vector b) {
		this.a = a;
		this.b = b;
	}

	public Line line() {
		return new Line(a, b);
	}

	public Vector asVector() {
		return b.subtract(a);
	}

	public Vector getMidpoint() {
		return a.add(b).divide(2);
	}

	public Vector getEndpointA() {
		return a;
	}

	public Vector getEndpointB() {
		return b;
	}

	public double getLength() {
		return a.distance(b);
	}
}