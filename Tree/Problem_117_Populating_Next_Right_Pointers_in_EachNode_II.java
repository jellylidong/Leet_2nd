/**
 * Created by vcoder on 2/24/16.
 * Follow up for problem "Populating Next Right Pointers in Each Node".

 What if the given tree could be any binary tree? Would your previous solution still work?

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
 */
public class Problem_117_Populating_Next_Right_Pointers_in_EachNode_II {
    public void connect(TreeLinkNode root) {
        TreeLinkNode head=root;//The left most node in the lower level
        TreeLinkNode prev=null;//The previous node in the lower level
        TreeLinkNode curr=null;//The current node in the upper level
        while (head!=null){
            curr=head;
            prev=null;
            head=null;
            while (curr!=null){
                if (curr.left!=null){
                    if (prev!=null)
                        prev.next=curr.left;//if we have a pre node, pre.next = cur.left
                    else
                        head=curr.left;//if we don't have a pre node, it means cur.left should be the head of next level
                    prev=curr.left; //update the pre anyway
                }
                if (curr.right!=null){
                    if (prev!=null)
                        prev.next=curr.right;
                    else
                        head=curr.right;
                    prev=curr.right;
                }
                curr=curr.next;
            }
        }
    }
}
