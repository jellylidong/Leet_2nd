/*Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.*/

/*the code is straightforward
 * count from down to up
 * any node without children is a UT
 * for any node with children
 * it's a UT when the conditions in the code*/
public class Problem250CountUnivalueSubtrees {
	int res = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
            isUT(root);
            return res;
    }
    
    private boolean isUT(TreeNode root){
    	if(root == null)
    		return true;
    	boolean left = isUT(root.left);
    	boolean right = isUT(root.right);
    	if(root.left == null && root.right == null){
    		res++;
    		return true;
    	}
    	if(root.left == null && root.right != null){
    		if(root.val == root.right.val && right)
    			res++;
    		return root.val == root.right.val && right;
    	}
    	if(root.left != null && root.right == null){
    		if(root.val == root.left.val && left)
    			res++;
    		return root.val == root.left.val && left;
    	}
    	
    	if(root.val == root.left.val && root.val == root.right.val && left && right)
    		res++;
    	return root.val == root.left.val && root.val == root.right.val && left && right;
    }
}
