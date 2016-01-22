/**
 * Created by vcoder on 1/18/16.
 */

/*Given an integer array nums, return the number of range sums
that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of
the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2]
and their respective sums are: -2, -1, 2.*/

/*My analysis
* define s(i) is the sum of [0 ... i]
* so s(j)-s(i) is the sum of[i+1, j]
* so note: s(i)-s(0) is the sum of [1...i]
* we can get sum of [0 ... i] by s(i)-s(j)
* to solve this, we need to have an array sums with length n+1
* where sums[i] is the sum of nums[0..i-1], and sums[0] is 0
*
* let's see how merge sort works
* first we have b and a, we can get merge-sorted result [a,b]
* then we have [a1,a2] and [b1,b2], both array are sorted
* we can get how many number in B that are
* lower <= bi-ai <= upper
* then we sort this two parts and continue the further counting
*
* similarly, in this problem, first we can get sums[0 ... n]
* where sum[i] is the sum of [0 .. i-1]
* we can find the range sum by divide and conquer using merge sort
* for each sub-array [s,e),
* the mid is m = (s+e)/2
* since this sub-array has already been sorted by previous operations,
* so sums of [s to m) is always <= sums of[m, e)
* so we can find the number of the target range sum of the former part from the latter part
* and then sort the whole sub-array
* */

public class Problem327_Count_of_RangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums.length == 0)
            return 0;
        long sum = 0;
        long[] sums = new long[nums.length+1];
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            sums[i+1] = sum;
        }

        return mergeFind(sums, lower, upper, 0, nums.length+1);

    }
    
    //e is a non-inclusive boundary
    public int mergeFind(long[] sums, int lower, int upper, int s,  int e){
        //when e-s < 2, i.e s+1 = e
    	//for example s = 0, e = 1, them m = (s+e)/2 = 0
    	//one branch is mergeFind(..., 0,0)
    	//the other would be mergeFind(..., 0, 1)
    	//if we don't return 0, there would be a dead loop
    	
    	//or we can explain by this:
    	//the range sum is s(j)-s(i)
    	//for a range[i, i+1), there is only one number
    	//can not form a range, so return 0
    	if(e-s < 2)
            return 0;
        int m = (e+s)/2;
        int count = mergeFind(sums, lower, upper, s, m) +
                mergeFind(sums, lower, upper, m, e);
        long[] cache = new long[e-s];
        int j = m, k = m, t = m;
        for(int i = s, r = 0; i < m; i++, r++){
            //j is the first index that sums[j]-sums[i] >= lower
            //k is the first index that sums[k]-sums[i] > upper
            while(j < e && sums[j] - sums[i] < lower) j++;
            //note the trick of k, we must use <=
            //if we use <, the loop can end with either sums[k]-sums[i] == upper or > upper
            //which make the counting difficult
            while(k < e && sums[k] - sums[i] <= upper) k++;


            //meger sort, use cache array to store current sorted results
            //then copy all sorted results to the original array

            //put all the numbers that < sums[i] of sums[m, e) to the cache array
            while(t < e && sums[t] < sums[i]) cache[r++] = sums[t++];
            cache[r] = sums[i];
            count += k-j;
        }
        //copy the sorted part from cache to original array
        System.arraycopy(cache, 0, sums, s, t-s);
        return count;
    }
}
