package me.kyle1320.linkedlist;

/**	A Queue class which uses Node objects to hold its data.
	@author Kyle Cutler
	@version 11/7/13
*/
public class Queue<E> {
	private Node<E> head;
	private int size;

	/**	Constructs a new, empty Queue
	*/
	public Queue() {
		head = new Node<E>();
		size = 0;
	}

	/**	Returns true if this Queue has no elements.
		@return true if this Queue has no elements
	*/
	public boolean isEmpty() {
		return head.getNext() == null;
	}

	/**	Adds the given element to the back of this Queue.
		@param element The element to add
		@return The element added
	*/
	public E add(E element) {
		Node<E> node = head;

		while (node.getNext() != null) {
			node = node.getNext();
		}

		node.setNext(new Node<E>(element));
		size++;
		return element;
	}

	/**	Removes the element at the front of this Queue.
		@return The element removed
	*/
	public E remove() {
		if (head.getNext() == null)
			return null;
		E element = head.getNext().getValue();
		head.setNext(head.getNext().getNext());
		size--;
		return element;
	}

	/**	Returns the element currently at the front of this Queue.
		@return the element currently at the front of this Queue
	*/
	public E peek() {
		if (head.getNext() == null)
			return null;
		return head.getNext().getValue();
	}

	/**	Returns the number of elements currently in this Queue.
		@return the number of elements currently in this Queue
	*/
	public int size() {
		return size;
	}

	/**	Returns this Queue as a String in the format {front, element, element, back}.
		@return This Queue as a String
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