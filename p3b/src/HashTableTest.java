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

import static org.junit.Assert.fail;

import java.util.Random;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/** TODO: add class header comments here */
/**
 * Defines a the hash table test class. Implements black box test to see if the
 * implementation of HashTable works as expected
 * 
 * @author Nhut Ly (nmly@wisc.edu)
 */
// TODO: add imports as needed
public class HashTableTest {

	// TODO: add other fields that will be used by multiple tests
	protected HashTable<Integer, String> hashTable;

	// TODO: add code that runs before each test method
	@BeforeEach
	public void setUp() throws Exception {
		hashTable = new HashTable<Integer, String>();
	}

	// TODO: add code that runs after each test method
	@AfterEach
	public void tearDown() throws Exception {
		hashTable = new HashTable<Integer, String>();
	}

	/**
	 * Tests that a HashTable returns an integer code indicating which collision
	 * resolution strategy is used. REFER TO HashTableADT for valid collision scheme
	 * codes.
	 */
	@Test
	public void test000_collision_scheme() {
		HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>();
		int scheme = htIntegerKey.getCollisionResolution();
		if (scheme < 1 || scheme > 9)
			fail("collision resolution must be indicated with 1-9");
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that insert(null,null) throws
	 * IllegalNullKeyException
	 */
	@Test
	public void test001_IllegalNullKey() {
		try {
			HashTableADT<Integer, String> htIntegerKey = new HashTable<Integer, String>();
			htIntegerKey.insert(null, null);
			fail("should not be able to insert null key");
		} catch (IllegalNullKeyException e) {
			/* expected */ } catch (Exception e) {
			fail("insert null key should not throw exception " + e.getClass().getName());
		}
	}

	// TODO add your own tests of your implementation
	/**
	 * Inserts new key, then check if its value is equal to expected.
	 */
	@Test
	public void test002_InsertNewKeys() {
		try {
			HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
			hashTable.insert(1, "1");
			if (!hashTable.get(1).equals("1")) {
				fail("the actual value is not expected");
			}
			hashTable.insert(2, "2");
			if (!hashTable.get(2).equals("2")) {
				fail("the actual value is not expected");
			}
		} catch (IllegalNullKeyException e) {
			fail("Unexpected illegal null key exception is thrown");
		} catch (NullPointerException e1) {
			e1.printStackTrace();
			fail("Unexpected null pointer exception is thrown.");
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserts new key, then delete it. Then check if KeyNotFoundException is
	 * thrown.
	 */
	@Test
	public void test003_RemoveKeys() {
		try {
			HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
			hashTable.insert(100, "1");
			if (hashTable.remove(100) == false) {
				fail("failed to remove");
			}
			hashTable.get(100);
			fail("should not be able to find key that does not exist");
		} catch (IllegalNullKeyException e) {
			fail("Unexpected illegal null key exception is thrown");
		} catch (NullPointerException e1) {
			fail("Unexpected null pointer exception is thrown.");
		} catch (KeyNotFoundException e) {
			// expected
		}
	}

	/**
	 * Inserts new key, then delete it. For every insertion and removal, checks if
	 * size is updated as expected
	 */
	@Test
	public void test004_size() {
		try {
			HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
			hashTable.insert(1, "1");
			if (hashTable.numKeys() != 1) {
				fail("failed to update size");
			}
			hashTable.insert(2, "2");
			if (hashTable.numKeys() != 2) {
				fail("failed to update size");
			}
			hashTable.insert(2, "2.2");
			if (hashTable.numKeys() != 2) {
				fail("failed to update size");
			}
			// hashTable.printHashTable();
			hashTable.remove(1);
			if (hashTable.numKeys() != 1) {
				fail("failed to update size");
			}
			hashTable.remove(2);
			if (hashTable.numKeys() != 0) {
				fail("failed to update size");
			}
		} catch (IllegalNullKeyException e) {
			fail("Unexpected illegal null key exception is thrown");
		} catch (NullPointerException e1) {
			fail("Unexpected null pointer exception is thrown.");
		}
	}

	/**
	 * Inserts 3 new keys, that will end up in the same index of the array. Then
	 * check if they from a linked list.
	 */
	@Test
	public void test005_collision_handling() {
		try {
			HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
			hashTable.insert(1, "1");
			hashTable.insert(101, "101");
			hashTable.insert(1001, "1001");
		} catch (IllegalNullKeyException e) {
			fail("Unexpected illegal null key exception is thrown");
		} catch (NullPointerException e1) {
			fail("Unexpected null pointer exception is thrown.");
		}
	}

	/**
	 * Inserts 3 new keys, that will end up in the same index of the array. Then,
	 * delete them. Check if they are deleted as expected.
	 */
	@Test
	public void test006_delete_collision() {
		try {
			HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
			hashTable.insert(1, "1");
			hashTable.insert(101, "101");
			hashTable.insert(1001, "1001");
			hashTable.remove(101);
			hashTable.remove(1001);
			hashTable.remove(1);
		} catch (IllegalNullKeyException e) {
			fail("Unexpected illegal null key exception is thrown");
		} catch (NullPointerException e1) {
			fail("Unexpected null pointer exception is thrown.");
		}
	}

	/**
	 * Inserts 100 new keys. Check if they are inserted and resized as expected,
	 * without throwing any exception or error.
	 */
	@Test
	public void test007_insert_100keys() {
		try {
			HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
			for (int i = 0; i < 100; i++) {
				hashTable.insert(i, Integer.toString(i));
			}
		} catch (IllegalNullKeyException e) {
			fail("Unexpected illegal null key exception is thrown");
		} catch (NullPointerException e1) {
			e1.printStackTrace();
			fail("Unexpected null pointer exception is thrown.");
		}catch (Exception e2) {
			fail("Unexpected exception is thrown.");
		}
	}

	/**
	 * Inserts 99 new keys. Check if they are inserted and resized as expected. Then
	 * tried to find every key to see if the keys still exist after resizing and
	 * rehashing
	 */
	@Test
	public void test008_insert_99randomkeys_thenGet() {
		try {
			HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
			Random r = new Random();
			int[] ar = new int[100];
			int numAdd = 99;
			for (int i = 0; i < numAdd; i++) {
				int num = r.nextInt(1000);
				ar[i] = num;
				hashTable.insert(num, Integer.toString(num + r.nextInt(10)));
			}
			for (int i = 0; i < numAdd; i++) {
				hashTable.get(ar[i]);
			}
		} catch (IllegalNullKeyException e0) {
			fail("Unexpected illegal null key exception is thrown");
		} catch (NullPointerException e1) {
			e1.printStackTrace();
			fail("Unexpected null pointer exception is thrown.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}// end of HashTableTest class
