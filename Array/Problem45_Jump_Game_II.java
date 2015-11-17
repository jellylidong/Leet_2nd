/*Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)*/

/*my analysis
 * note that i+nums[i] is the max position we can reach in position i,
 * so when for step s, assume we are at position i, currently we can reach max position curMax
 * we traversal position [i, curMax] to find the max position we can reach:
 * 		nextMax = Math.max(nextMax, i+nums[i]);
 * *****(looking for the next max position we can reach)
 * *****(if next max position is already >= nums.lengh-1, we return the step)
 * After traversal, update curMax: curMax = nextMax
 * note: for the case nums.length <= 1, we are already at the target position, so return 0 directly
 * */
public class Problem45_Jump_Game_II {
	public int jump(int[] nums){
		if(nums.length <= 1)
			return 0;
		
		int curMax = 0;
		int nextMax= 0;
		int i = 0;
		int step = 0;
		
		while(curMax - i >= 0){//make sure current position i is <= current max position that we can reach
			step++;
			for(; i <= curMax; i++){
				nextMax = Math.max(nextMax,  i+nums[i]);
				if(nextMax >= nums.length)
					return step;
			}
			curMax = nextMax;
		}
		return Integer.MAX_VALUE;
	}

}
