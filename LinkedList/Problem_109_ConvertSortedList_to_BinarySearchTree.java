/**
 * Created by vcoder on 2/25/16.
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 */
public class Problem_109_ConvertSortedList_to_BinarySearchTree {
    ListNode node;
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;
        int count = 0;
        ListNode cur = head;
        while(cur != null){
            count++;
            cur = cur.next;
        }
        node = head;
        return helper(0, count-1);
    }

    private TreeNode helper(int s, int e){
        if(s > e)
            return null;
        int m = s + (e-s)/2;
        TreeNode left = helper(s, m-1);
        TreeNode root = new TreeNode(node.val);
        node = node.next;

        TreeNode right = helper(m+1, e);
        root.left = left;
        root.right = right;

        return root;
    }
}
