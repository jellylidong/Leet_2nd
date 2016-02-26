import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by vcoder on 2/24/16.
 * Given a non-empty binary search tree and a target value,
 * find k values in the BST that are closest to the target.

 Note:
 Given target value is a floating point.
 You may assume k is always valid, that is: k ≤ total nodes.
 You are guaranteed to have only one unique set of k values
 in the BST that are closest to the target.
 Follow up:
 Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

 Hint:

 Consider implement these two helper functions:
 getPredecessor(N), which returns the next smaller node to N.
 getSuccessor(N), which returns the next larger node to N.
 Try to assume that each node has a parent pointer,
 it makes the problem much easier.
 Without parent pointer we just need to
 keep track of the path from the root to the current node using a stack.
 You would need two stacks to track the path in finding predecessor
 and successor node separately.
 */

/*my analysis
* use two stacks,
* one used to store inorder traversal results until > target (not-inclusive)
* one used to store reversed inorder results until < target (not inclusive)
* pop the the stack with min diff between taget*/
public class Problem_272ClosestBinarySearchTreeValue_II {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<Integer> stk_inorder = new Stack<>();
        inorder(root, false, target, stk_inorder);

        Stack<Integer> stk_reverse = new Stack<>();
        inorder(root, true, target, stk_reverse);

        List<Integer> res = new ArrayList<>();

        while(k-- > 0){
            if(stk_inorder.isEmpty())
                res.add(stk_reverse.pop());
            else if(stk_reverse.isEmpty())
                res.add(stk_inorder.pop());
            else if(Math.abs(stk_inorder.peek() - target) < Math.abs(stk_reverse.peek() - target))
                res.add(stk_inorder.pop());
            else
                res.add(stk_reverse.pop());
        }
        return res;
    }

    private void inorder(TreeNode root, boolean reverse, double target, Stack<Integer> stk){
        if(root == null)
            return;

        if(reverse)
            inorder(root.right, reverse, target, stk);
        else
            inorder(root.left, reverse, target, stk);

        if((reverse && root.val <= target) || (!reverse && root.val > target))
            return;

        stk.push(root.val);

        if(reverse)
            inorder(root.left, reverse, target, stk);
        else
            inorder(root.right, reverse, target, stk);
    }
}
