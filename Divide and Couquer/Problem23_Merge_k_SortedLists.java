/*Merge k sorted linked lists and return it as one sorted list. 
 * Analyze and describe its complexity.*/

/*my analysis
 * divide and conquer
 * 
 * method 1 non-recursion
 * use queue to implement a level order BFS
 * every time we poll two node from the queue and sort them
 * then put the sorted list back to queue
 * do above repeatedly until there's only one element left in the queue
 * 
 * 
 * method 2 divide and conquer recursion
 * */

import java.util.*;
public class Problem23_Merge_k_SortedLists {
	public ListNode mergeKLists_1(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        Queue<ListNode> q = new LinkedList<>();
        for(ListNode node: lists)
            q.offer(node);
        while(q.size() > 1){
            int size = q.size();
            for(int i = 0; i < size;){
                ListNode n1 = q.poll();
                i++;
                if(!q.isEmpty()){
                    ListNode n2 = q.poll();
                    i++;
                    ListNode helper = new ListNode(0);
                    ListNode cur = helper;
                    
                    while(n1 != null || n2 !=null){
                        if(n1 == null && n2 != null){
                            cur.next = new ListNode(n2.val);
                            cur = cur.next;
                            n2 = n2.next;
                        }
                        else if(n1 != null && n2 == null){
                            cur.next = new ListNode(n1.val);
                            cur = cur.next;
                            n1 = n1.next;
                        }
                        else{
                            int val = 0;
                            if(n1.val > n2.val){
                                val = n2.val;
                                n2 = n2.next;
                            }
                            else{
                                val = n1.val;
                                n1 = n1.next;
                            }
                            cur.next = new ListNode(val);
                            cur = cur.next;
                        }
                    }
                    q.offer(helper.next);
                }
                else
                    q.offer(n1);
            }
        }
        return q.poll();
    }
	
	public ListNode mergeKLists_2(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        return merge(lists, 0, lists.length-1);
    }
    
    public ListNode merge(ListNode[] lists, int s, int e){
        if(s == e)
            return lists[s];
        int m = s + (e-s)/2;
        ListNode n1 = merge(lists, s, m);
        ListNode n2 = merge(lists, m+1, e);
            
        return merge(n1, n2);
    }
    
    public ListNode merge(ListNode n1, ListNode n2){
        ListNode helper = new ListNode(0);
        ListNode cur = helper;
        while(n1 != null || n2 != null){
            if(n1 == null && n2 != null){
                    cur.next = n2;
                    break;
                }
                else if(n1 != null && n2 == null){
                    cur.next = n1;
                    break;
                }
                else{
                    int val = 0;
                    if(n1.val > n2.val){
                        val = n2.val;
                        n2 = n2.next;
                    }
                    else{
                        val = n1.val;
                        n1 = n1.next;
                    }
                    cur.next = new ListNode(val);
                    cur = cur.next;
                }
          }
          return helper.next;
    }
}
