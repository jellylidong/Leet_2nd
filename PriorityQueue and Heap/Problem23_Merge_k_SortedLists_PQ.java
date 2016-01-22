import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by vcoder on 1/21/16.
 */

/*Merge k sorted linked lists and return it as one sorted list.
Analyze and describe its complexity.*/

/*MY ANALYSIS
* use a priority q to put all the head node of of the lists
* then put the polled node to the res and put the node.next to the pq*/

public class Problem23_Merge_k_SortedLists_PQ {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;

        Queue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode t1, ListNode t2) {
                return t1.val-t2.val;
            }
        });

        for(ListNode node: lists) {
            if(node == null)
                continue;
            pq.offer(node);
        }

        ListNode helper = new ListNode(0);
        ListNode cur = helper;

        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            cur.next = node;
            if(node.next != null)
                pq.offer(node.next);
            cur = cur.next;
        }
        return helper.next;
    }
}
