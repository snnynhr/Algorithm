package DataStructure;

/* true: red, false: black */

public class RedBlackTree {
	public void insert(Tree t, Node z)
	{
		Node x = t.root;
		Node y = null;
		while(x != null)
		{
			y = x;
			if(z.key < x.key)
				x = x.left;
			else x = x.right;
		}
		z.p = y;
		if(y == null)
			t.root = z;
		else if(z.key < y.key)
			y.left = z;
		else
			y.right = z;
		z.left = null;
		z.right = null;
		z.color = true;
		insertFix(t,z);
	}
	public void insertFix(Tree t, Node z)
	{
		
	}
	public void leftRotate(Tree t, Node x)
	{
		Node y = x.right;
		x.right = y.left;
		if(y.left != null)
			y.left.p = x;
		y.p = x.p;
		if(x.p == null)
			t.root = y;
		else if(x == x.p.left)
			x.p.left = y;
		else 
			x.p.right = y;
		y.left = x;
		x.p = y;
		return;
	}
	public void rightRotate(Tree t, Node x)
	{
		Node y = x.left;
		x.left = y.right;
		if(y.right != null)
			y.right.p = x;
		y.p = x.p;
		if(x.p == null)
			t.root = y;
		else if(x == x.p.right)
			x.p.right = y;
		else 
			x.p.left = y;
		y.right = x;
		x.p = y;
		return;
	}
	private class Tree
	{
		Node root;
	}
	private class Node
	{
		boolean color;
		Node left,right,p;
		int key;
		public Node(int key)
		{
			this.key = key;
		}
	}
}
