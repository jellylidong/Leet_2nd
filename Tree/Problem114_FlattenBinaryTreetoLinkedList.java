/*Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, 
each node's right child points to the next node of a pre-order traversal.*/

/*my analysis
 * remember the code!!!!!!
 * for a tree like
 *       1		
        / \		
       2   5	
       
 * 
 * try to do similar in-place transform like inorder and postorder*/
public class Problem114_FlattenBinaryTreetoLinkedList {
	TreeNode pre = null;
    public void flatten(TreeNode root) {
    	if(root == null)
    		return;
    	flatten(root.right);
    	flatten(root.left);
    	root.right = pre;
    	root.left = null;
    	pre = root;
    }
}
