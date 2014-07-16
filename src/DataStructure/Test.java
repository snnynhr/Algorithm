package DataStructure;

import java.util.Arrays;

public class Test {
	public static void main(String args[])
	{
		dHeap x = new dHeap(3);
		int[] A = {4,0};
		x.buildMaxHeap(A);
		System.out.println(Arrays.toString(A));
		System.out.println(x.checkHeap(A,0));
	}
}
