package Assignment2;
//Ruiqian Zhang G00948867

import java.util.Iterator;

public class Set<T extends Comparable<T>> implements Iterable<T>, Comparable <Set<T>>
{
	
	private BST<T> tree = new BST<T> ();
	public Set ()
	{
		
	}
	
	public Set(T[] setElements)
	{
		for (int i=0; i<setElements.length;i++)
			tree.insert(setElements[i]);
	}

	public boolean elementOf(T t)
	{
		return tree.search(t)!=null;
	}
	
	public Set<T> copy()
	{
		Set<T> copySet = new Set<T> ();
		Iterator<T> iter = iterator();
		while (iter.hasNext())
			copySet.insert(iter.next());
		
		return copySet;
	}
	
	public void insert (T t)
	{
		tree.insert(t);
	}
	
	public T search (T t)
	{
		return tree.search(t);
	}
	
	public boolean delete (T t)
	{
		return tree.delete(t);
	}
	
	public Set<T> union(Set<T> s)
	{
		Set<T> retSet = s.copy();
		Iterator<T> iter = iterator();
		while (iter.hasNext())
			retSet.insert(iter.next());
		return retSet;
	}
	
	public Set<T> intersection(Set<T> s)
	{
		Set<T> retSet = new Set<T> ();
		Iterator<T> iter = iterator();
		T temp ;
		while (iter.hasNext())
		{
			temp = s.search(iter.next());
			if(temp!=null)
				retSet.insert(temp);
		}
		return retSet;
	}
	
	public boolean subsetOf(Set<T> s)
	{
		T temp ;
		Iterator<T> iter = iterator();
		while (iter.hasNext())
		{
			temp = s.search(iter.next());
			if (temp==null)
				return false;
			else
				;
		}
		return true;
	}
	
	public Iterator<T> iterator ()
	{
		return tree.iterator();
	}
	
	public String toString ()
	{
		StringBuilder outString = new StringBuilder ();
		Iterator<T> iter = iterator();
		outString.append("{");
		while (iter.hasNext())
		{
			outString.append(iter.next()+",");
		}
		if(outString.length()>=2)
			outString.deleteCharAt(outString.length()-1);
		outString.append("}");
		return outString.toString();
	}

	public int compareTo (Set<T> s)
	{
		int first = this.findEleNum();
		int second = s.findEleNum();
		
		return (first-second);
	}
	
	public int findEleNum ()
	{
		int i=0;
		Iterator<T> iter = iterator();
		while (iter.hasNext())
		{
			iter.next();
			i++;
		}
		return i;
	}
}

