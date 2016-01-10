import java.util.Arrays;

/*You are given an integer array nums and 
 * you have to return a new counts array. 
 * The counts array has the property 
 * where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

Hide Company Tags Google
Hide Tags Divide and Conquer Binary Indexed Tree Segment Tree Binary Search Tree
*/

/*my analysis
 * we need to build a binary search tree
 * where for each tree node, it has 
 * val: (this number), 
 * sum: the total numbber of left nodes this node's left tree
 * dup: the number of this node itself
 * for each value we can get the number at its right and smaller it by
 * add up all the sum and dup when we go right (include the tree root node)
 * for example
 * for[5, 2, 6, 1]
 * first is 1
 * the tree is
 * 1,(0, 1) ans[3] = 0
 * then 6
 * the tree will become
 * 1,(0,1) 
 * 	\
 * 	6(0,1) ans[2] = 0 + 1 (node 1)
 * then 2
 * the tree becomes
 * 			1,(0,1)
 *  		\
 *  		6(1,1) there will be a left tree, update the sum from 0 to 1
 *  		/
 *  	   2,(0,1) ans[1] = 0 + 1 (node 1)
 *  then 5
 *  the tree becomes
 *   	   1,(0,1)
 *  		\
 *  		6(2,1) there will be a left tree, update the sum from 2 to 3
 *  		/
 *  	   2,(0,1)
 *  	   \
 *  	    5,(0,1) ans[0] = 0+1  + 0+1 = 2 (node 2 and 1)
 *  */

import java.util.*;

public class Problem315_Count_of_SmallerNumbersAfterSelf {
	public List<Integer> countSmaller(int[] nums) {
		if(nums.length == 0)
			return new ArrayList<Integer>();
		Integer[] ans = new Integer[nums.length];
		TreeNode node = null;
		for(int i = nums.length-1; i >= 0; i--){
			node = buildTree(node, nums[i], ans, i, 0);
		}
		return Arrays.asList(ans);
	}
	
	private class TreeNode{
		int val, sum, dup;
		TreeNode  left, right;
		TreeNode(int v, int s){
			dup = 1;
			val = v;
			sum = s;
		}
	}
	
	private TreeNode buildTree(TreeNode node, int val, Integer[] ans, int i, int preSum){
		if(node == null){
			ans[i] = preSum;
			node = new TreeNode(val, 0);
		}
		else if(node.val == val){
			node.dup++;
			ans[i] = node.sum + preSum; //?why need to add preSum
		}
		else if(node.val > val){
			node.sum++;
			node.left = buildTree(node.left, val, ans, i, preSum);
		}
		else{
			node.right = buildTree(node.right, val, ans, i, node.sum + preSum + node.dup);
		}
		return node;
	}
}
