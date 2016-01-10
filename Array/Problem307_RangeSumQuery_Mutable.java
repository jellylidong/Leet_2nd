/*Given an integer array nums, 
 * find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.*/

/*here we use a special binary tree called binary index tree
 * 
 * to build the tree we start j = i+1 (i start from 0 to nums.length)
 * tree[j] += nums[j]
 * then j += j & (-j)
 * 
 * update the tree follows the same step of build tree
 * 
 * to get the sumRange(i, j) (both are inclusive)
 * we need to get s1 = sum(0, i-1) s2 = (0, j)
 * then sum = s2 - s1
 * to get sum(0, i)
 * we start from i+1 to 1
 * sum += tree[j];
 * j -= j&(-j)*/

public class Problem307_RangeSumQuery_Mutable {
	public class NumArray{
		int[] nums;
		int[] tree;
		public NumArray(int[] nums) {
			this.nums = nums;
			this.tree = new int[this.nums.length+1];
			for(int i = 0; i < this.nums.length; i++){
				for(int j = i+1; j < tree.length; j += j&(-j)) // !!!!!!!!!!! remember this
					tree[j] += nums[i];
			}
		}
		
		 void update(int i, int val) {
			 for(int j = i+1; j < tree.length; j += j&(-j)){
				 tree[j] += val - nums[i];
			 }
			 nums[i] = val;
		 }
		 
		 public int sumRange(int i, int j) {
			 int s1 = sumRange(i-1);
			 int s2 = sumRange(j);
			 return s2 - s1;
		 }
		 
		 private int sumRange(int i){
			 if(i < 0)
				 return 0;
			 int sum = 0;
			 for(int j = i+1; j > 0; j -= j&(-j))
				 sum += tree[j];
			 return sum;
		 }
	}
}
