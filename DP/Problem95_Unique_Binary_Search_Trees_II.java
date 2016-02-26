/*Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3*/

/*my analysis
* res[n] is all the BSTs composed from [0...n-1]
*
* note the clone method, we can see that
* the structure of the trees are the same but the value can be different
* so we must use a offset to revise the values only for right sub trees*/

import java.util.*;
public class Problem95_Unique_Binary_Search_Trees_II {
	public List<TreeNode> generateTrees(int n) {
		List<TreeNode>[] res = new List[n+1];
		res[0] = new ArrayList<TreeNode>();
		res[0].add(null);
		for(int i = 1; i <= n; i++){
			res[i] = new ArrayList<TreeNode>();
			for(int j = 0; j < i; i++){
				for(TreeNode left: res[j]){
					for(TreeNode right: res[i-j-1]){
						TreeNode root = new TreeNode(j+1);
						root.left = left;
						root.right= clone(right, j+1);
						res[i].add(root);
					}
				}
			}
		}
		return res[n];
	}
	
	public TreeNode clone(TreeNode root, int offset){
		if(root == null)
			return null;
		TreeNode newRoot = new TreeNode(root.val + offset);
		newRoot.left = clone(root.left, offset);
		newRoot.right= clone(root.right, offset);
		return newRoot;
	}
}
