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

	@Override
	public void insert(E element) 
	{
		int index = binarySearch(element);
		if(this.array.length == this.arraySize) 
		{
			doubleCapacity();
		}
		for(int i = this.arraySize - 1; i > index; i--)
		{
			this.array[i] = this.array[i - 1];
		}
		this.array[index] = element;
	}

	@Override
	public void insertAll(Collection<? extends E> coll) 
	{
		for(E element : coll) 
		{
			insert(element);
		}
	}

	@Override
	public boolean isEmpty() 
	{
		boolean isTrue = true;
	for(E element : this.array) 
	{
		if(!(element.equals(null))) 
		{
			isTrue =  false;
		}
	}
	return isTrue;
	}

	@Override
	public E max() throws NoSuchElementException 
	{
		if(isEmpty()) 
		{
			throw new NoSuchElementException();
		}
		return array[array.length - 1];
	}

	@Override
	public E median() throws NoSuchElementException 
	{
		if(isEmpty()) 
		{
			throw new NoSuchElementException();
		}
		if(array.length%2 == 1) 
		{
			return array[(array.length / 2) + 1];
		}
		else 
		{
			if(cmp.compare(array[array.length/2], array[(array.length/2)+1]) <= 0) 
			{
				return array[(array.length/2 )+ 1];
			}
			else 
			{
				return array[(array.length/2 )];
			}
		}
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
		return low;
	}

	@Override
	public E min() throws NoSuchElementException {
		if(isEmpty()) 
		{
			throw new NoSuchElementException();
		}
		return array[0];
	}

	@Override
	public int size() {
		return this.arraySize;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[array.length];
		for(int i = 0 ; i < array.length; i++) 
		{
			result[i] = array[i];
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private void doubleCapacity() 
	{
		E[] largerArray = (E[]) new Object[array.length * 2];

		for(int i = 0; i < arraySize; i++) 
		{
			largerArray[i] = array[i];
		}
			array = largerArray;
			this.arraySize *= 2;
	}

}