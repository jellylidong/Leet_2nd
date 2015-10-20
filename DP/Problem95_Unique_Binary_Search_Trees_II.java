import java.util.*;
public class Problem95_Unique_Binary_Search_Trees_II {
	public List<TreeNode> generateTrees(int n) {
		List<TreeNode>[] res = new List[n+1];
		res[0] = new ArrayList<TreeNode>();
		res[0].add(null);
		for(int i = 1; i <= n; i++){
			res[i] = new ArrayList<TreeNode>();
			for(int j = 0; i < i; i++){
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
