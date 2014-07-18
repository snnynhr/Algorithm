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
		Node p = root.parent;
		while(p != null && p.right == root)
		{
			p = p.parent;
			root = root.parent;
		}
		return p;
	}
	public Node predecessor(Node root)
	{
		if(root.left != null)
			return maximum(root.left);
		Node p = root.parent;
		while(p != null && p.left == root)
		{
			p = p.parent;
			root = root.parent;
		}
		return p;
	}
	public Node insert(Node root, Node x)
	{
		if(root == null) return x;
		if(x.value < root.value)
		{
			
		}
	}
	private class Node
	{
		Node left,right,parent;
		int value;
		public Node()
		{
			this.parent = null;
			this.right = null;
			this.left = null;
		}	
	}
}
