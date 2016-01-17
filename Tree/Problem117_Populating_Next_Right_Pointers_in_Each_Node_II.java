/*Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? 
Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
Show Company Tags
Show Tags
Show Similar Problems
*/

/*my analysis
 * remember the code !!!!!!!!!!!
 * 
 * we use a head to store the first node of next level of current level
 * we use a pre to store the previous node of current node's children node(s)
 * */
public class Problem117_Populating_Next_Right_Pointers_in_Each_Node_II {
	 public void connect(TreeLinkNode root) {
		 TreeLinkNode head = null;
		 TreeLinkNode pre = null;
		 TreeLinkNode cur = root;
		 
		 while(cur != null){
			 
			 while(cur != null){
				 //if pre is still null, it means we still have not found the head
				 //of next level, so head = cur.child
				 //otherwise we already have the previous node of current node's children
				 //so pre.next = cur.child
				 
				 if(cur.left != null){
					 if(pre == null)
						 head = cur.left;
					 else
						 pre.next = cur.left;
					 pre = cur.left;
				 }
				 
				 if(cur.right != null){
					 if(pre == null)
						 head = cur.right;
					 else
						 pre.next = cur.right;
					 pre = cur.right;
				 }
				 cur = cur.next;
			 }
			 
			 cur = head;
			 head = null;
			 pre = null;
		 }
	 }
}
