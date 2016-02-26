/*Given preorder and inorder traversal of a tree, 
 * construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.*/

/*my analysis
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * inorder sequence: D B E A F C
Preorder sequence: A B D E C F

In a Preorder sequence, leftmost element is the root of the tree. 
So we know ‘A’ is root for given sequences. 
By searching ‘A’ in Inorder sequence, 
we can find out all elements on left side of ‘A’ are 
in left subtree and elements on right are in right subtree. 
So we know below structure now.

                 A
               /   \
             /       \
           D B E     F C
We recursively follow above steps and get the following tree.

         A
       /   \
     /       \
    B         C
   / \        /
 /     \    /
D       E  F
Algorithm: buildTree()
1) Pick an element from Preorder. 
Increment a Preorder Index Variable (preIndex in below code) 
to pick next element in next recursive call.
2) Create a new tree node tNode with the data as picked element.
3) Find the picked element’s index in Inorder. Let the index be inIndex.
4) Call buildTree for elements before inIndex and make the built tree as left subtree of tNode.
5) Call buildTree for elements after inIndex and make the built tree as right subtree of tNode.
6) return tNode.*/
public class Problem105ConstructBinaryTree_from_Preorder_and_InorderTraversal {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(0, 0, inorder.length-1, inorder, preorder);
    }
    
    private TreeNode buildTree(int preStart, int inStart, int inEnd, int[] inorder, int[] preorder){
        if(preStart > preorder.length-1 || inStart > inEnd)
            return null;
        int num = preorder[preStart];
        TreeNode root = new TreeNode(num);
        int idOfNum = 0;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == num){
                idOfNum = i;
                break;
            }
        }
        root.left = buildTree(preStart+1, inStart, idOfNum-1, inorder, preorder);
        root.right = buildTree(preStart+idOfNum-inStart+1, idOfNum+1, inEnd, inorder, preorder);
        return root;
    }
}
