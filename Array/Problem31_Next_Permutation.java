/*
 Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */

/*
 * my analysis
 * next permutation is the next smallest number that bigger than current permutation
 * we need to find the position that nums[i-1] < nums[i] happens
 * because from this position i (include), the numbers are sorted in reversed order
 * so we need to find the minimum from [i, nums.length] that is bigger than nums[i-1]
 * than we swap nums[i-1] and nums[j], where j is the minimum value's index
 * and reverse nums[i, nums.length-1]
 * */
public class Problem31_Next_Permutation {
	public void nextPermutation(int[] nums){
		int i = nums.length-1;
		while(i > 0){
			if(nums[i-1] < nums[i])
				break; // note: from i (include), numbers are in reversed order 
			i--;
		}
		if(i == 0) //i == 0 mean the whole array is in reversed order, no bigger permutation exist, so just reverse it as the problem requires
			reverse(0, nums.length-1, nums);
		else{
			int j = i;
			while(j < nums.length && nums[j] > nums[i-1])
				j++;
			int minId = j--; // position of the minimum value bigger than nums[i-1]
			
			//swap nums[i-1] and nums[minId]
			int tmp = nums[minId];
			nums[minId] = nums[i-1];
			nums[i-1] = tmp;
			reverse(i, nums.length-1, nums);
		}
	}
	
	public void reverse(int lo, int hi, int[] nums){
		while(lo < hi){
			int tmp = nums[hi];
			nums[hi] = nums[lo];
			nums[lo] = tmp;
			lo++;
			hi--;
		}
	}
}
