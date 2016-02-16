///*Rotate an array of n elements to the right by k steps.
//
//For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7]
//is rotated to [5,6,7,1,2,3,4].
//
//Note:
//Try to come up as many solutions as you can,
//there are at least 3 different ways to solve this problem.
//
//[show hint]
//
//Hint:
//Could you do it in-place with O(1) extra space?
//Related problem: Reverse Words in a String II
//
//Credits:
//Special thanks to @Freezen for adding this problem
//and creating all test cases.*/
//
///*method_1, On time, On space
// * store the rotated results into a new array,
// * than assign the new array to original array*/
//
///*method_2, On time, O1 space
// * reverse whole array
// * reverse 0 to k-1
// * reverse k to length-1*/
//
///*method_3, On time, O1 space
// * start from one element, keep rotating untill
// * we have rotated n elements*/
//
//
//public class Problem189_Rotate_Array {
//	public void rotate_1(int[] nums, int k) {
//        int l = nums.length;
//        int[] tmp = new int[l];
//        for(int i = 0; i < l; i++){
//            tmp[(i+k)%l] = nums[i];
//        }
//        for(int i = 0; i < nums.length; i++)
//            nums[i] = tmp[i];
//    }
//
//	public void rotate_2(int[] nums, int k) {
//	       k = k%nums.length;
//	       reverse(nums, 0, nums.length-1);
//	       reverse(nums, 0, k-1);
//	       reverse(nums, k, nums.length-1);
//	    }
//
//	    public void reverse(int[] nums, int lo, int hi){
//	        while(lo < hi){
//	            int tmp = nums[hi];
//	            nums[hi] = nums[lo];
//	            nums[lo] = tmp;
//	            lo++;
//	            hi--;
//	        }
//	    }
//
//	    public void rotate_3(int[] nums, int k){
//	    	k = k%nums.length;
//	    	if(k == 0)
//	    		return;
//	    	int start = 0;
//	    	int cur = start;
//	    	int numToBeRotated = nums[start];
//	    	int cntRotated = 0;
//
//	    	while(cnt < nums.length){
//	    		do{
//	    			int tmp = nums[(cur+k)%nums.length];
//	    			nums[(cur+k)%nums.length] = numToBeRotated;
//	    			numToBeRotated = tmp;
//	    			cur = (cur+k)%nums.length;
//	    			cntRotated++;
//	    		}while(cur != start);
//
//	    		start++;
//	    		cur = start;
//	    		numToBeRotated = nums[start];
//	    	}
//	    }
//}
