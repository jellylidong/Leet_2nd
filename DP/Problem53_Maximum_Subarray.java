/*Find the contiguous subarray within an array
(containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.*/
public class Problem53_Maximum_Subarray {
	 public int maxSubArray_On(int[] nums) {
	        int maxEndHere = nums[0];
	        int maxSoFar   = nums[0];
	        
	        for(int i = 1; i < nums.length; i++){
	            maxEndHere = Math.max(maxEndHere+nums[i], nums[i]); // if maxEndHere is negative, then maxEndHere = nums[i];
	                                                                // if maxEndHere is positive, then maxEndHere = maxEndHere+nums[i]
	                                                                // so there is no need to worry about add with "gap"
	            maxSoFar   = Math.max(maxEndHere, maxSoFar);
	        }
	        return maxSoFar;
	 }
	 
	 public int maxSubArray_Greedy(int[] nums) {
	        int min = 0;
	        int res = nums[0];
	        int sum = 0;
	        for(int i = 0; i < nums.length; i++){
	            sum += nums[i];
	            if(sum - min > res) res = sum -min;
	            if(sum < min) min = sum;
	        }
	        return res;
	    }
}
