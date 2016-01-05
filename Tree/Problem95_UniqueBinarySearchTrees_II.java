import java.util.ArrayList;
import java.util.List;

/*Given n, generate all structurally unique BST's 
 * (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3*/

/*My analysis
 * method 1 recursion
 * remember the code
 * 
 * method 2 DP
 * ts[i] stores the all tree of UniqueBinarySearchTrees(i);
 * remember the code*/
public class Problem95_UniqueBinarySearchTrees_II {
	public List<TreeNode> generateTrees_1(int n) {
        List<TreeNode> list = new ArrayList<>();
        if(n == 0)
            return list;
        return buildTree_1(1, n);
    }
	
	private List<TreeNode> buildTree_1(int s,int e){
		List<TreeNode> list = new ArrayList<>();
		if(s > e)
			list.add(null);
		else if(s == e)
			list.add(new TreeNode(s));
		else{
			for(int i = s; i <= e; i++){
				List<TreeNode> left = buildTree_1(s, i-1);
				List<TreeNode> right = buildTree_1(i+1, e);
				for(TreeNode nl: left){
					for(TreeNode nr: right){
						TreeNode root = new TreeNode(i);
						root.left = nl;
						root.right = nr;
						list.add(root);
					}
				}
			}
		}
		return list;
	}
	
	public List<TreeNode> generateTrees_2(int n) {
		List<TreeNode>[] ts = new ArrayList[n+1];
		ts[0] = new ArrayList<>();
		if(n == 0)
			return ts[0];
		ts[0].add(null);
		
		for(int len = 1; len <= n; len++){
			ts[len] = new ArrayList<>();
			for(int i = 1; i <= len; i++){
				for(TreeNode nl: ts[i-1]){
					for(TreeNode nr: ts[len-i]){
						TreeNode root = new TreeNode(i);
						root.left = nl;
						root.right = clone(nr, i);
						ts[len].add(root);
					}
				}
			}
		}
		return ts[n];
	}
	
	public TreeNode clone(TreeNode node, int offset){
		if(node == null)
			return null;
		TreeNode root = new TreeNode(node.val+offset);
		root.left = clone(node.left, offset);
		root.right = clone(node.right, offset);
		return root;
	}
}
