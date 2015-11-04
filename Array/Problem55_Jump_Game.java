/*Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.*/

/*my analysis
 * initially, set maxReach = 0
 * traversal the number one by one
 * every iteration, 
 * if maxReach <= current position i
 * it mean we can not reach not far than current position i
 * so return false
 * if not, update the maxReach if i+nums[i] > maxReach*/
public class Problem55_Jump_Game {
	public boolean jumpGame(int[] nums){
		int maxReach = 0;
		for(int i = 0; i < nums.length; i++){
			if(maxReach <= i)
				return false;
			maxReach = Math.max(maxReach, i+nums[i]);
		}
		return true;
	}
}
