package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods
	private Node<T> tail;

	public NodesStackAndQueue() {
		head = null;
		tail = null;
	}

	/**
	 * Checks if the stack / queue is empty
	 * 
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack. TODO:
	 * Complete this method
	 * 
	 * @param element the element to be "pushed"
	 */
	public void push(T element) {
		Node<T> nodeToPush = new Node<T>(element);

		if (isEmpty()) {
			head = nodeToPush;
		} else {
			nodeToPush.setNext(tail);
		}

		tail = nodeToPush;
	}

	/**
	 * pop an element from the top of the stack (removes and returns the top
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException(); // exception check
		}

		if (head == tail) { // if size of stack is 1
			T tailValue = tail.getValue();
			head = null;
			tail = null;
			return tailValue;
		} else {
			Node<T> nodeToPop = tail;
			tail = tail.getNext();
			return nodeToPop.getValue();
		}
	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		return tail.getValue();
	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		if (isEmpty()) {
			push(element);
		} else {
			Node<T> nodeToAppend = new Node<T>(element);
			head.setNext(nodeToAppend);
			head = nodeToAppend;
		}
	}
}
