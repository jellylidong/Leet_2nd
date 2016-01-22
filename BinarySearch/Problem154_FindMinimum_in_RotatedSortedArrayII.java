/**
 * Created by vcoder on 1/21/16.
 */

/*Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.*/

/*my analysis
* just start looking for this number from the middle position
* if nums[m] > nums[e]
* it means the min is is between m+1 and hi
* if nums[m] < nums[s]
* it means the min is between s and m (since m can be the min, we can not do m-1)
* otherwise, nums[s] <= nums[m] <= nums[e]
* since we need to find the min, so it's safe to reduce e, ie e--*/

public class Problem154_FindMinimum_in_RotatedSortedArrayII {
    public int findMin(int[] nums) {
        int id = helper(nums, 0, nums.length-1);
        return nums[id];
    }

    public int helper(int[] nums, int s, int e){
        if(s == e)
            return s;
        int m = s + (e-s)/2;
        if(nums[m] > nums[e])
            return helper(nums, m+1, e);
        else if(nums[m] < nums[s])
            return helper(nums, s, m);
        else
            return helper(nums, s, e-1);
    }
}
