
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Algo x = new Algo();
		int grid[][] = {{1,3,1},{1,5,1},{4,2,1}};
		
		System.out.println(x.uniquePaths(4, 5));
		System.out.println(x.minPathSum(grid));
	}

}
