package Assignment2;

public class Test1 {

	public static void main(String[] args) 
	{
		
		
		/*Set<Integer>[] aSets = (Set<Integer>[]) new Set[4];
	      // assign set values to first two array elements
	      Integer[] a0 = {1, 2, 3};
	      Integer[] a1 = {2, 4, 6, 8};
	      Integer[] a2 = {1};
	      Integer[] a3 = {1};
	      aSets[0] = new Set(a0);
	      aSets[1] = new Set(a1);
	      aSets[2] = new Set(a2);
	      aSets[3] = new Set(a3);
	      
	  	System.out.println("aSets[0]:"+aSets[0]);
	  	System.out.println("aSets[1]:"+aSets[1]);
	  	System.out.println("aSets[2]:"+aSets[2]);
	  	System.out.println("aSets[3]:"+aSets[3]);
	  	System.out.println(aSets[3].subsetOf(aSets[2]));
	  	*/
		Set<Integer>[] aSets = (Set<Integer>[]) new Set[4];
	      // assign set values to first two array elements
	      Integer[] a1 = {1, 2, 3};
	      Integer[] a2 = {2, 4, 6, 8};
	      Integer[] a3, a4;
	      aSets[0] = new Set(a1);
	      aSets[1] = new Set(a2);
	      // perform set operations on the sets
	      aSets[2] = aSets[0].union(aSets[1]);
	      aSets[3] = aSets[0].intersection(aSets[1]);
	      for (int i = 0; i < 4; i++)
	         System.out.println(aSets[i]);
	      // repeat with sets of sets of integers
	      Set<Set<Integer>>[] aSetsOfSets = (Set<Set<Integer>>[]) new Set[4];
	      for (int i = 0; i < 4; i++)
	         aSetsOfSets[i] = new Set<Set<Integer>>();
	      aSetsOfSets[0].insert(aSets[0]);
	      aSetsOfSets[0].insert(aSets[1]);
	      aSetsOfSets[0].insert(aSets[2]);
	      aSetsOfSets[1].insert(aSets[2]);
	      aSetsOfSets[1].insert(aSets[3]);
	      aSetsOfSets[2] = aSetsOfSets[0].union(aSetsOfSets[1]);
	      aSetsOfSets[3] = aSetsOfSets[0].intersection(aSetsOfSets[1]);
	      for (int i = 0; i < 4; i++)
	         System.out.println(aSetsOfSets[i]);
	      
	      System.out.println(aSets[0].equals(aSets[0]));
	}

}
