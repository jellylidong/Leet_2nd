/*Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?*/

/*my analysis
 * this is a very tricky question
 * the basic idea is every time we find
 * nums[i][j] == 9, we set 
 * nums[i][0] = 0
 * nums[0][j] = 0
 * then we go from nums[height-1][length-1] to nums[0][0]
 * if(nums[i][0] == 0 or nums[0][j] == 0
 * then nums[i][j] = 0; 
 * 
 * the most tricky part is, since we use colume 0 as labels,
 * we should not change their values (ie nums[x][0]) before we go through
 * nums[x][1..length-1]
 * or we will not be able to judge according to if(nums[i][0] == 0 or nums[0][j] == 0
 * the solution is, we set a col0 = 1 initially, if nums[x][0] == 0, 
 * this mean the whole colume 0 should be set 0, so, let col0 = 0 
 * so that we can know we should set nums[x][0] = 0 after going through nums[x][1..length-1]*/

public class Problem73_SetMatrixZeroes {
	public void setZeros(int[][] nums){
		int col0 = 1;
		for(int i = 0 ; i < nums.length; i++){
			if(nums[i][0] == 0)
				col0 = 0; // find nums[x][0] == 0, so we should set colume 0 to 0, 
						  //ie other nums[x][0] = 0 after going through nums[x][1...length-1]
			for(int j = 0; j < nums[0].length; j++){
				if(nums[i][j] == 0){
					nums[i][0] = 0;
					nums[0][j] = 0;
				}
			}
		}
		
		for(int i = nums.length-1; i >= 0; i--){
			for(int j = nums[0].length - 1; j >= 1; j--){
				if(nums[i][0] == 0 || nums[0][j] == 0){
					nums[i][j] = 0;
					//can not decide whether to set nums[x][0] = 0 use this condition
					//because we don't knwo nums[0][0] == 0 is assigned by condition nums[0][x01] == 0
					//or assigned by nums[x>0][0], if it's the first condition, then set nums[i][0]=0 is wrong
				}
			}
			if(col0 == 0)
				nums[i][0] = 0;
		}
	}
}
