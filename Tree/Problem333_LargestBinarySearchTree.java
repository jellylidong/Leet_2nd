
/**
 * Created by vcoder on 2/23/16.
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 */

/*my analysis
* use a res class to record the size, lower and upper of each subtree*/

public class Problem333_LargestBinarySearchTree {
    public class Solution {
        int max = 0;
        public int largestBSTSubtree(TreeNode root) {
            helper(root);
            return max;
        }

        public res helper(TreeNode root){
            if(root == null)
                return new res(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            res left = helper(root.left);
            res right = helper(root.right);

            //if any of the subtree is invalid BST, it mean the subtree start with current node is also invalid
            if(left.size == -1 || root.val <= left.upper
                    ||right.size == -1 || root.val >= right.lower){
                return new res(-1, 0, 0);//when the subtree is invalid BST, label the size = -1
                //for a valid BST, both its left and right subtree must be valid BST
            }
            int size = left.size + 1 + right.size;
            max = Math.max(max, size);
            return new res(size, Math.min(root.val, left.lower), Math.max(root.val, right. upper));
            //here we must use max/min function because when the child node is null,
            //we return lower=int_max, upper=int_min, we have to use max/min function to return the correct value
        }

        public class res{
            int size;
            int lower;
            int upper;

            res(int s, int l, int u){
                size = s;
                lower = l;
                upper = u;
            }
        }
    }
}
