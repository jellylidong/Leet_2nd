
public class Problem198_House_Robber {
	public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        int preYes = nums[0];
        int preNo  = 0;
        
        for(int i = 1; i < nums.length; i++){
            int curYes = nums[i] + preNo;
            int curNo  = Math.max(preYes, preNo);
            preYes = curYes;
            preNo  = curNo;
        }
        return Math.max(preYes, preNo);
    }
	
	public int rob_optimized(int[] nums) {
        if(nums.length == 0)
            return 0;
        int preYes = nums[0];
        int preNo  = 0;
        int tmp = 0;
        for(int i = 1; i < nums.length; i++){
            tmp = preYes;
            preYes = nums[i] + preNo;
            preNo  = Math.max(tmp, preNo);
        }
        return Math.max(preYes, preNo);
    }
}
