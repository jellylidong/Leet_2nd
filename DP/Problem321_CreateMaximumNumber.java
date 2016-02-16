/**
 * Created by vcoder on 2/15/16.
 */

/*Given two arrays of length m and n with digits 0-9 representing two numbers.
Create the maximum number of length k <= m + n from digits of the two.
The relative order of the digits from the same array must be preserved.
Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]*/

/*my analysis
* basic idea:
* iterate i from Math.max(0, k-l1) to Math.min(k, l2)
* generate array a1 with length i from nums1
* generate array a2 with length i from nums2
* merge these two arrays a1 and a2 two make the maximum array (name as candidate)
* (which has length k)
* compare candidate with previously generated candidate (name as ans)
* re-assign ans with the greater value
*
*
* explain for the range of i's iteration
* 1. if both l1 and l2 <= k:
* for a1, the length range is [k-l2, l1]
* for a2, the length range is [k-l1, l2]
* the length range should be the the cross set of these two range
* i.e. [Math.max(k-l2, k-l1), Math.min(l1,l2)]
*
*
* 2.if l1 > k, l2 < k
* for a1 length range is [0, k]
* for a2 length range is [0, l2]
* so the range is [0, l2]
*
* 3.if l1>k, l2>k
* for a1 and a2, the min length should be 0, max length should be k
*
* to summarize, given two arrays that l1+l2 >= k
* the max length of a1 should be k-Math.max(0, k-l1)
* the max length of a2 should be Math.min(k, l2)
* so the range of i should be [Math.max(0, k-l1), Math.min(k, l2)]
* */


public class Problem321_CreateMaximumNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] ans = new int[k];
        for(int i = Math.max(0, k-m); i<=n && i <=k; i++){ // here i is the length, so must be <=
            int[] candidate = merge(maxArray(nums1, k-i), maxArray(nums2, i), k);
            if(greater(candidate, 0, ans, 0))
                ans = candidate;
        }
        return ans;
    }

    //!!!!!!!!!!!
    public int[] maxArray(int[] nums, int k){
        int[] ans = new int[k];
        for(int i=0, j=0; i < nums.length; i++){
            while(nums.length-i+j > k && j > 0 && ans[j-1] < nums[i])
                j--;
            if(j < k)
                ans[j++] = nums[i];
        }
        return ans;
    }

    public int[] merge(int[] nums1, int[] nums2, int k){
        int[] ans = new int[k];
        for(int r=0, i=0, j=0; r < k; r++){
            ans[r] = greater(nums1, i, nums2, j)? nums1[i++]: nums2[j++];
        }
        return ans;
    }

    public boolean greater(int[] nums1, int i, int[] nums2, int j){
        while(i < nums1.length && j < nums2.length && nums1[i] == nums2[j]){
            i++;
            j++;
        }
        return j==nums2.length || (i<nums1.length && nums1[i] > nums2[j]);
    }
}
