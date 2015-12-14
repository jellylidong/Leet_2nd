/*Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.*/

/*my analysis
 * search from the top right corner, 
 * so initially x = len-1, y = 0
 * every time,  * if nums[y][x] < target
 * it means the last number of row y < target, so y++
 * if nums[y][x] > target, it means the first number of column x > target,
 * so x--
 * time complexity is O(m+n)*/
public class Problem240_Searcha2DMatrix_II {
	public boolean searchMatrix(int[][] m, int target) {
        int l = m[0].length;
        int h = m.length;
        
        int x = l-1;
        int y = 0;
        while(x >= 0 && y < h){
        	if(m[y][x] == target)
        		return true;
        	else if(m[y][x] < target)
        		y++;
        	else if(m[y][x] > target)
        		x--;
        }
        return false;
    }
}
