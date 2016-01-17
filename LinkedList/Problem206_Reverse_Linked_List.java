/*Reverse a singly linked list.

click to show more hints.

Hint:
A linked list can be reversed either iteratively or recursively. 
Could you implement both?

Show Company Tags
Show Tags
Show Similar Problems
*/

/*my analysis
 * start from two node and look for the steps*/
public class Problem206_Reverse_Linked_List {
	public ListNode reverseList_iterations(ListNode head) {
		ListNode pre = null;
		ListNode cur = head;
		ListNode next = null;
		
		while(cur != null){
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		
		return pre;
	}
	
	public ListNode reverseList_recursion(ListNode head) {
		
		return reverseList(head, null);
	}
	
	public ListNode reverseList(ListNode head, ListNode newHead){
		if(head == null)
			return newHead;
		ListNode next = head.next;
		head.next = newHead;
		return reverseList(next, newHead);
	}
}
