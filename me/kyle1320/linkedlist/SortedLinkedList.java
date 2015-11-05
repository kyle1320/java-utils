package me.kyle1320.linkedlist;

public class SortedLinkedList<E extends Comparable<? super E>> {
	private Node<E> head;
	private int size;

	public SortedLinkedList() {
		head = new Node<E>();
	}

	public E add(E element) {
		Node<E> node = head;
		Node<E> next = node.getNext();

		while (next != null && next.getValue().compareTo(element) < 0) {
			node = node.getNext();
			next = node.getNext();
		}

		insert(element, node);

		return element;
	}

	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		Node<E> node = head.getNext();

		while (index-- > 0)
			node = node.getNext();

		return node.getValue();
	}

	private void insert(E element, Node<E> prev) {
		Node<E> next = new Node<E>(element);
		next.setNext(prev.getNext());
		prev.setNext(next);
	}

	public String toString() {
		String as = "{";
		Node<E> node = head.getNext();

		while (node != null && node.getNext() != null) {
			as += node.toString() + ", ";
			node = node.getNext();
		}

		if (node != null)
			as += node.toString();
		as += "}";

		return as;
	}
}