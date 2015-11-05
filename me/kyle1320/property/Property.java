public class Property<E> {
	private E data = null;
	private long version = 0;
	private boolean nullified = false;

	public Property() {}

	public Property(E data) {
		set(data);
	}

	public E get() {
		return data;
	}

	public void set(E data) {
		this.data = data;
		update();
	}

	public void update() {
		version++;
	}

	public void nullify() {
		this.nullified = true;
		update();
	}

	public boolean isNullified() {
		return nullified;
	}

	public long version() {
		return version;
	}

	public boolean isOld() {
		return false;
	}
}