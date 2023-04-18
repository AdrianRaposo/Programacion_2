package queue;

import node.*;
import queue.exceptions.EmptyQueueException;

public class SimpleQueue<E> {
	private Node<E> head;

	public SimpleQueue() {
		head = null;
	}

	// PRE: no est� la cola vac�a
	public E peek() throws EmptyQueueException {
		if (head == null)
			throw new EmptyQueueException("La cola est� vac�a.");

		return head.element();
	}

}
