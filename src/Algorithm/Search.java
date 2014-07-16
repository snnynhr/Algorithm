package Algorithm;

public class Search {
	public static int binarySearch(int key, int[] a)
	{ 
		// Array must be sorted.
		int l = 0;
		int h = a.length - 1;
		while (l <= h)
		{ 
			int m = l+(h-l)/2;
			if (key < a[m]) h = m- 1;
			else if (key > a[m]) l = m + 1;
			else return m;
		}
		return -1;
	}
}
