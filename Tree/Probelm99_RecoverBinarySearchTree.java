/*Two elements of a binary search tree (BST) are 
 * swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. 
Could you devise a constant space solution?*/

/*my analysis
 * if we just traverse the tree in order, and store the traversal result
 * into an array, it's easy to find which two are exchanged
 * but for the space requirement, we can in-order traversal
 * the tree and find these two nodes without store it
 * 
 * first, let's see an in-order-traversed tree
 * 
 * 1,6,3,4,5,2
 * it's easy to find 6 and 2 the our target
 * first, we find 6 > 3 ==> 6, so t1 = the bigger number's node(left),
 * then we find 5 > 2  ==> 2, so t2 = smaller number's node(right)
 * 
 * thus based on the code of inorder traversal
 * if(root == null)
 * 	return;
 * traverse(root.left);
 * do sth
 * traverse(root.right);
 * 
 * we can see the following code: remember!!!!!!!!!!!!*/
public class Probelm99_RecoverBinarySearchTree {
	TreeNode t1 = null, t2 = null;
	TreeNode pre = null;
	public void recoverTree(TreeNode root) {
        traverse(root);
        int tmp = t1.val;
        t1.val = t2.val;
        t2.val = tmp;
    }
	
	public void traverse(TreeNode root){
		if(root == null)
			return;
		traverse(root.left);
		if(pre != null){
			if(t1 == null && pre.val > root.val)
				t1 = pre;
			//note: don't use else if here
			//for case[2,null,1], t2 will never get assigned if use "else if"
			if(t1 != null && pre.val > root.val)
				t2 = root;
		}
		pre = root;
		traverse(root.right);
	}
}
