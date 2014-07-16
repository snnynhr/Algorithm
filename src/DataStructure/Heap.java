package DataStructure;

public class Heap {
	public int[] data;
	public int heapsize;
	
	public int parent(int i)
	{
		return i/2;
	}
	
	public int left(int i)
	{
		return 2*i;
	}
	
	public int right(int i)
	{
		return 2*i+1;
	}
	
	/*
	 * O(n*log(n)) time
	 */
	public void heapSort(int[] A)
	{
		buildMaxHeap(A);
		for(int i=A.length; i>=2; i--)
		{
			int temp = A[1];
			A[1] = A[i];
			A[i] = temp;
			heapsize--;
			maxHeapify(A,1);
		}
	}
	
	/*
	 * O(n) time
	 */
	public void buildMaxHeap(int[] A)
	{
		heapsize = A.length;
		for(int i=A.length/2; i>0; i--)
			maxHeapify(A,i);
	}
	/*
	 * O(log(n)) time
	 */
	public void maxHeapify(int[] A, int i)
	{
		int l = 2*i;
		int r = 2*i+1;
		int largest;
		if(l<=heapsize && A[l]>A[i])
			largest = l;
		else largest = i;
		if(r<=heapsize && A[r]>A[i])
			largest = r;
		if(largest!=i)
		{
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			maxHeapify(A,largest);
		}
	}
	
	public int maximum()
	{
		return data[1];
	}
	
	public int removeMaximum(int[] A)
	{
		int max = data[1];
		data[1] = data[heapsize--];
		maxHeapify(A,1);
		return max;
	}
}