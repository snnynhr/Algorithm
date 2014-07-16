package Algorithm;

public class Sort {
	//O(n^2) stable
	public static void linkedInsertionSort(int[] arr, int[] link) {
		int i, j, curr,c;
		for (i = 1; i < arr.length; i++) 
		{
			curr = arr[i];
			c = link[i];
			j = i;
			while (j > 0 && arr[j - 1] > curr) 
			{
				arr[j] = arr[j - 1];
				link[j]=link[j-1];
				j--;
			}
			arr[j] = curr;
			link[j]= c;
		}
	}
	public static void mergeSort(int array[],int lo, int n){
		int low = lo;
		int high = n;
		if (low >= high) {
			return;
		}
		int middle = (low + high) / 2;
		mergeSort(array, low, middle);
		mergeSort(array, middle + 1, high);
		int end_low = middle;
		int start_high = middle + 1;
		while ((lo <= end_low) && (start_high <= high)) {
			if (array[low] < array[start_high]) {
				low++;
			} else {
				int Temp = array[start_high];
				for (int k = start_high- 1; k >= low; k--) {
					array[k+1] = array[k];
				}
				array[low] = Temp;
				low++;
				end_low++;
				start_high++;
			}
		}
	}
	// LSD radix sort
    public static void LSDsort(String[] a) {
    	int W = 0;
    	for(int i=0; i<a.length; i++)
    		W = Math.max(W,a[i].length());
    	StringBuilder s  = new StringBuilder(W);
    	for(int i=0; i<W; i++) s.append(' ');
    	for(int i=0; i<a.length; i++)
    		a[i] = a[i]+s.subSequence(0, W-a[i].length());
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int d = W-1; d >= 0; d--) {
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d) + 1]++;
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];
            for (int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }
    private static final int R      = 256;   // extended ASCII alphabet size
    private static final int CUTOFF =  15;   // cutoff to insertion sort

    // sort array of strings
    public static void MSDsort(String[] a) {
        int N = a.length;
        String[] aux = new String[N];
        sort(a, 0, N-1, 0, aux);
    }

    // return dth character of s, -1 if d = length of string
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }
    // sort from a[lo] to a[hi], starting at the dth character
    private static void sort(String[] a, int lo, int hi, int d, String[] aux) {
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c+2]++;
        }
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c+1]++] = a[i];
        }
        for (int i = lo; i <= hi; i++) 
            a[i] = aux[i - lo];
        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }


    // return dth character of s, -1 if d = length of string
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    private static boolean less(String v, String w, int d) {
        assert v.substring(0, d).equals(w.substring(0, d));
        return v.substring(d).compareTo(w.substring(d)) < 0; 
    }
    // sort the array a[] of strings
    public static void Quick3sort(String[] a) {
        // StdRandom.shuffle(a);
        Quick3sort(a, 0, a.length-1, 0);
        assert isSorted(a);
    }
    // 3-way string quicksort a[lo..hi] starting at dth character
    private static void Quick3sort(String[] a, int lo, int hi, int d) { 

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if      (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        Quick3sort(a, lo, lt-1, d);
        if (v >= 0) Quick3sort(a, lt, gt, d+1);
        Quick3sort(a, gt+1, hi, d);
    }
    // is the array sorted
    private static boolean isSorted(String[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i-1]) < 0) return false;
        return true;
    }
}
