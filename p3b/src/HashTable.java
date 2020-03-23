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
 * Defines a the hash table class. Every linked list will be each element of the
 * array hashtable. Every linked list contains different nodes which represents
 * a pair K and V
 * 
 * @author Nhut Ly (nmly@wisc.edu)
 * @param <K> Key: Comparable type to be used as a key to an associated value.
 * @param <V> value associated with the given key.
 */

// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
// => My collision resolution is having a linked list for each element of the array. 
// Every time the index of the new node is occupied, 
// the new node will be added to the end of the linked list at the same index.
//
// TODO: explain your hashing algorithm here : I have index= key.hashCode() % capacity
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//=> I combined the hashCode provided by the <K key> object with the % capacity
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
	private Object[] arr; // data structure for hashtable
	private int capacity; // size of array
	private double loadFactorThreshold;
	private int numKeys;
	private double loadFactor;

	// TODO: comment and complete a default no-arg constructor
	/**
	 * Constructs a default Hashtable: creates an array with default size of 10.
	 * Each element is a linked list of nodes. In other words, it is a array of
	 * linked list.
	 */
	public HashTable() {
		this.capacity = 10;
		this.loadFactorThreshold = .75;
		numKeys = 0;
		calculateLoadFactor();
		arr = new Object[capacity];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new LinkedNodes<K, V>();
		}
	}

	// TODO: comment and complete a constructor that accepts
	// initial capacity and load factor threshold
	// threshold is the load factor that causes a resize and rehash
	/**
	 * Constructs a hashtable with desired capacity and load factor threshold
	 * Creates an array with size of provided capacity. Each element is a linked
	 * list of nodes. In other words, it is a array of linked list.
	 * 
	 * @param initialCapacity
	 * @param loadFactorThreshold
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		this.capacity = initialCapacity;
		this.loadFactorThreshold = loadFactorThreshold;
		numKeys = 0;
		calculateLoadFactor();
		arr = new Object[capacity];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new LinkedNodes<K, V>();
		}
	}

	@Override
	// Add the key,value pair to the data structure and increase the number of keys.
	// @param key
	// @param value
	// If key is null, throw IllegalNullKeyException;
	// If key is already in data structure, replace value with new value
	public void insert(K key, V value) throws IllegalNullKeyException {
		if (key == null)
			throw new IllegalNullKeyException();
		Node<K, V> newNode = new Node<K, V>(key, value, null);
		int index = key.hashCode() % capacity; // hash function
		@SuppressWarnings("unchecked")
		LinkedNodes<K, V> list = (LinkedNodes<K, V>) arr[index];
		if (list.getHead() == null) { // if linked list is empty
			list.insert(newNode);
			numKeys++;
		} else {// if linked list not empty, traverse list to checks if duplicate
			if (list.lookUp(newNode) == null) {
				list.insert(newNode);
				numKeys++;
			} else if (list.lookUp(newNode) != null) {
				// if duplicate, update the existed key to new value
				list.lookUp(newNode).setValue(value);
			}
		}
		calculateLoadFactor(); // update load factor
		// checks of load factor >= load factor threshold
		// if so, resize and rehash
		getLoadFactorThreshold();

	}

	@Override
	// If key is found,
	// remove the key,value pair from the data structure
	// decrease number of keys.
	// @return true if successfully remove. otherwise, return false
	// @throw IllegalNullKeyException If key is null
	public boolean remove(K key) throws IllegalNullKeyException {
		if (key == null)
			throw new IllegalNullKeyException();
		try {
			if (get(key) != null) {// if key exists
				// locate index of linked list where node exists.
				int index = key.hashCode() % capacity;
				@SuppressWarnings("unchecked")
				LinkedNodes<K, V> list = (LinkedNodes<K, V>) arr[index];
				if (list.remove(key) == true) { // delete node of located linked list
					numKeys--;
					calculateLoadFactor(); // update load factor
					return true;
				}
			}
			return false;
		} catch (KeyNotFoundException e) {
			return false;
		}
	}

	@Override
	// Returns the value associated with the specified key
	// Does not remove key or decrease number of keys
	// @param key - key to search
	// @return value that is paired with provided key
	// @throw IllegalNullKeyException If key is null, 
	// @throw KeyNotFoundException(). If key is not found, 
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null)
			throw new IllegalNullKeyException();
		// locate index of linked list where node exists.
		int index = key.hashCode() % capacity;
		// if returned index out of bounds
		if (index < 0 || index >= arr.length)
			throw new KeyNotFoundException();
		@SuppressWarnings("unchecked")
		LinkedNodes<K, V> list = (LinkedNodes<K, V>) arr[index];
		// this fake node only contains key for searching purpose
		Node<K, V> fakeNode = new Node<K, V>(key, null, null);
		if (list.lookUp(fakeNode) != null) {
			// the lookup will return "real node" which contains the vaule
			return list.lookUp(fakeNode).getValue();
		}
		throw new KeyNotFoundException();
	}

	@Override
	// Returns the number of key,value pairs in the data structure
	// @return numKeys
	public int numKeys() {
		return numKeys;
	}

	@SuppressWarnings("unchecked")
	@Override
	// Returns the load factor threshold that was
	// passed into the constructor when creating
	// the instance of the HashTable.
	// When the current load factor is greater than or
	// equal to the specified load factor threshold,
	// the table is resized and elements are rehashed.
	// @return loadFactorThreshold
	public double getLoadFactorThreshold() {
		calculateLoadFactor(); // final update load factor
		if (getLoadFactor() >= loadFactorThreshold) {
			// resized + elements rehashed
			// REQUIRED: When the load factor threshold is reached,
			// the capacity must increase to: 2 * capacity + 1
			Object[] newArr = new Object[2 * getCapacity() + 1];
			for (int i = 0; i < newArr.length; i++) {
				newArr[i] = new LinkedNodes<K, V>();
			}
			capacity = newArr.length;
			calculateLoadFactor();
			for (int i = 0; i < arr.length; i++) {
				LinkedNodes<K, V> list = (LinkedNodes<K, V>) arr[i];
				if (list.getHead() != null) {// if there is at least one node exist at the index
					Node<K, V> currentNode = list.getHead();
					// locate new index in the new array
					int newIndex = currentNode.getKey().hashCode() % capacity;
					LinkedNodes<K, V> newList = (LinkedNodes<K, V>) newArr[newIndex];
					// creates a deep copy of the current node
					Node<K, V> fakeCurrentNode = new Node<K, V>(currentNode.getKey(), currentNode.getValue(), null);
					// rehash the node
					newList.insert(fakeCurrentNode);
					while (currentNode.getNext() != null) { // if there is more than 1 node in the list
						currentNode = currentNode.getNext();
						// locate new index in the new array
						newIndex = currentNode.getKey().hashCode() % capacity;
						newList = (LinkedNodes<K, V>) newArr[newIndex];
						// creates a deep copy of the current node
						fakeCurrentNode = new Node<K, V>(currentNode.getKey(), currentNode.getValue(), null);
						// rehash
						newList.insert(fakeCurrentNode);
					}
				}
			}
			arr = newArr; // change reference to new array
		}
		return loadFactorThreshold;
	}

	@Override
	// Returns the current load factor for this hash table
	// load factor = number of items / current table size
	// @return loadFactor
	public double getLoadFactor() {
		return loadFactor;
	}

	@Override
	// Return the current Capacity (table size)
	// of the hash table array.
	//
	// The initial capacity must be a positive integer, 1 or greater
	// and is specified in the constructor.
	//
	// REQUIRED: When the load factor threshold is reached,
	// the capacity must increase to: 2 * capacity + 1
	//
	// Once increased, the capacity never decreases
	// @return capacity
	public int getCapacity() {
		return capacity;
	}

	@Override
	// Returns the collision resolution scheme used for this hash table.
	// Implement with one of the following collision resolution strategies.
	// Define this method to return an integer to indicate which strategy.
	//
	// 1 OPEN ADDRESSING: linear probe
	// 2 OPEN ADDRESSING: quadratic probe
	// 3 OPEN ADDRESSING: double hashing
	// 4 CHAINED BUCKET: array of arrays
	// 5 CHAINED BUCKET: array of linked nodes(My strategy)
	// 6 CHAINED BUCKET: array of search trees
	// 7 CHAINED BUCKET: linked nodes of arrays
	// 8 CHAINED BUCKET: linked nodes of linked node
	// 9 CHAINED BUCKET: linked nodes of search trees
	public int getCollisionResolution() {
		return 5;
	}

	/**
	 * Calculates new loadFactor = numKeys / capacity
	 * @return updated loadFactor
	 */
	private void calculateLoadFactor() {
		loadFactor = (double) numKeys / (double) capacity;
	}

	/**
	 * Print out hash table
	 */
	private void printHashTable() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("Index: " + i + "| ");
			@SuppressWarnings("unchecked")
			LinkedNodes<K, V> list = (LinkedNodes<K, V>) arr[i];
			list.printLinkedNodes();
			System.out.println();
		}
		System.out.println("LoadFactor: " + getLoadFactor());
		System.out.println("Capacity: " + getCapacity());
		System.out.println("Num: " + numKeys);
		System.out.println("_____________________________");
	}
}// end of HashTable class
