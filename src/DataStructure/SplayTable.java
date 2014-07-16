package DataStructure;

import java.util.TreeSet;

public class SplayTable {
	private BST[] table;
	private int size;
	private int num;
	private double loadf;
	public SplayTable(int size)
	{
		table = new BST[size];
		for(int i=0; i<size; i++)
		{
			table[i] = new BST();
		}
	}
	public int hash(int key)
	{
		return key % size;
	}
	public void add(int elem)
	{
		table[hash(elem)].add(elem);
		num++;
	}
	public boolean remove(int elem)
	{
		num--;
		return table[hash(elem)].remove(elem);
	}
	public void updateLF()
	{
		loadf = num/size;
	}
	public double getLoadFactor()
	{
		updateLF();
		return loadf;
	}
	private class BST
	{
		TreeSet<Integer> tree;
		public BST()
		{
			tree = new TreeSet<Integer>();
		}
		public void add(int e)
		{
			tree.add(e);
		}
		public boolean remove(int x)
		{
			return tree.remove(x);
		}
	}
}