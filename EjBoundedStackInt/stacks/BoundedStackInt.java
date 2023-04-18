package stacks;

import stacks.excepciones.EmptyStackExp;
import stacks.excepciones.FullStackExp;

public class BoundedStackInt {
	private int[] datos;
	private int noElems;
	
	public BoundedStackInt(int capacidad) {
		datos = new int[capacidad];
		noElems = 0;
	}
	
	public boolean isEmpty() {
		return noElems == 0;
	}
	
	public boolean isFull() {
		return noElems == datos.length;
	}
	
	public void push(int e) throws FullStackExp {
		if (noElems == datos.length)
			throw new FullStackExp("Pila llena con " + noElems);
		datos[noElems++] = e;
	}
	
	public int pop() throws EmptyStackExp {
		if (noElems == 0)
			throw new EmptyStackExp("Pila vacía");
		return datos[--noElems];
	}
	
	public int peek() throws EmptyStackExp {
		if (noElems == 0)
			throw new EmptyStackExp("Pila vacía");
		return datos[noElems - 1];
	}
}
