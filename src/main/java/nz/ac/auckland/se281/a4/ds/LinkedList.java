package nz.ac.auckland.se281.a4.ds;

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {
	// the head of the linked list
	private Node<T> head;
	// the tail of the linked list
	private Node<T> tail;

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * This is a helper method that takes in a position and returns whether it is
	 * outside allowed bounds
	 * 
	 * @param position the position of the element
	 * @return if the position is valid or not
	 */
	private Boolean invalidPositionException(int position) {
		if ((position < 0) || (position > this.size() - 1)) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns a reference to a node whose position is at pos TODO:
	 * Complete this method
	 * 
	 * @param pos: an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	private Node<T> locateNode(int pos) throws InvalidPositionException {

		if (invalidPositionException(pos)) { // checks if within bounds
			throw new InvalidPositionException("Outside Bounds");
		}

		Node<T> currentNode = this.head;
		for (int count = 0; count < pos; count++) { // iterates through all nodes until the desired node is reached
			currentNode = currentNode.getNext();

		}

		return currentNode;
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {

		// checks size of current linked list to decide on functionality
		if (this.size() == 0) {
			append(element);
		} else {
			Node<T> nodeToPrepend = new Node<T>(element);
			nodeToPrepend.setNext(head); // set the next node to the previous head
			this.head = nodeToPrepend; // set as new head
		}
	}

	/**
	 * This method adds a node with specified data as the end node of the list TODO:
	 * Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {
		Node<T> nodeToAppend = new Node<T>(element);

		if (this.size() == 0) {
			head = nodeToAppend;
		} else {
			tail.setNext(nodeToAppend); // sets the previous tail to point to new tail
		}
		tail = nodeToAppend; // set to tail
		tail.setNext(null);
	}

	/**
	 * This method gets the value of a node at a given position TODO: Complete this
	 * method
	 *
	 * @param pos an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public T get(int pos) throws InvalidPositionException {
		if (invalidPositionException(pos)) {
			throw new InvalidPositionException("Outside Bounds"); // checks if within bounds
		}

		return locateNode(pos).getValue(); // returns the value of the node at that position
	}

	/**
	 * This method adds an node at a given position in the List TODO: Complete this
	 * method
	 * 
	 * @param pos:     an integer, which is the position
	 * @param element: the element to insert
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public void insert(int pos, T element) throws InvalidPositionException {

		if ((pos < 0) || (pos > this.size())) {
			throw new InvalidPositionException("Outside Accepted Bounds"); // checks if within bounds
		}

		if (pos == this.size()) {
			this.append(element);
		} else if (pos == 0) {
			this.prepend(element);
		} else {
			Node<T> nodeToInsert = new Node<T>(element);
			nodeToInsert.setNext(locateNode(pos)); // set next node to the node previously in current position
			locateNode(pos - 1).setNext(nodeToInsert); // make previous node point to current node to insert

		}
	}

	/**
	 * This method removes an node at a given position TODO: Complete this method
	 *
	 * @param pos: an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {
		if (invalidPositionException(pos)) {
			throw new InvalidPositionException("Outside Bounds");
		}

		if ((pos == 0) && (pos == this.size() - 1)) {
			this.head = null;
		} else if (pos == this.size() - 1) {
			locateNode(pos - 1).setNext(null);
		} else if (pos == 0) {
			this.head = locateNode(pos + 1);
		} else {
			locateNode(pos - 1).setNext(locateNode(pos + 1));
		}

	}

	/**
	 * This method returns the size of the Linked list TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {

		int sizeCount = 0;
		Node<T> currentNode = this.head;
		while (currentNode != null) { // iterates until it finds a null (end of list)
			sizeCount++;
			currentNode = currentNode.getNext();
		}

		return sizeCount;
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}
}