package DataStructure;

public class BST {
	public Node search(Node root, int key)
	{
		if(root == null) return null;
		int val = root.value;
		if(key == val)
			return root;
		else if(key < root.value)
			return search(root.left, key);
		else search(root.right, key);
		return null;
	}
	public Node iterativeSearch(Node root, int key)
	{
		while(root != null && key != root.value)
		{
			if(key < root.value)
				root = root.left;
			else root = root.right;
		}
		if(root == null)
			return null;
		else
			return root;
	}
	public Node minimum(Node root)
	{
		while(root != null)
			root = root.left;
		return root;
	}
	public Node maximum(Node root)
	{
		while(root != null)
			root = root.right;
		return root;
	}
	public Node successor(Node root)
	{
		if(root.right != null)
			return minimum(root.right);
		Node p = root.p;
		while(p != null && p.right == root)
		{
			p = p.p;
			root = root.p;
		}
		return p;
	}
	public Node predecessor(Node root)
	{
		if(root.left != null)
			return maximum(root.left);
		Node p = root.p;
		while(p != null && p.left == root)
		{
			p = p.p;
			root = root.p;
		}
		return p;
	}
	public Node insert(Node root, int x)
	{
		Node y = null;
		while(root != null)
		{
			y = root;
			if(x < root.value)
				root = root.left;
			else
				root = root.right;
		}
		Node z = new Node(x);
		if(y == null)
			return z;

		z.p = y;
		if(x < y.value)
			y.left = z;
		else
			y.right = z;
		return y;
	}
	public void delete(Node root, Node x)
	{
		if(x == null) return;
		if(x.left == null)
			transplant(root,x,x.right);
		else if(x.right == null)
			transplant(root,x,x.left);
		else
		{
			Node y = successor(x);
			if(y.p != x)
			{
				transplant(root,y,y.right);
				y.right = x.right;
				y.right.p = y;
			}
			transplant(root,x,y);
			y.left = y.left;
			y.left.p = y;
		}
	}
	public Node transplant(Node root, Node u, Node v)
	{
		if(u.p == null)
			root = v;
		else if(u == u.p.left)
			u.p.left = v;
		else u.p.right = v;
		if(v != null)
			v.p = u.p;
		return root;
	}
	private class Node
	{
		Node left,right,p;
		int value;
		public Node(int value)
		{
			this.value = value;
			this.p = null;
			this.right = null;
			this.left = null;
		}	
	}
}
