/*Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].*/

/*my analysis
 * if index i is odd
 * nums[i] should be >= nums[i-1]
 * or we swap them
 * if index i is even
 * nums[i] should <= nums[i-1]
 * or we swap them*/
public class Problem280_WiggleSort {
	public void wiggleSort(int[] nums){
		for(int i = 0; i < nums.length; i++){
			if(i%2 == 1){
				if(nums[i] < nums[i-1]){
					int tmp = nums[i];
					nums[i] = nums[i-1];
					nums[i-1] = tmp;
				}
			}
			else if(i != 0){
				if(nums[i] > nums[i-1]){
					int tmp = nums[i];
					nums[i] = nums[i-1];
					nums[i-1] = tmp;
				}
			}
		}
	}
}
