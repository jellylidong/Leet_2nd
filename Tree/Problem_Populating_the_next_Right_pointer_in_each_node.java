import java.util.*;

/*Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. 
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (
ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL*/

/*my analysis
 * 
 * method 1
 * if we don't consider space requirement, we can simply use BFS
 * to traversal the tree level by level and connect them 
 * within the iteration.
 * 
 * To meet the O(1) space requirement,  we need to make use of the next pointer
 * note that via the next pointer, we can access the next node of current level
 * so we can connect cur.right to the cur's next node.left by
 * cur.right = cur.left.
 * then cur = cur.next
 * so we can start from the left most node and do above repeatedly*/

public class Problem_Populating_the_next_Right_pointer_in_each_node {
	public void connect_1(TreeLinkNode root) {
         if(root == null)
             return;
         TreeLinkNode pre = null;
         Queue<TreeLinkNode> q = new LinkedList<>();
         q.offer(root);
         while(!q.isEmpty()){
             int size = q.size();
             for(int i = 0; i < size; i++){
                 TreeLinkNode cur = q.poll();
                 if(pre != null)
                     pre.next = cur;
                 pre = cur;
                 if(cur.left != null)
                     q.offer(cur.left);
                 if(cur.right != null)
                     q.offer(cur.right);
             }
             pre.next = null;
             pre = null;
         }
	}
	
	public void connect_2(TreeLinkNode root) {
		if(root == null)
			return;
		TreeLinkNode cur = root;
		while(cur.left != null){
			TreeLinkNode tmp = cur; // store cur to tmp for future use
			cur.left.next = cur.right;
			while(cur.next != null){
				cur.right = cur.next.left;
				cur.next.left.next = cur.next.right;
				cur = cur.next;
			}
			cur = tmp.left;
		}
	}
}
