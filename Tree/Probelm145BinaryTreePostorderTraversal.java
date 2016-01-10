/*Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?*/

/*method 1 recursion
 *method 2 iteration use stack but need to change the tree
 *method 3, iteration use stack without changing the tree
 * the post order is left, right, mid
 * we use a stack first put mid, then left, then right
 * every poped node will be put at the beginning of list*/

import java.util.*;

public class Probelm145BinaryTreePostorderTraversal {
	List<Integer> res;
    public List<Integer> postorderTraversal_1(TreeNode root) {
        res = new ArrayList<>();
        post(root);
        return res;
    }
    
    public void post(TreeNode root){
        if(root == null)
            return;
        if(root.left != null)
            post(root.left);
        if(root.right != null)
            post(root.right);
        res.add(root.val);
    }
    
    public List<Integer> postorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        TreeNode cur = root;
        
        while(!stk.isEmpty() || cur != null){
            if(cur != null){
                stk.push(cur);
                cur = cur.left;
            }
            else{
                TreeNode tmp = stk.peek();
                if(tmp.right != null){
                    cur = tmp.right;
                    tmp.right = null;//must change the right child to null 
                    				 //after right child is traversed, or it will lead to dead loop
                }
                else
                    res.add(stk.pop().val);
            }
        }
        
        return res;
    }
    
    //pre-order push, pop and add to list in reversed order
    public List<Integer> postorderTraversal_3(TreeNode root) {
        
        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        if(root == null)
            return res;
        else
            stk.push(root);
        while(!stk.isEmpty()){
            TreeNode tmp = stk.pop();
            res.add(0, tmp.val);
            if(tmp.left != null)
                stk.push(tmp.left);
            if(tmp.right != null)
                stk.push(tmp.right);
        }
        
        return res;
    }
}
