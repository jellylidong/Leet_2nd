/*Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.*/

/*my analysis
 * Don't treat it as a 2D array, treat it as sorted list*/
public class Problem74_Searcha2DMatrix {
	 public boolean searchMatrix(int[][] m, int target) {
	        int l = m[0].length;
	        int h = m.length;
	        
	        int lo = 0;
	        int hi = l*h-1;
	        while(lo <= hi){
	            int mid = lo + (hi-lo)/2;
	            int y = mid/l;
	            int x = mid-y*l;
	            if(m[y][x] == target)
	                return true;
	            else if(m[y][x] < target)
	                lo = mid+1;
	            else
	                hi = mid-1;
	        }
	        return false;
	    }
}
