/*Given an array of n integers nums and a target, 
 * find the number of index triplets i, j, k 
 * with 0 <= i < j < k < n that satisfy
 * the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?*/

/*my analysis
 * sort the array
 * go through the whole array from 0 to end
 * for every iteration i, let lo = i+1, hi=len-1
 * if nums[i, lo, hi] < target
 * update count
 * note!:  if(nums[i] + nums[left] + nums[right] < target)
 * this means nums[i] + nums[lo] + nums[any indices that > lo and < hi]
 * meet condition < target
 * so we need to do count += right-left
 * !!!! not count++*/

import java.util.*;
public class Problem259_3Sum_Smaller {
	public int threeSumSmaller(int[] nums, int target) {
		Arrays.sort(nums);
		int count = 0;
		for(int i = 0; i < nums.length; i++){
			int lo = i+1;
			int hi = nums.length-1;
			while(lo < hi){
				if(nums[i] + nums[lo] + nums[hi] < target){
					count += hi - lo;
					lo++;
				}
				else
					hi--;
			}
		}
		return count;
	}
}
