package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortedArrayListTest {

	private SortedArrayList<Integer> list;

	@BeforeEach
	public void setUp() {
		list = new SortedArrayList<Integer>();
	}

	@Test
	public void testNewListIsEmpty() {
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}

	@Test
	public void testInsertOneElement() {
		list.insert(5);

		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
		assertTrue(list.contains(5));
	}

	@Test
	public void testInsertMaintainsSortedOrder() {
		list.insert(8);
		list.insert(2);
		list.insert(10);
		list.insert(5);

		Object[] expected = { 2, 5, 8, 10 };

		assertArrayEquals(expected, list.toArray());
	}

	@Test
	public void testInsertDuplicateElements() {
		list.insert(4);
		list.insert(2);
		list.insert(4);
		list.insert(4);

		Object[] expected = { 2, 4, 4, 4 };

		assertArrayEquals(expected, list.toArray());
		assertEquals(3, list.countEntries(4));
	}

	@Test
	public void testInsertAll() {
		list.insertAll(Arrays.asList(7, 3, 9, 1, 5));

		Object[] expected = { 1, 3, 5, 7, 9 };

		assertArrayEquals(expected, list.toArray());
		assertEquals(5, list.size());
	}

	@Test
	public void testContainsExistingElement() {
		list.insertAll(Arrays.asList(2, 4, 6, 8));

		assertTrue(list.contains(6));
	}

	@Test
	public void testContainsMissingElement() {
		list.insertAll(Arrays.asList(2, 4, 6, 8));

		assertFalse(list.contains(5));
	}

	@Test
	public void testContainsElementSmallerThanMinimum() {
		list.insertAll(Arrays.asList(2, 4, 6, 8));

		assertFalse(list.contains(1));
	}

	@Test
	public void testContainsElementLargerThanMaximum() {
		list.insertAll(Arrays.asList(2, 4, 6, 8));

		assertFalse(list.contains(10));
	}

	@Test
	public void testContainsAllReturnsTrue() {
		list.insertAll(Arrays.asList(1, 2, 3, 4, 5));

		assertTrue(list.containsAll(Arrays.asList(2, 4, 5)));
	}

	@Test
	public void testContainsAllReturnsFalse() {
		list.insertAll(Arrays.asList(1, 2, 3, 4, 5));

		assertFalse(list.containsAll(Arrays.asList(2, 4, 10)));
	}

	@Test
	public void testContainsAllEmptyCollection() {
		list.insertAll(Arrays.asList(1, 2, 3));

		assertTrue(list.containsAll(Collections.emptyList()));
	}

	@Test
	public void testCountEntriesWhenTargetExists() {
		list.insertAll(Arrays.asList(2, 5, 2, 7, 2, 9));

		assertEquals(3, list.countEntries(2));
	}

	@Test
	public void testCountEntriesWhenTargetDoesNotExist() {
		list.insertAll(Arrays.asList(2, 5, 7, 9));

		assertEquals(0, list.countEntries(4));
	}

	@Test
	public void testMinimum() {
		list.insertAll(Arrays.asList(8, 3, 12, 1));

		assertEquals(1, list.min());
	}

	@Test
	public void testMaximum() {
		list.insertAll(Arrays.asList(8, 3, 12, 1));

		assertEquals(12, list.max());
	}

	@Test
	public void testMedianWithOddSize() {
		list.insertAll(Arrays.asList(5, 1, 9, 3, 7));

		// Sorted order: [1, 3, 5, 7, 9]
		assertEquals(5, list.median());
	}

	@Test
	public void testMedianWithEvenSize() {
		list.insertAll(Arrays.asList(8, 2, 6, 4));

		// Sorted order: [2, 4, 6, 8]
		// This test assumes the upper-middle element is required.
		assertEquals(6, list.median());
	}

	@Test
	public void testClear() {
		list.insertAll(Arrays.asList(1, 2, 3, 4));

		list.clear();

		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		assertArrayEquals(new Object[] {}, list.toArray());
	}

	@Test
	public void testMinThrowsExceptionWhenEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			list.min();
		});
	}

	@Test
	public void testMaxThrowsExceptionWhenEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			list.max();
		});
	}

	@Test
	public void testMedianThrowsExceptionWhenEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			list.median();
		});
	}

	@Test
	public void testArrayResizes() {
		for (int i = 20; i >= 1; i--) {
			list.insert(i);
		}

		assertEquals(20, list.size());

		for (int i = 1; i <= 20; i++) {
			assertTrue(list.contains(i));
		}

		Object[] result = list.toArray();

		for (int i = 0; i < 20; i++) {
			assertEquals(i + 1, result[i]);
		}
	}

	@Test
	public void testStringList() {
		SortedArrayList<String> words = new SortedArrayList<String>();

		words.insert("pear");
		words.insert("apple");
		words.insert("orange");
		words.insert("banana");

		Object[] expected = {
				"apple", "banana", "orange", "pear"
		};

		assertArrayEquals(expected, words.toArray());
	}

	@Test
	public void testReverseComparator() {
		Comparator<Integer> reverseComparator =
				(a, b) -> b.compareTo(a);

		SortedArrayList<Integer> reverseList =
				new SortedArrayList<Integer>(reverseComparator);

		reverseList.insertAll(Arrays.asList(3, 8, 1, 5));

		Object[] expected = { 8, 5, 3, 1 };

		assertArrayEquals(expected, reverseList.toArray());
		assertEquals(8, reverseList.min());
		assertEquals(1, reverseList.max());
	}
}