/*Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: 
â€œThe lowest common ancestor is defined between two nodes v and w 
as the lowest node in T that has both v and w as descendants 
(where we allow a node to be a descendant of itself).â€�

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
Another example is LCA of nodes 5 and 4 is 5, 
since a node can be a descendant of itself according to the LCA definition.*/

/*my analysis
 * remember the code
 * see the comment of the code
 * 
 * another solution is
 * first inorder traversal the tree and store the results in
 * then post order traversal the tree and store the results post
 * then find the number between the two node of in
 * the the number has biggest index of post is the LCA*/
public class Problem236_LowestCommonAncestor_of_a_BinaryTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		
		if(root == null || p == root || q == root)
			return root;
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		//if (sub)tree contains both of the node
		//it means current root is the LCA
		//if some node is not in the (sub)tree, left or right == null
		//return the other one
		if(left == null)
			return right;
		if(right == null)
			return left;
		return root;
	}
}
