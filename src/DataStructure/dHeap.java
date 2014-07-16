package DataStructure;

public class dHeap {
	/*
	 * Using 0 as root
	 */
	public int d;
	public int[] data;
	public int heapsize;
	
	public dHeap(int d)
	{
		this.d = d;
	}
	
	public int parent(int i)
	{
		return (i-1)/d;
	}
	
	/* Children k = 0 to d-1 */
	public int child(int i, int k)
	{
		return d*i + k + 1;
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
		for(int i=A.length/d; i>=0; i--)
			maxHeapify(A,i);
	}
	/*
	 * O(log(n)) time
	 */
	public void maxHeapify(int[] A, int i)
	{
		int largest = i;
		for(int j=0; j<d; j++)
		{
			int c = child(i,j);
			if(c<heapsize && A[c]>A[largest])
				largest = c;
		}
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
		return data[0];
	}
	
	public int removeMaximum(int[] A)
	{
		int max = data[0];
		data[0] = data[heapsize--];
		maxHeapify(A,0);
		return max;
	}
	
	public boolean checkHeap(int[] A, int i)
	{
		for(int j=0; j<d; j++)
		{
			int c = child(i,j);
			if(c<heapsize && !(checkHeap(A, c) && A[c] <= A[i]))
				return false;
		}
		return true;
	}
}