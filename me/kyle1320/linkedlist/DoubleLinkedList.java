package me.kyle1320.linkedlist;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.Collection;
import java.util.ListIterator;
import java.util.Iterator;

public class DoubleLinkedList<E> implements List<E> {
	private DoubleLinkedNode<E> head;
	private int size;

	// Constructors

	public DoubleLinkedList() {
		this.head = new DoubleLinkedNode<E>();
		size = 0;
	}

	public DoubleLinkedList(DoubleLinkedNode<E> head) {
		this.head = head;
		size = 0;
	}

	// Add

	public void addFront(E element) {
		addBetween(element, head, head.next);
	}

	public void addBack(E element) {
		addBetween(element, head.prev, head);
	}

	public boolean add(E element) {
		addBack(element);
		return true;
	}

	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);

		DoubleLinkedNode<E> node = head;

		while (index-- > 0) {
			node = node.next;
		}

		addBetween(element, node, node.next);
	}

	public boolean addAll(Collection<? extends E> c) {
		for (E element : c)
			addBack(element);
		return c.size() > 0;
	}

	public boolean addAll(int index, Collection<? extends E> c) throws IndexOutOfBoundsException{
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);

		DoubleLinkedNode<E> node = head;

		while (index-- > 0) {
			node = node.next;
		}

		for (E element : c) {
			addBetween(element, node, node.next);
			node = node.next;
		}

		return c.size() > 0;
	}

	// Get

	public E getFront() {
		return head.next.value;
	}

	public E getBack() {
		return head.prev.value;
	}

	public E get(int index) throws IndexOutOfBoundsException {
		DoubleLinkedNode<E> node = getNode(index);

		return node.value;
	}

	// Set

	public E set(int index, E element) throws IndexOutOfBoundsException {
		DoubleLinkedNode<E> node = getNode(index);

		E prev = node.value;
		node.value = element;

		return prev;
	}

	// Remove

	public E removeFront() {
		return remove(head.next);
	}

	public E removeBack() {
		return remove(head.prev);
	}

	public E remove(int index) throws IndexOutOfBoundsException {
		DoubleLinkedNode<E> node = getNode(index);

		return remove(node);
	}

	public boolean remove(Object element) {
		DoubleLinkedNode<E> node = head.next;

		while (node != head) {
			if (node == element || node.value.equals(element)) {
				remove(node);
				return true;
			}
			node = node.next;
		}

		return false;
	}

	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		
		for (Object element : c) {
			changed = changed || remove(element);
		}

		return changed;
	}
	
	// Helpers

	private void addBetween(E element, DoubleLinkedNode<E> prev, DoubleLinkedNode<E> next) {
		DoubleLinkedNode<E> add = new DoubleLinkedNode<E>(element, prev, next);

		prev.next = add;
		next.prev = add;
		size++;
	}

	private void addBetween(DoubleLinkedNode<E> add, DoubleLinkedNode<E> prev, DoubleLinkedNode<E> next) {
		prev.next = add;
		next.prev = add;
		size++;
	}

	private E remove(DoubleLinkedNode<E> node) {
		DoubleLinkedNode<E> prev = node.prev;
		DoubleLinkedNode<E> next = node.next;

		prev.next = next;
		next.prev = prev;
		size--;

		return node.value;
	}

	private DoubleLinkedNode<E> getNode(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);

		DoubleLinkedNode<E> node = head.next;

		while (index-- > 0) {
			node = node.next;
		}

		return node;
	}

	// Search

	public boolean contains(Object element) {
		return indexOf(element) >= 0;
	}

	public boolean containsAll(Collection<?> c) {
		for (Object element : c) {
			if (indexOf(element) <= 0)
				return false;
		}

		return true;
	}

	public int indexOf(Object element) {
		int index = 0;
		DoubleLinkedNode<E> node = head.next;

		while (node != head) {
			if (node == null && element == null || node.value == element)
				return index;
			node = node.next;
			index++;
		}

		return -1;
	}

	public int lastIndexOf(Object element) {
		int lastIndex = -1;
		int index = 0;
		DoubleLinkedNode<E> node = head.next;

		while (node != head) {
			if (node == null && element == null || node.value == element)
				lastIndex = index;
			node = node.next;
			index++;
		}

		return lastIndex;
	}

	// Misc

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size <= 0;
	}

	public void clear() {
		head = new DoubleLinkedNode<E>();
		size = 0;
	}

	public DoubleLinkedList<E> subList(int min, int max) {
		return null;
	}

	public boolean retainAll(Collection<?> c) {
		return false;
	}

	public Object[] toArray() {
		return null;
	}

	public <T> T[] toArray(T[] arr) {
		return null;
	}

	// Iterators

	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	public Iterator<E> iterator(int index) throws IndexOutOfBoundsException {
		return new DLLIterator(index);
	}

	public ListIterator<E> listIterator() {
		return new DLLIterator();
	}

	public ListIterator<E> listIterator(int index) throws IndexOutOfBoundsException {
		return new DLLIterator(index);
	}

	// Object overrides

	public String toString() {
		if (size == 0)
			return "{}";

		String as = "{";
		DoubleLinkedNode<E> node = head.next;

		while (node.next != head) {
			as += node + ", ";
			node = node.next;
		}

		as += node + "}";

		return as;
	}

	// Static sorting methods

	/**	A static method which sorts the given DoubleLinkedList using the selection sorting method.
		@param list A DoubleLinkedList object to sort
	*/
	public static <T extends Comparable<? super T>> void sortSelection(DoubleLinkedList<T> list) {
		DoubleLinkedNode<T> node = list.head.next;
		DoubleLinkedNode<T> scan;
		DoubleLinkedNode<T> min, val;

		while (node.next != list.head) {
			scan = node.next;
			min = node;

			while (scan != list.head) {
				if (scan.value.compareTo(min.value) < 0) {
					min = scan;
				}

				scan = scan.next;
			}

			node.swap(min);
			node = min.next;
		}
	}

	/**	A static method which sorts the given DoubleLinkedList using the insertion sorting method.
		@param list A DoubleLinkedList object to sort
	*/
	public static <T extends Comparable<? super T>> void sortInsertion(DoubleLinkedList<T> list) {
		DoubleLinkedNode<T> node = list.head.next;
		DoubleLinkedNode<T> scan;
		T element, prev;

		while (node != list.head) {
			element = node.value;
			scan = node;
			prev = scan.prev.value;

			while (scan.prev != list.head && prev.compareTo(element) > 0) {
				scan = scan.prev;
				prev = scan.prev.value;
			}

			list.remove(node);
			list.addBetween(node, scan.prev, scan);
		}
	}

	// Private ListIterator class

	private class DLLIterator implements ListIterator<E> {
		private int index;
		private DoubleLinkedNode<E> node;
		private DoubleLinkedNode<E> lastNode;

		// Constructors

		public DLLIterator() {
			index = size-1;
			node = head;
			lastNode = null;
		}

		public DLLIterator(int index) throws IndexOutOfBoundsException {
			if (index < 0 || index >= size)
				throw new IndexOutOfBoundsException();

			this.index = index-1;
			node = head;
			for (int i=0; i < index; i++)
				node = node.next;
		}

		// Move

		public boolean hasNext() {
			return size > 0 && node.next != head;
		}

		public boolean hasPrevious() {
			return size > 0 && node != head;
		}

		public E next() throws NoSuchElementException {
			if (size <= 0)
				throw new NoSuchElementException();

			node = nextFrom(node);
			lastNode = node;
			index++;

			return node.value;
		}

		public E previous() throws NoSuchElementException {
			if (size <= 0)
				throw new NoSuchElementException();

			if (node == head)
				node = head.prev;

			E value = node.value;
			lastNode = node;
			node = node.prev;
			index--;

			return value;
		}

		public int nextIndex() {
			if (size > 0)
				return real(index + 1);
			return -1;
		}

		public int previousIndex() {
			if (size > 0)
				return real(index);
			return -1;
		}

		// Change

		public void remove() throws IllegalStateException {
			if (lastNode == null)
				throw new IllegalStateException();
			if (lastNode == node)
				node = node.prev;

			DoubleLinkedList.this.remove(lastNode);
			lastNode = null;
		}

		public void add(E element) {
			DoubleLinkedList.this.addBetween(element, node, node.next);
			index++;
			node = node.next;
			lastNode = null;
		}

		public void set(E element) throws IllegalStateException {
			if (lastNode == null)
				throw new IllegalStateException();
			lastNode.value = element;
		}

		// Helpers

		private DoubleLinkedNode<E> nextFrom(DoubleLinkedNode<E> node) {
			if (node == head.prev)
				return head.next;
			return node.next;
		}

		private int real(int index) {
			if (size == 0)
				return -1;
			while (index >= size)
				index -= size;
			while (index < 0)
				index += size;

			return index;
		}

		// Overrides

		public String toString() {
			return "" + lastNode;
		}
	}

	// Double linked node class
	
	private static class DoubleLinkedNode<E> {
		E value;
		DoubleLinkedNode<E> next;
		DoubleLinkedNode<E> prev;

		// Constructors

		public DoubleLinkedNode() {
			this(null);
		}

		public DoubleLinkedNode(E value) {
			this.value = value;
			this.next = this;
			this.prev = this;
		}

		public DoubleLinkedNode(E value, DoubleLinkedNode<E> prev, DoubleLinkedNode<E> next) {
			this.value = value;
			this.next = next;
			this.prev = prev;
		}

		public void swap(DoubleLinkedNode<E> other) {
			if (other == null || this == other)
				return;

			E temp = other.value;
			other.value = this.value;
			this.value = temp;
		}

		// Overrides

		public String toString() {
			return "" + value;
		}
	}
}