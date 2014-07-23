package DataStructure;

/* true: red, false: black */

public class RedBlackTree {
	public boolean RED = true;
	public boolean BLACK = false;
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
		z.color = RED;
		insertFix(t,z);
	}
	public void insertFix(Tree t, Node z)
	{
		while(z.p.color == RED)
		{
			if(z.p == z.p.p.left)
			{
				Node y = z.p.p.right;
				if(y.color == RED)
				{
					z.p.color = BLACK;
					y.color = BLACK;
					z.p.p.color = RED;
					z = z.p.p;
				}
				else
				{
					if(z == z.p.right)
					{
						z = z.p;
						leftRotate(t, z);
					}
					z.p.color = BLACK;
					z.p.p.color = RED;
					rightRotate(t, z.p.p);
				}
			}
			else
			{
				Node y = z.p.p.left;
				if(y.color == RED)
				{
					z.p.color = BLACK;
					y.color = BLACK;
					z.p.p.color = RED;
					z = z.p.p;
				}
				else
				{
					if(z == z.p.left)
					{
						z = z.p;
						rightRotate(t, z);
					}
					z.p.color = BLACK;
					z.p.p.color = RED;
					leftRotate(t, z.p.p);
				}
			}
		}
		t.root.color = BLACK;
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
	public void transplant(Tree t, Node u, Node v)
	{
		if(u.p == null)
			t.root = v;
		else if (u == u.p.left)
			u.p.left = v;
		else u.p.right = v;
		v.p = u.p;
	}
	public void delete(Tree t, Node z)
	{
		Node x = null;
		Node y = z;
		boolean yOld = y.color;
		if(z.left == null)
		{
			x = z.right;
			transplant(t, z, z.right);
		}
		else if(z.right == null)
		{
			x = z.left;
			transplant(t,z,z.left);
		}
		else
		{
			y = minimum(z.right);
			yOld = y.color;
			x = y.right;
			if(y.p == z)
				x.p = y;
			else
			{
				transplant(t, y, y.right);
			}
			transplant(t, z, y);
			y.left = z.left;
			y.left.p = y;
			y.color = z.color;

		}
		if(yOld == BLACK)
			deleteFixup(t,x);
	}
	public void deleteFixup(Tree t, Node x)
	{
		while(x != t.root && x.color == BLACK)
		{
			if(x == x.p.left)
			{
				Node w = x.p.right;
				if(w.color == RED)
				{
					w.color = BLACK;
					x.p.color = RED;
					leftRotate(t, x.p);
					w = x.p.right;
				}
				if(w.left.color == BLACK && w.right.color == BLACK)
				{	
					w.color = RED;
					x = x.p;
				}
				else
				{
					if(w.right.color == BLACK)
					{
						w.left.color = BLACK;
						w.color = RED;
						rightRotate(t, w);
						w = x.p.right;
					}
					w.color = x.p.color;
					x.p.color = BLACK;
					w.right.color = BLACK;
					leftRotate(t, x.p);
					x = t.root;
				}
			}
			else
			{
				Node w = x.p.left;
				if(w.color == RED)
				{
					w.color = BLACK;
					x.p.color = RED;
					rightRotate(t, x.p);
					w = x.p.left;
				}
				if(w.right.color == BLACK && w.left.color == BLACK)
				{	
					w.color = RED;
					x = x.p;
				}
				else
				{
					if(w.left.color == BLACK)
					{
						w.right.color = BLACK;
						w.color = RED;
						leftRotate(t, w);
						w = x.p.left;
					}
					w.color = x.p.color;
					x.p.color = BLACK;
					w.left.color = BLACK;
					rightRotate(t, x.p);
					x = t.root;
				}
			}
		}
		x.color = BLACK;
	}
	public Node minimum(Node root)
	{
		while(root != null)
			root = root.left;
		return root;
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
