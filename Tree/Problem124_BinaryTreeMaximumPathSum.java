/*Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

Show Company Tags
Show Tags
Show Similar Problems
*/

/*my analysis
 * the basic idea is find the max left sub tree, find the max right sub tree
 * since the node.val can be negative
 * the the final max sum can be in any sub tree
 * so we need to update max value in every recursion*/
public class Problem124_BinaryTreeMaximumPathSum {
	int max;
    public int maxPathSum(TreeNode root) {
    	max = Integer.MIN_VALUE;
    	maxSub(root);
    	return max;
    }
    
    private int maxSub(TreeNode root){
    	if(root == null)
    		return 0;
    	int left = Math.max(0, maxSub(root.left));
    	int right = Math.max(0, maxSub(root.right));
    	max = Math.max(max, left+root.val+right);
    	return Math.max(left, right) + root.val;
    }
}
