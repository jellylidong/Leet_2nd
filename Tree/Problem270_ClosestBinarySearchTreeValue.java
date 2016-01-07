/*Given a non-empty binary search tree and a target value, 
 * find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.*/

/*my analysis
 * we can easily inorder traverse the BST and find the closest value
 * but it's O(n) time complex, we can make use some features of BST
 * root.val > target, the closest value must be in the left subtree or root
 * because target can be a value between root and subtree
 * root.val = target ==> root.val
 * root.val < target, the closest value must be in the right subtree or root
 * */
public class Problem270_ClosestBinarySearchTreeValue {
	public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode t = a > target? root.left: root.right;
        if(t == null)
            return a;
        int b = closestValue(t, target);
        double v1 = Math.abs(a-target);
        double v2 = Math.abs(b-target);
        
        return v1 > v2? b: a;
    }
}
