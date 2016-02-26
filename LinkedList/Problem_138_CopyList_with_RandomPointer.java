/**
 * Created by vcoder on 2/25/16.
 * A linked list is given such that
 * each node contains an additional random pointer
 * which could point to any node in the list or null.

 Return a deep copy of the list.
 */

/*my analysis
* insert a node after each node of original linkedlist
* after all nodes a inserted:
* update the random pointer of the inserted nodes by:
 * cur.next.randome = cur.random.next
 * cur = cur.next.next
 *
 * extract the new node from the linkedlist*/
public class Problem_138_CopyList_with_RandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode cur = head;
        while(cur != null){
            RandomListNode next = cur.next;
            cur.next = new RandomListNode(cur.label);
            cur = cur.next;
            cur.next = next;
            cur = next;
        }

        cur = head;
        while(cur != null){
            if(cur.random != null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        cur = head;
        RandomListNode h = new RandomListNode(0);
        RandomListNode newCur = h;
        while(cur != null){
            RandomListNode next = cur.next.next;
            newCur.next = cur.next;
            newCur = newCur.next;
            cur.next = next;
            cur = next;
        }
        // newCur.next = null;
        // cur.next = null;

        return h.next;
    }
}
