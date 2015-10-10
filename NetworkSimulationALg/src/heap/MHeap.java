package heap;
import java.util.Arrays;

public class MHeap <T extends Comparable<T>> {

	private static final int DEFAULT_CAPACITY = 50000;
	protected T[] array;
	protected int size;

	@SuppressWarnings("unchecked")
	public MHeap() {
		array = (T[])new Comparable[DEFAULT_CAPACITY];  
		size = 0;
	}

	public void add(T value) {
		if (size >= array.length - 1) {
			array = this.resize();
		}        
		size++;
		int index = size;
		array[index] = value;

		bubbleUp();
	}

	public boolean isEmpty() {
		return size == 0;
	}


	public T peek() {
		if (this.isEmpty()) {
			throw new IllegalStateException();
		}
		return array[1];
	}

	public boolean removeType(T t){
		for(int i=1 ;i<= size; i++){
			if(((EdgeType)array[i]).getH().equals(((EdgeType)t).getH())){
				swap(i,1);
				remove();
			}
		}
		return true;
	}


	public T remove() {
		T result = peek();
		array[1] = array[size];
		array[size] = null;
		size--;
		bubbleDown();
		return result;
	}

	public String toString() {
		return Arrays.toString(array);
	}

	protected void bubbleDown() {
		int index = 1;

		while (hasLeftChild(index)) {
			int smallerChild = leftIndex(index);
			if (hasRightChild(index)
					&& array[leftIndex(index)].compareTo(array[rightIndex(index)]) < 0) {
				smallerChild = rightIndex(index);
			} 

			if (array[index].compareTo(array[smallerChild]) < 0) {
				swap(index, smallerChild);
			} else {
				break;
			}
			index = smallerChild;
		}        
	}

	protected void bubbleUp() {
		int index = this.size;

		while (hasParent(index)
				&& (parent(index).compareTo(array[index]) < 0)) {
			swap(index, parentIndex(index));
			index = parentIndex(index);
		}        
	}

	protected boolean hasParent(int i) {
		return i > 1;
	}

	protected int leftIndex(int i) {
		return i * 2;
	}

	protected int rightIndex(int i) {
		return i * 2 + 1;
	}

	protected boolean hasLeftChild(int i) {
		return leftIndex(i) <= size;
	}

	protected boolean hasRightChild(int i) {
		return rightIndex(i) <= size;
	}

	protected T parent(int i) {
		return array[parentIndex(i)];
	}

	protected int parentIndex(int i) {
		return i / 2;
	}

	protected T[] resize() {
		return Arrays.copyOf(array, array.length * 2);
	}
	public int getSize(){
		return size;
	}
	protected void swap(int index1, int index2) {
		T tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;        
	}
}
