/*Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.*/

/*my analysis
 * method 1
 * is first find the index of minimum number, because the pivot position is always this index
 * the code can be */
 /*
 while(lo < hi){
 	mid = (lo + hi)/2;
 	if(nums[mid] < nums[hi]) hi = mid;
 	else lo = mid - 1;
 }*/
/*then lo would be the index of minimum number and we can decide which part to search for the target*/

/*method 2
 * the target is always in left part of pivot or right part of pivot
 * so when doing binary search
 * every time we decide how to update lo and hi by comparing the value of nums[mid] with target
 * if nums[mid] == target	return mid
 * if(nums[mid] < nums[hi] ==>
 * 		if nums[mid] < target <= nums[hi] then lo= mid+1 else hi = mid-1
 * if(nums[mid] > nums[lo]
 * 		if nums[lo] <= target < nums[mid] then hi = mid-1 else lo = mid-1
 * keep doing this until lo == hi
 * then return nums[lo] == target? lo: -1*/


public class Problem33_Search_in_Rotated_Sorted_Array {
	public static int search(int[] nums, int target){
		int lo = 0;
		int hi = nums.length - 1;
		while(lo < hi){
			int mid = (lo + hi) / 2;
			if(nums[mid] == target)
				return mid;
			if(nums[mid] < nums[hi]){
				if(nums[mid] < target && target <= nums[hi]) // if in this range, lower bound = mid+2
					lo = mid + 1;
				else // not in this range, hiher bound = mid-1
					hi = mid - 1;
			}
			else{// nums[mid] >= nums[hi], we can only search another part
				if(nums[lo] <= target && target < nums[mid])
					hi = mid - 1;
				else
					lo = mid + 1;
				
			}
			
			
		}
		return nums[lo] == target? lo: -1;
		
	}
}
