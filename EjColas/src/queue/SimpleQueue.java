package queue;

import node.*;
import queue.exceptions.EmptyQueueException;

public class SimpleQueue<E> {
	private Node<E> head;

	public SimpleQueue() {
		head = null;
	}

	// PRE: no está la cola vacía
	public E peek() throws EmptyQueueException {
		if (head == null)
			throw new EmptyQueueException("La cola está vacía.");

		return head.element();
	}

}
