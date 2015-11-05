package me.kyle1320.linkedlist;

/** A generic node class which holds a value and a pointer to the next node.
	@author Kyle Cutler
	@version 11/7/13
*/
class Node<E> {
	private E value;
	private Node<E> next;

	// Constructors

	public Node() {
		this(null);
	}

	public Node(E value) {
		this.value = value;
		this.next = null;
	}

	public Node(E value, Node<E> next) {
		this.value = value;
		this.next = next;
	}

	// Accessors

	public E getValue() {
		return value;
	}

	public Node<E> getNext() {
		return next;
	}

	// Mutators

	public void setValue(E value) {
		this.value = value;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	// Overrides

	public String toString() {
		if (value == null)
			return "null";
		return value.toString();
	}
}