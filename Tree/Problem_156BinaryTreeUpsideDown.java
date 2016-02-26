/**
 * Created by vcoder on 2/25/16.
 * Given a binary tree where all the right nodes
 * are either leaf nodes with a sibling
 * (a left node that shares the same parent node) or empty,
 * flip it upside down and turn it into a tree
 * where the original right nodes turned into left leaf nodes.
 * Return the new root.

 For example:
 Given a binary tree {1,2,3,4,5},
 1
 / \
 2   3
 / \
 4   5
 return the root of the binary tree [4,5,2,#,#,3,1].
 4
 / \
 5   2
 / \
 3   1
 */
public class Problem_156BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode next_root = null;  //next
        TreeNode next_right = null; //pre
        TreeNode next_left = null; // tmp

        while(cur != null){
            next_root = cur.left;
            cur.left = next_left;
            next_left = cur.right;
            cur.right = next_right;
            next_right = cur;
            cur = next_root;
        }
        return next_right;
    }
}
