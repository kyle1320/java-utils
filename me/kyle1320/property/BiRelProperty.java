import java.util.function.BiFunction;

class BiRelProperty<E, U, V> extends Property<E> {
	private Property<U> varU;
	private Property<V> varV;

	private BiFunction<U, V, E> func;
	private long u_version, v_version;

	public BiRelProperty(Property<U> varU, Property<V> varV, BiFunction<U, V, E> func) {
		this.varU = varU;
		this.varV = varV;

		this.func = func;
	}

	@Override
	public E get() {
		if (isOld()) {
			super.set(func.apply(varU.get(), varV.get()));

			u_version = varU.version();
			v_version = varV.version();
		}

		return super.get();
	}

	@Override
	public boolean isNullified() {
		if (super.isNullified())
			return true;
		else if (varU.isNullified() || varV.isNullified()) {
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
		return varU.version() != u_version ||
			varV.version() != v_version ||
			varU.isOld() ||
			varV.isOld();
	}
}