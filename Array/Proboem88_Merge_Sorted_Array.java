/*Given two sorted integer arrays nums1 and nums2, 
 * merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space 
(size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.*/

/*my analysis
 * the best way is store the numbers from nums[m+n-1]
 * and go through numbers in valid range from m-1 and n-1
 * so that we don't change the numbers that has not been read
 * if go through numbers from 0, we'll have to store the least number from nums[m+n-1]
 * ie we have to store merge results descending order and 
 * then change it to ascending order
 * a very tricky case is
 * [4,5,6,0,0,0], 3
 * [1,2,3] 3
 * in this case if we go from 0,
 * the unread number of nums1 will be changed
 * the don't do this*/

public class Proboem88_Merge_Sorted_Array {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m-1;
		int j = n-1;
		while(i >= 0 && j >= 0){
			if(nums1[i] >= nums2[j])
				nums1[i+j+1] = nums1[i++];
			else
				nums2[i+j+1] = nums2[j++];
		}
		//actually this while changes nothing because in nums1, this part is already sorted
		while(i >= 0)
			nums1[i+j+1] = nums1[i++];
		
		
		while(j >= 0)
			nums2[i+j+1] = nums2[j++];
	}
}
