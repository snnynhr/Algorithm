package Algorithm;

public class kSelection {
	public int randomizedkSelect(int[] A, int p, int q, int i)
	{
		if(p == q)
			return A[p];
		int m = randomizedPartition(A, p, q);
		int k = m - p + 1;
		if(i == k)
			return A[k];
		if(i < k)
			randomizedkSelect(A, p, m - 1, i);
		else
			randomizedkSelect(A, m + 1, q, i - k);
		return -1;
	}
	public int randomizedPartition(int[] A, int p, int q)
	{
		int m = p + ((int)((q-p+1)*(Math.random())));
		int temp = A[q];
		A[q] = A[m];
		A[m] = temp;
		
		int x = A[q];
		int i = p - 1;
		for(int j = p; j<q; j++)
		{
			if(A[j] <= x)
			{
				i = i++;
				temp = A[j];
				A[j] = A[i];
				A[i] = temp;
			}
		}
		temp = A[i+1];
		A[i+1] = A[q];
		A[q] = temp;
		return i + 1;
	}
}
