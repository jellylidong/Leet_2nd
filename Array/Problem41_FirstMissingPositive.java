/*Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.*/

/*my analysis
 *this problem can be solved in three steps:
 *step1
 *partition the array into two parts,
 *the left part are nums[0 ... k] that are positive
 *the right part are negative values
 *this can be realized by using two pointers
 *every time we find a positive value
 *q++; swap(nums, i, q); i++
 *
 *Step2
 *now we get the array that nums[0 .. q] are all positive numbers
 *tmp = abs(nums[i])
 *if(tmp - 1 <= q && nums[tmp-1] > 0
 *	nums[tmp-1] = -nums[tmp-1];
 *above operation will label exist numbers as negative
 *
 *Steop3
 *then we go from 0 to q
 *if we find a value that is positive
 *this means it has not been labeled, so its indext+1 is the first missing number*/
public class Problem41_FirstMissingPositive {
	public int firstMissingPositive(int[] nums) {
		//Step 1: partition
		int q = -1;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] > 0){
				q++;
				int tmp = nums[q];
				nums[q] = nums[i];
				nums[i] = tmp;
			}
		}
		
		//Step 2: label
		for(int i = 0; i <= q; i++){
			int tmp = Math.abs(nums[i]);
			if(tmp - 1 <= q && nums[tmp-1] > 0)
				nums[tmp-1] = - nums[tmp-1];
		}
		
		//Step 3 find the first missing positive
		int i = 0;
		for(; i <= q; i++){
			if(nums[i] > 0)
				break;
		}
		return i+1;
	}
}
