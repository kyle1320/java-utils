import java.util.function.Function;

class RelProperty<E, U> extends Property<E> {
	private Property<U> varU;

	private Function<U, E> func;
	private long u_version;

	public RelProperty(Property<U> varU, Function<U, E> func) {
		this.varU = varU;

		this.func = func;
	}

	@Override
	public E get() {
		if (isOld()) {
			super.set(func.apply(varU.get()));

			u_version = varU.version();
		}

		return super.get();
	}

	@Override
	public boolean isNullified() {
		if (super.isNullified())
			return true;
		else if (varU.isNullified()) {
			nullify();
			return true;
		}

		return false;
	}

	@Override
	public void set(E data) {
		throw new UnsupportedOperationException("Cannot set a property that relies on another!");
	}

	@Override
	public boolean isOld() {
		return varU.isOld() || varU.version() != u_version;
	}
}