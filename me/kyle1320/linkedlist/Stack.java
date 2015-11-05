package me.kyle1320.linkedlist;

/**	A Stack class which uses Node objects to hold its data.
	@author Kyle Cutler
	@version 11/7/13
*/
public class Stack<E> {
	private Node<E> head;
	private int size;

	/**	Constructs a new, empty Stack
	*/
	public Stack() {
		head = new Node<E>();
		size = 0;
	}

	/**	Returns true if this Stack has no elements.
		@return true if this Stack has no elements
	*/
	public boolean isEmpty() {
		return head.getNext() == null;
	}

	/**	Adds the given element to the top of this Stack.
		@param element The element to add
		@return The element added
	*/
	public E push(E element) {
		head.setNext(new Node<E>(element, head.getNext()));
		size++;
		return element;
	}

	/**	Removes the element at the top of this Stack.
		@return The element removed
	*/
	public E pop() {
		E element = head.getNext().getValue();
		head.setNext(head.getNext().getNext());
		size--;
		return element;
	}

	/**	Returns the element currently at the top of this Stack.
		@return the element currently at the top of this Stack
	*/
	public E peek() {
		return head.getNext().getValue();
	}

	/**	Returns the number of elements currently in this Stack.
		@return the number of elements currently in this Stack
	*/
	public int size() {
		return size;
	}

	/**	Returns this Stack as a String in the format {top, element, element, bottom}.
		@return This Stack as a String
	*/
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