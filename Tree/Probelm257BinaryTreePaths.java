import java.util.*;

/*Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]*/

/*remember the code !!!!!!1
* only add new path when current node has no children
* having child(ren) means current node is not a leaf node*/

public class Probelm257BinaryTreePaths {
	List<String> pathes;
    public List<String> binaryTreePaths(TreeNode root) {
    	pathes = new ArrayList<>();
    	if(root == null)
    		return pathes;
    	buildPath(root, "");
    	return pathes;
    }
    
    public void buildPath(TreeNode root, String path){
    	if(root.left == null && root.right == null)
    		pathes.add(path + root.val);
    	if(root.left != null)
    		buildPath(root.left, path + root.val + "->");
    	if(root.right != null)
    		buildPath(root.right, path + root.val + "->");
    }
}
