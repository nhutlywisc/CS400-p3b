// DO IMPLEMENT A BINARY SEARCH TREE IN THIS CLASS
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: p3a
//Files: LinkedNodes.java, Node.java. DataStructureADT.java, HashTable.java, HashTableADT.java,
// HashTableTest, IllegalNullKeyException.java, KeyNotFoundException.java
//Course: (COMP SCI 400 LEC 002, Spring, 2020)
//
//Student: (Nhut Minh Ly (Jack))
//Email: (nmly@wisc.edu)
//Lecturer's Name: (Debra Deppeler)
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name: NONE
//Partner Email: NONE
//Partner Lecturer's Name: NONE
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//___ Write-up states that pair programming is allowed for this assignment.
//___ We have both read and understand the course Pair Programming Policy.
//___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully
//acknowledge and credit those sources of help here. Instructors and TAs do
//not need to be credited here, but tutors, friends, relatives, room mates,
//strangers, and others do. If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons: NONE
//Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * Defines a the generic linked list of nodes class. Every linked list will be
 * each element of the array hashtable.
 * 
 * @author Nhut Ly (nmly@wisc.edu)
 * @param <K> Key: Comparable type to be used as a key to an associated value.
 * @param <V> value associated with the given key.
 */
class LinkedNodes<K extends Comparable<K>, V> {
	private Node<K, V> head;
	private Node<K, V> tail;
	private int size; // size of linked list

	/**
	 * Constructs an empty linked list
	 */
	public LinkedNodes() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Insert new node at the end of linked list
	 * @param newNode - new Node to insert
	 */
	public void insert(Node<K, V> newNode) {
		if (head == null && tail == null) {// if the linked list is empty
			head = newNode;
			tail = newNode;
		} else {
			// if the linked list is not empty insert at the end
			Node<K, V> pre = tail;
			pre.setNext(newNode);
			newNode.setPrevious(pre);
			tail = newNode;
		}
		size++;
	}

	/**
	 * Remove the Node that contains the provided key
	 * @param key - Node contains this key will be removed
	 * @return true if successfully removed, otherwise if node not found, return false
	 */
	public boolean remove(K key) {
		if (size > 0) {
			Node<K, V> current = head;
			while (current.getKey().compareTo(key) != 0 && current != tail) {// searching for node
				current = current.getNext();
			}
			if (current.getKey().compareTo(key) == 0) {// when node found
				if (current == head && current == tail) {// only 1 node in list
					head = null;
					tail = null;
				} else if (current == head) {// remove at head
					head = current.getNext();
					current = null;
				} else if (current == tail) {// remove at tail
					tail = current.getPrevious();
					current = null;
				} else { // remove at middle
					Node<K, V> pre = current.getPrevious();
					Node<K, V> next = current.getNext();
					pre.setNext(next);
					next.setPrevious(pre);
				}
				size--;
				return true;
			}
			return false; // node not found;
		} else
			return false;
	}

	/**
	 * Lookup a node
	 * 
	 * @param findNode - node
	 * @return the node if found, null otherwise
	 */
	public Node<K, V> lookUp(Node<K, V> findNode) {
		if (size == 0) {
			return null;
		} else {
			Node<K, V> current = head;
			// keeps traversing through linked list searching for the node
			while (current.compareTo(findNode) != 0 && current.getNext() != null) {
				current = current.getNext();
			}
			if (current.compareTo(findNode) == 0) { // found node
				return current;
			}
			return null;
		}
	}

	/**
	 * Gets the head of linked list
	 * @return head
	 */
	public Node<K, V> getHead() {
		return head;
	}

	/**
	 * Gets the size of linked list
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Prints out the linked nodes
	 */
	public void printLinkedNodes() {
		Node<K, V> current = head;
		for (int i = 0; i < size; i++) {
			System.out.print("(" + current.getKey() + ", " + current.getValue() + ")" + ", ");
			current = current.getNext();
		}
	}
}// end of LinkedNodes class
