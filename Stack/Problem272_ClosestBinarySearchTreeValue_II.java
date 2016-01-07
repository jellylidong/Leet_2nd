/*Given a non-empty binary search tree and a target value, 
 * find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values 
in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, 
could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, 
it makes the problem much easier.
Without parent pointer we just need to keep track of the path 
from the root to the current node using a stack.
You would need two stacks to track the path in 
finding predecessor and successor node separately.
Show Company Tags
Show Tags
Show Similar Problems
*/

/*my analysis
 * use two stacks to store the inorder and reversed inorder traversal
 * results of the BST
 * note: when push to stacks, we need to check the root.val
 * for non-reverse, if root.val >= target, terminate pushing
 * for reverse, if root.val < target, terminate push
 * early terminating is required because if we don't terminate in time
 * useless value will be pushed to the top of stacks, which will lead to 
 * incorrect results
 * e.g.
 * [3,1,4,null,2]
	2.000000
	1
	obviously, the results should be 2
	if we don't have early termintating
	inorder_stk: 1,2,3,4
	reverse_stk: 4,3,2,1
	we have to start pop from the peek, 1 and 4 will be poped
	which are wrong reslts
	
	more details, see the code*/

import java.util.*;
public class Problem272_ClosestBinarySearchTreeValue_II {
	 public List<Integer> closestKValues(TreeNode root, double target, int k) {
		 Stack<Integer> sin = new Stack<>();
		 Stack<Integer> sre = new Stack<>();
		 
		 inorder(root, false, target, sin);
		 inorder(root, true, target, sre);
		 
		 List<Integer> res = new ArrayList<>();
		 while(k-- > 0){
			 if(sin.isEmpty())
				 res.add(sre.pop());
			 else if(sre.isEmpty())
				 res.add(sin.pop());
			 else if(Math.abs(sin.peek() - target) < Math.abs(sre.peek() - target))
				 res.add(sin.pop());
			 else
				 res.add(sre.pop());
		 }
		 return res;
	 }
	 
	 private void inorder(TreeNode root, boolean reverse, double target, Stack<Integer> stk){
		 if(root == null)
			 return;
		 
		 if(reverse)
			 inorder(root.right, reverse, target, stk);
		 else
			 inorder(root.left, reverse, target, stk);
		 
		 if((reverse && root.val <= target) || (!reverse && root.val > target))
			 return;
		 
		 stk.push(root.val);
		 
		 if(reverse)
			 inorder(root.left, reverse, target, stk);
		 else
			 inorder(root.right, reverse, target, stk);
	 }
}
