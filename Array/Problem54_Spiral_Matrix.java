import java.util.*;

/*Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:*/

/*my analysis
 * set four boundaries, left, right, up, down
 * just up data each boundary after it's traversed
 * the code is straightforward
 * but it's very easy to get boundary mistakes*/
public class Problem54_Spiral_Matrix {
	public class Solution {
	    public List<Integer> spiralOrder(int[][] nums) {
	        List<Integer> res = new ArrayList<>();
	        if(nums.length == 0)//check empty first
	            return res;
	            
	        int count = 0;
	        int all = nums[0].length * nums.length;
	        
	        
	        int left = 0;
	        int right = nums[0].length-1;
	        int up = 0;
	        int down = nums.length-1;
	        
	        // while(left < right || up < down){
	        while(count < all){
	        	//!!!
	        	//must check count < all for every traversal
	        	//or it can write more numbers > all
	        	//e.g [[2,3]]
	            for(int i = left; count < all && i <= right; i++){
	                // res[count] = nums[up][i];
	                res.add(nums[up][i]);
	                count++;
	            }
	            up++;
	            
	            for(int i = up; count < all && i <= down; i++){
	                // res[count] = nums[i][right];
	                res.add(nums[i][right]);
	                count++;
	            }
	            right--;
	            
	            for(int i = right; count < all && i >= left; i--){
	                // res[count] = nums[down][i];
	                res.add(nums[down][i]);
	                count++;
	            }
	            down--;
	            
	            for(int i = down; count < all && i >= up; i--){
	                // res[count] = nums[i][left];
	                res.add(nums[i][left]);
	                count++;
	            }
	            left++;
	        }
	        
	        return res;
	    }
	}
}
