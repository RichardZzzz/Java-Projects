package Assignment2;
// Ruiqian Zhang G00948867

import java.util.Iterator;
import java.util.Stack;

public  class BST<T extends Comparable<T>> implements Iterable<T>
{
	private BSTNode<T> root;
	
	// Create new BST
	public BST()
	{
		root = null;
	}
	
	// empty method
	public boolean isEmpty ()
    {
        return (root == null);
    }
	
	// get root from BST
	public BSTNode<T> getRoot ()
    {
        return root;
    }
	
	// set new root
	public void setRoot (BSTNode<T> newRoot)
    {
        root = newRoot;
    }
	
	// search target 
	public T search (T target)
	{
		return search(target,root);
	}
	
	// search target in BST which BSTNode P as the root
	public T search (T target, BSTNode<T> P)
	{
		if (P==null) 
			return null;
		int comp = target.compareTo(P.data);
		if(comp==0)
			return P.data; // found it 
		if(comp<0)
			return search(target,P.left); // target less than P.data
		else
			return search(target,P.right); // target bigger than P.data
	}
	
	// add a new value to the tree
	public boolean insert(T value)
	{
		if (root==null)
		{
			root = new BSTNode<T> (value);
			return true;
		}
		return insert(value,root);
	}
	
	// add a new value into the BST which P as the root 
	public boolean insert(T value,BSTNode<T> P)
	{
		int comp = value.compareTo(P.data);
		if (comp == 0) // already in the BST
			return false;
		if (comp < 0) // value less than P.data
		{
			if (P.left==null)
			{
				P.left = new BSTNode<T> (value);// insert here
				return true;
			}
			else
				return insert(value,P.left); // keep going left
		}
		else // value bigger than P.data
		{
			if(P.right==null)
			{
				P.right = new BSTNode<T> (value);// insert here
				return true;
			}
			else
				return insert(value,P.right);// keep going right
		}

	}
	
	// delete target from BST
	public boolean delete(T target)
	{
		if (root==null)
			return false;
		return delete(target,root)!=null;
	}
	
	// delete target from BST which BSTNode P as the root
	public BSTNode<T> delete(T target, BSTNode<T> P)
	 {
		
	    if (search(target,P) == null) {
	        return null; 	// target isn't in the BST
	    }
	    
	    if (target==P.data) // find target
	    {
	    	
	    	if (P.left == null && P.right == null) // P has no children 
	    	{
	            return null; // delete P
	        }
	        if (P.left== null) // P has only right child
	        {
	            return P.right; // set P.right as the position of P
	        }
	        if (P.right == null) // P has only left child
	        {
	            return P.left; // set P.left as the position of P
	        }
	       
	        else // P has two children
	        {   
	        	BSTNode<T> leftMost = P.left;
	        	while(leftMost.right!=null)
		        {
		        	leftMost = leftMost.right;
		        }

		        P.setData(leftMost.data);
		        P.setLeft(delete(leftMost.data,P.left));
		        
		        return P; 
	        }
	    }
	    else if (target.compareTo(P.data) < 0) {
	        P.setLeft( delete(target,P.left) );
	        return P;
	    }
	    
	    else {
	        P.setRight( delete(target,P.right) );
	        return P;
	    }
	 }
	
	
	public Iterator<T> iterator() 
	{
		return new TreeIterator<T> ();
	}

	
	public class TreeIterator<T> implements Iterator<T>
	{
		private BSTNode<T> cursor;
		Stack<BSTNode<T>> stack;
		T savedCursor;
		 
		public TreeIterator ()
		{
			this.cursor = (BSTNode<T>) root;
			this.stack = new Stack<BSTNode<T>>();
			this.savedCursor = null;
		 }
		
		public boolean hasNext() 
		{
			return (cursor!=null||!stack.empty());
		}

		public T next()
		{
			
			while (cursor != null)
	        {
	            stack.push(cursor);
	            cursor = cursor.left;
	        }
	        cursor = stack.pop();
	        savedCursor = cursor.data;
	        cursor = cursor.right;
	        return savedCursor;
		}

		public void remove() 
		{
		}
	}
	
	public class BSTNode <T>
	{
		public T data;
	    public BSTNode<T> left;
	    public BSTNode<T> right;


	    public BSTNode (T newData)
	    {
	        data = newData;
	        left = null;
	        right = null;
	    }

	    public BSTNode ()
	    {
	        data = null;
	        left = null;
	        right = null;
	    }

	    public String toString ()
	    {
	        return data.toString ();
	    }

	    public void setData (T newData)
	    {
	        data = newData;
	    }

	    public T getData ()
	    {
	        return data;
	    }

	    public void setLeft (BSTNode<T> newLeft)
	    {
	        left = newLeft;
	    }

	    public void setRight (BSTNode<T> newRight)
	    {
	        right = newRight;
	    }

	    public int compareTo (BSTNode<T> theOther)
	    {
	    	return this.data.toString().compareTo(theOther.data.toString());
	    }
	}
}
