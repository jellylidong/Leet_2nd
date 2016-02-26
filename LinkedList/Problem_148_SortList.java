/**
 * Created by vcoder on 2/25/16.
 * Sort a linked list in O(n log n) time using constant space complexity.
 */

/*my analysis
* separate the linkedlist into two parts and mergesort the two parts recursively
* */
public class Problem_148_SortList {public ListNode sortList(ListNode head) {
    if(head == null || head.next == null) // if there is only one node in the list, without judge head.next == null, will stack overflow
        return head;
    ListNode slow = head;
    ListNode fast = head;
    ListNode pre = head;
    while(fast != null){
        pre = slow;
        slow = slow.next;
        fast = fast.next;
        if(fast != null)
            fast = fast.next;
    }
    pre.next = null;
    ListNode l1 = sortList(head);
    ListNode l2 = sortList(slow);
    return merge(l1, l2);
}

    public ListNode merge(ListNode l1, ListNode l2){
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while(l1 != null || l2 != null){
            while(l1 != null && l2 != null){
                if(l1.val < l2.val){
                    cur.next = l1;
                    l1 = l1.next;
                }
                else{
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
            while(l1 != null){
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }
            while(l2 != null){
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        return head.next;
    }

}
