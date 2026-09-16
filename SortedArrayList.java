package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SortedArrayList<E> implements SortedList<E> {

	private E[] array;
	private int arraySize = 10; // starting size of 10
	private Comparator<? super E> cmp;

	@SuppressWarnings("unchecked")
	public SortedArrayList() {
		this.array = (E[]) new Object[this.arraySize];
		this.cmp = null; // natural ordering
	}

	@SuppressWarnings("unchecked")
	public SortedArrayList(Comparator<? super E> cmp) {
		this.array = (E[]) new Object[this.arraySize];
		this.cmp = cmp; // given ordering
	}

	@Override
	public void clear() {
		for (int i = 0; i < arraySize; i++) {
			this.array[i] = null; // setting each element to 0 to denote an empty array
		}
		this.arraySize = 0; // array size is 0
	}

	@Override
	public boolean contains(E element) {
		for (int i = 0; i < arraySize; i++) {
			if (this.array[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends E> items) {
		for (E item : items) {
			for (E element : this.array) {
				if (!(element.equals(item))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int countEntries(E target) {
		int count = 0;
		for (E element : this.array) {
			if (element.equals(target)) {
				count++;
			}
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(E element) 
	{
		if(binarySearch(element) == -1) 
		{
			
		}
			
		}
	}

	@Override
	public void insertAll(Collection<? extends E> coll) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E max() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E median() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public int binarySearch(E target) 
	{
		int low = 0;
		int high = this.arraySize - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (this.array[mid].equals(target)) {
				return mid; // Target found
			} else if (((Comparable<? super E>) this.array[mid]).compareTo(target) < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}

	@Override
	public E min() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
