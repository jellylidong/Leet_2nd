/*Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.*/
public class Problem308_RangeSumQuery2D_Mutable {
	public class NumMatrix {
	    int nums[][];
	    int tree[][];
	    int x, y;
	    public NumMatrix(int[][] m) {
	        if(m.length == 0 || m[0].length == 0)
	            return;
	        this.nums = new int[m.length][m[0].length];
	        this.tree = new int[m.length+1][m[0].length+1];
	        for(int i = 0; i < nums.length; i++){
	            for(int j = 0; j < nums[0].length; j++)
	                update(i, j, m[i][j]);
	        }
	        // System.out.println("new");
	    }

	    public void update(int row, int col, int val) {
	        int delta = val - nums[row][col];
	        nums[row][col] = val;
	        for(int i = row+1; i < tree.length; i += i&(-i)){
	            for(int j = col+1; j < tree[0].length; j += j&(-j))
	                tree[i][j] += delta;
	        }
	        // System.out.println("update");
	    }

	    public int sumRegion(int row1, int col1, int row2, int col2) {
	        return sum(row2, col2) + sum(row1-1, col1-1) - sum(row2, col1-1) - sum(row1-1, col2);
	    }
	    
	    private int sum(int row, int col){
	        int sum = 0;
	        for(int i = row+1; i > 0; i -= i&(-i)){
	            for(int j = col+1; j > 0; j -= j&(-j))
	                sum += tree[i][j];
	        }
	        // System.out.println("sum");
	        return sum;
	    }
	}
}
