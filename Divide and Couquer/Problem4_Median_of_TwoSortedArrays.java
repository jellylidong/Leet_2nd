/*There are two sorted arrays nums1 and nums2 of size m and n respectively. 
 * Find the median of the two sorted arrays. 
 * The overall run time complexity should be O(log (m+n)).*/

/*my analysis
 * 
 * some tips:
 * when we say position, we mean index+1
 * 
 * the index of the kth element start from s is s+k-1(e.g. start from 0, 1st is 0, 2nd is 1 ...)
 * for an array, the index of the number in the middle position is s+(e-s)/2,
 * so it's the s+(e-s)/2+1 th element if the start index is 0, i.e. e/2+1
 * or (l+1)/2, but note, if the l = 0; e/2+1 will be wrong while(l+1)/2 is still correct!!!!
 * 
 * 
 * so remember, always use (l+1)/2 to get the position of middle  number (which is index+1)
 * 
 * for two sorted arrays,
 * assume nums1[m1] <= nums2[m2]
 * where m1 and m2 are the middle number of each array
 * let k is the (l1+l2+1)/2 initially, which is the middle index of the two arrays
 * let x is the length of numbers before m1 (not include)
 * let y is the length of nuimber before m2 (not include)
 * 
 * so there are at least x+y+1 numbers before nums[m2], the +1 means nums1[m1]
 *    there are at least (e1-m1+1-1) + (e2-m2+1) nuimbers after nums[m1]. the -1 means nums1[m1]
 *    
 * 1.so if k <= (x+y+1), 
 * it means the median is < num2[m2], but we can not know if the median also < nums1[m1]
 * so the target number can be in the whole nums1, but only the part of num2 before m2,
 * i.e. we can find this number from nums1's s1 to e1(inclusive) 
 * and nums2's s2 to m2(not inclusive)
 * else k > (x+y+1)
 * it means the median at least > nums1[m1]
 * so  the k-(m1-s1+1) th element can be the median
 * i.e. we can find the (k-(m1-s2+1))th number 
 * from the (e1-m1-1+1) + (e2-m2+1) elements
 * 
 * if nums1[m1] <= nums[m2]
 * the code is symmetry, just exchange the position of nums1 and nums2 in code
 * 
 * we can above recursively to find the median,
 * while doing recursion, if one of the array is exhausted,
 *  i.e. s1 > e1, we can find the median only from the other array
 *  which is s2+k-1*/
public class Problem4_Median_of_TwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int l1 = nums1.length;
		int l2 = nums2.length;
		
		int k1 = (l1+l2+1)/2;
		double v1 = (double)helper(nums1, 0, l1-1, nums2, 0, l2-1, k1);
		
		//if the overall length is even, it means there are 2 middle numbers
		//so also find the other one
		if((l1+l2)%2 == 0){
			int k2 = k1+1;
			double v2 = (double)helper(nums1, 0, l1-1, nums2, 0, l2-1, k2);
			
			v1 = (v1+v2)/2;
		}
		
		return v1;
	}
	
	private int helper(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int k){
		if(s1 > e1)
			return nums2[s2+k-1];
		if(s2 > e2)
			return nums1[s1+k-1];
		
		int m1 = s1 + (e1-s1)/2;
		int m2 = s2 + (e2-s2)/2;
		
		if(nums1[m1] <= nums2[m2]){
			if(k <= (m1-s1+1) + (m2-s2))
				return helper(nums1, s1, e1, nums2, s2, m2-1, k);
			else
				return helper(nums1, m1+1, e1, nums2, s2, e2, k-(m1-s1+1));
		}
		else
		{
			if(k <= (m2-s2+1) + (m1-s1))
				return helper(nums2, s2, e2, nums1, s1, m1-1, k);
			else
				return helper(nums2, m2+1, e2, nums1, s1, e1, k-(m2-s2+1));
		}
		
		
	}
}
