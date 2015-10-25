/*Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].*/
public class Problem34_Search_for_a_Range {
/*my analysis
 * the hard part of this problem is that we have to 
 * find the two indexes in O(lgn)
 * the basic idea is:
 * we do binary search for two times
 * the first time we look for the lower index
 * 	mid = (lo + hi)/2;
 * 	if(nums[mid] < target)	lo = mid + 1;
 * 	else	hi = mid;
 * the second time we look for higher index
 * 	mid = (lo + hi) /2 + 1
 * 	if(nums[mid] > target)	hi = mid - 1
 * 	else lo = mid;
 * 
 * note:
 * there are two tricks in this solution
 * first, after first binary search, we need to check if nums[lo] == target
 * if not, it mean no target exists in this array
 * second, before 2nd binary search, we only need to update hi = nums.length-1
 * because we have found one target
 * beside, in the 2nd binary search, 
 * mid = (lo + hi)/2 + 1
 * we must add 1 so that the mid value is bias to the right part
 * or the binary will be stuck in cases like [2,2], 2 ------------????still don't stand*/
	public int[] searchRange(int[] nums, int target){
		int[] res = {-1, -1};
		int lo = 0;
		int hi = nums.length - 1;
		while(lo < hi){
			int mid = (lo + hi)/2;
			if(nums[mid] < target)	lo = mid + 1;
			else hi = mid;
		}
		if(nums[lo] != target)	return res;
		else res[0] = lo;
		
		hi = nums.length - 1;
		while(lo < hi){
			int mid = (lo + hi)/2 + 1; //!!!!!!!!bias to right so that binary search won't be stuck
			if(nums[mid] > target)	hi = mid - 1;
			else lo = mid;
		}
		res[1] = hi;
		return res;
	}
}
