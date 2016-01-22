/**
 * Created by vcoder on 1/21/16.
 */

/*Given an array nums containing n + 1 integers
where each integer is between 1 and n (inclusive),
prove that at least one duplicate number must exist.
Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.*/


/*my analysis
* we are given an arry of length n+1
* but only 1 .. n are stored in the array
* to find the dup,
* although the array is not sorted, but we can count
* how many numbers are <= i, let it be count
* the count can be only <= i or > i
* if count <= i
* it means we have exactly [1..i] in the array, no dup
* if count > i
* it means we have [1...i] and some other numbers, which is the dup
* among [1..i], so continue to search in the range of [1..i]*/

public class Problem287_Find_the_Duplicate_Number {
    public int findDuplicate(int[] nums) {
        int lo = 1;
        int hi = nums.length;
        while(lo < hi){
            int m = lo + (hi-lo)/2;
            int count = 0;
            for(int n: nums){
                if(n <= m)
                    count++;
            }
            if(count == m)
                lo = m+1;
            else
                hi = m;
        }
        return lo;
    }
}
