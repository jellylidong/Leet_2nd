import java.util.HashMap;

/*Given inorder and postorder traversal of a tree, 
 * construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.*/

/*my analysis
 * similar to construct tree from inorder and preorder
 * to optimize the running time
 * we can use a hashmap to store the values and its indexes
 * of inorder array into a hashmap
 * instead of searching for the index in for loop*/
public class Problem106_ConstructBinaryTreefromInorderandPostorderTraversal {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        for(int i = 0; i < inorder.length; i++)
            hash.put(inorder[i], i);
        TreeNode root = buildTree(postorder.length-1, 0, inorder.length-1, inorder, postorder, hash);
        return root;
    }
	
	public TreeNode buildTree(int ps, int is, int ie, int[] inorder, int[] postorder, HashMap<Integer, Integer> hash){
		if(ps < 0 || is > ie)
			return null;
		TreeNode root = new TreeNode(postorder[ps]);
		int mid = hash.get(root.val);
		root.right = buildTree(ps-1, mid+1, ie, inorder, postorder, hash);
		root.left  = buildTree(ps-1-(ie-mid), is, mid-1, inorder, postorder, hash);
		return root;
	}
}
