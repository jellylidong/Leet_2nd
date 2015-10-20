
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
            res = Math.max(curMax, res);
        }
        return res;
    }
}
