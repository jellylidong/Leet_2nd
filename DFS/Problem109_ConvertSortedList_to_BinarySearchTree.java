/*Given a singly linked list where elements are sorted in ascending order, 
 * convert it to a height balanced BST.*/

/*my analysis, the basic idea is find the middle number,
 * It requires us useing O(n) to make this tree
 * use it as root, left as root.left, root.right = right,
 * what make it hard is for we are given a linkedlist, which makes
 * it use O(i) to access the ith element
 * 
 * to solve this, we set a curNode to pointe at the current node we are accessing
 * remember the code and idea*/
public class Problem109_ConvertSortedList_to_BinarySearchTree {
	ListNode curNode;
    public TreeNode sortedListToBST(ListNode head) {
    	if(head == null)
    		return null;
    	int size = 0;
    	ListNode cur = head;
    	while(cur != null){
    		cur = cur.next;
    		size++;
    	}
    	curNode = head;
    	TreeNode root = helper(0, size-1);
    	return root;
    }
    
    public TreeNode helper(int s, int e){
    	if(s > e)
    		return null;
    	
    	int mid = s + (e-s)/2;
    	TreeNode left = helper(s, mid-1);
    	TreeNode root = new TreeNode(curNode.val);
    	curNode = curNode.next;
    	
    	TreeNode right = helper(mid+1, e);
    	root.left = left;
    	root.right = right;
    	
    	return root;
    }
}
