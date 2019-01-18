import java.util.Arrays;

public class Algo {

	public Algo(){
		
	}
// ********************************************************************************************************************
//	Given a m x n grid filled with non-negative numbers, 
//	find a path from top left to bottom right which minimizes the sum of all numbers along its path.
//
//	Note: You can only move either down or right at any point in time.
//
//	Example:
//
//	Input:
//	[
//	  [1,3,1],
//	  [1,5,1],
//	  [4,2,1]
//	]
//	Output: 7
//	Explanation: Because the path 1→3→1→1→1 minimizes the sum.
	public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i=1; i<n; i++)
            dp[0][i] = dp[0][i-1] + grid[0][i];
        for(int i=1; i<m; i++)
            dp[i][0] = dp[i-1][0] + grid[i][0];
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(dp[i-1][j] > dp[i][j-1])
                    dp[i][j] = grid[i][j]+dp[i][j-1];
                else
                    dp[i][j] = grid[i][j]+dp[i-1][j];
            }
        }
        
        return dp[m-1][n-1];
    }
	
// ********************************************************************************************************************
//	Given a non-empty array of integers, every element appears twice except for one. Find that single one.
//	Example 1:
//
//	Input: [2,2,1]
//	Output: 1
//	Example 2:
//
//	Input: [4,1,2,1,2]
//	Output: 4
	public int singleNumberI(int[] nums) {
        int ret = 0;
        for(int i : nums){
            ret ^= i;
        }
        
        return ret;
    }
	
// ********************************************************************************************************************
//	Given a non-empty array of integers, every element appears three times except for one, 
//	which appears exactly once. Find that single one.
//	Example 1:
//
//		Input: [2,2,3,2]
//		Output: 3
//	
//	Example 2:
//
//		Input: [0,1,0,1,0,1,99]
//		Output: 99

	public int singleNumberII(int [] nums){
	    int ones = 0, twos = 0;
	    for(int i = 0; i < nums.length; i++){
	        ones = (ones ^ nums[i]) & ~twos;
	        twos = (twos ^ nums[i]) & ~ones;
	    }
	    return ones;
	}
	
	
// ********************************************************************************************************************
//	A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
//	The robot can only move either down or right at any point in time. 
//	The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
//	How many possible unique paths are there?
	public int uniquePaths(int m, int n) {
        int[][] dp = new int [m][n];
        for(int i=0;i<m;i++)
            dp[i][0]=1;
        for(int j=0;j<n;j++)
            dp[0][j]=1;
        for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
                
        return dp[m-1][n-1];        
    }

// ********************************************************************************************************************
//	Given an array with n objects colored red, white or blue, 
//	sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
//
//	Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
//
//	Example:
//
//	Input: [2,0,2,1,1,0]
//	Output: [0,0,1,1,2,2]	
	public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0)
                count0++;
            else if(nums[i]==1)
                count1++;
            else
                count2++;
        }
        int index = 0;
        for(int i=0;i<count0;i++){
            nums[index] = 0;
            index++;
        }
        for(int i=0;i<count1;i++){
            nums[index] = 1;
            index++;
        }
        for(int i=0;i<count2;i++){
            nums[index] = 2;
            index++;
        }
    }
// ********************************************************************************************************************	
//	Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
//
//	Return 0 if the array contains less than 2 elements.
//
//	Example 1:
//
//	Input: [3,6,9,1]
//	Output: 3
//	Explanation: The sorted form of the array is [1,3,6,9], either
//	             (3,6) or (6,9) has the maximum difference 3.
//	Example 2:
//
//	Input: [10]
//	Output: 0
//	Explanation: The array contains less than 2 elements, therefore return 0.
	public int maximumGap(int[] nums) {
        if(nums.length == 0 || nums.length == 1)
            return 0;
        Arrays.sort(nums);
        
        int max = 0;
        for(int i=1;i<nums.length;i++){
            if(abs(nums[i-1]-nums[i]) > max)
                max = abs(nums[i-1]-nums[i]);
        }
        return max;
    }
    
    private int abs(int n){
        if(n >= 0)
            return n;
        else
            return n*(-1);
    }
    
// ********************************************************************************************************************	   
//    A peak element is an element that is greater than its neighbors.
//
//    Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
//    The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
//    Example 1:
//
//    Input: nums = [1,2,3,1]
//    Output: 2
//    Explanation: 3 is a peak element and your function should return the index number 2.
//    Example 2:
//
//    Input: nums = [1,2,1,3,5,6,4]
//    Output: 1 or 5 
//    Explanation: Your function can return either index number 1 where the peak element is 2, 
//                 or index number 5 where the peak element is 6.
    public int findPeakElement(int[] nums) {
        int length = nums.length;
        if(length==1)
            return 0;
        else{
            if(nums[0]>nums[1])
                return 0;
            for(int i=1;i<length;i++){
            if(i+1==length)
                return i;
            else{
                if((nums[i-1]<nums[i])&&(nums[i]>nums[i+1]))
                    return i;
            }
        }
        }
    
        return length-1;
    }
    
// ********************************************************************************************************************	   
/*
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.

	Example 1:
	
	Input:
	11110
	11010
	11000
	00000
	
	Output: 1
	Example 2:
	
	Input:
	11000
	11000
	00100
	00011
	
	Output: 3   
 */
    public int numIslands(char[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    DFScheck(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void DFScheck(char[][] grid, int i, int j){
        if(i>=0&&j>=0&&i<grid.length&&j<grid[0].length&&grid[i][j]=='1'){
            grid[i][j] = '0';
            DFScheck(grid,i+1,j);
            DFScheck(grid,i-1,j);
            DFScheck(grid,i,j+1);
            DFScheck(grid,i,j-1);
        }
    }
}
