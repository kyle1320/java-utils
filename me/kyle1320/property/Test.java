public class Test {
	public static void main(String[] args) {
		Property<Integer> a = new Property<>(8);
		Property<Double> b = new Property<>(6.5);
		RelProperty<Integer, Integer> c = new RelProperty<>(a, (x) -> x * 2);
		BiRelProperty<Double, Integer, Double> d = new BiRelProperty<>(c, b, (x, y) -> x + y);

		System.out.println(d.get());
		a.set(5);
		System.out.println(d.get());
		b.set(4.5);
		System.out.println(d.get());
		a.set(2);
		System.out.println(d.get());
	}
}