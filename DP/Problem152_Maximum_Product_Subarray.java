/*Find the contiguous subarray within an array
(containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.*/

/*solution is straightforward*/
public class Problem152_Maximum_Product_Subarray {
	public int maxProduct(int[] nums) {
        int curMax = nums[0];
        int curMin = nums[0];
        int preMax = nums[0];
        int preMin = nums[0];
        
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            curMax = Math.max(Math.max(preMax*nums[i], preMin*nums[i]), nums[i]);
            curMin = Math.min(Math.min(preMax*nums[i], preMin*nums[i]), nums[i]);
            preMax = curMax;
            preMin = curMin;
            res = Math.max(curMax, res);
        }
        return res;
    }
	
	public int maxProduct_optimized(int[] nums) {
        int curMax = nums[0];
        int curMin = nums[0];
        
        
        int res = nums[0];
        //!!!!!!!!!!!!!!!!
        // the max/min judge can only keep for example
        // curMax*num[i] or num[i]
        //it can not keep the curMax
        //we need to use a variable to store this value and update it and the end of each loop
        for(int i = 1; i < nums.length; i++){
            if(nums[i] >= 0){
                curMax = Math.max(curMax*nums[i], nums[i]);
                curMin = Math.min(curMin*nums[i], nums[i]);
            }
            else{
                int tmp = curMax;
                curMax = Math.max(curMin*nums[i], nums[i]);
                curMin = Math.min(tmp*nums[i],    nums[i]);
            }
            //update the real max value
            res = Math.max(curMax, res);
        }
        return res;
    }
}
