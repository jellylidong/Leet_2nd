
public class Problem213_House_Robber_II {
	public int rob(int[] nums) {
        int l = nums.length;
        if(l == 0)
            return 0;
        if(l == 1)
            return nums[0];
        return Math.max(helper(nums, 0, l-2), helper(nums, 1, l-1));
    }
    
    public int helper(int[] nums, int start, int end){
        int preYes = nums[start];
        int preNo  = 0;
        for(int i = start+1; i <= end; i++){
            int tmp = preYes;
            preYes = preNo + nums[i];
            preNo = Math.max(tmp, preNo);
        }
        return Math.max(preYes, preNo);
    }
}
