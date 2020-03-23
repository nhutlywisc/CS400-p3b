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
 * Defines a the generic Node class that represents each key-value pair in hashtable.
 * @author Nhut Ly (nmly@wisc.edu)
 * @param <K> Key: Comparable type to be used as a key to an associated value.
 * @param <V> value associated with the given key.
 */
class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>> {

	private K key;
	private V value;
	private Node<K, V> previous; // previous node
	private Node<K, V> next; // next node

	/**
	 * Constructs a node
	 * @param key
	 * @param value
	 * @param next - next node
	 */
	public Node(K key, V value, Node<K, V> next) {
		this.key = key;
		this.setValue(value);
		this.setNext(next);
		this.previous = null;
	}

	@Override
	/**
	 * Compare two node by comparing its key
	 */
	public int compareTo(Node<K, V> otherNode) {
		return key.compareTo(otherNode.key);
	}
	
	/**
	 * Gets key method
	 * @return key of Node
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Sets key method
	 * @param key - new key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * Gets value method
	 * @return value of Node
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Sets value method
	 * @param value - new value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * Gets next node
	 * @return next - next Node
	 */
	public Node<K, V> getNext() {
		return next;
	}

	/**
	 * Sets next node method
	 * @param next - new next Node to set
	 */
	public void setNext(Node<K, V> next) {
		this.next = next;
	}

	/**
	 * Gets previous node method
	 * @return previous - previous node
	 */
	public Node<K, V> getPrevious() {
		return previous;
	}
	
	/**
	 * Sets new previous node method
	 * @param previous - new previous Node to set
	 */
	public void setPrevious(Node<K, V> previous) {
		this.previous = previous;
	}

}// end of Node class
