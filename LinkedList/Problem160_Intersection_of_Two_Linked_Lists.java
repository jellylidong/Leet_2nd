/**
 * Created by vcoder on 1/28/16.
 */

/*Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.*/

/*my analysis
considering the time and space requirements, it's impossible to find the cross node just
by using hashset.

* this problem actually requires us to find two node of each list that has the same
* distance with the cross the node, i.e a1 and b2 in the above lists, once we find them
* we can simply go to next from each node until their next node are the same
* we can use curA and curB to scan their own list once one of them become null,
* let it start scan from the other list, that is
* for the above example, B has one more node than A before c1, so when curB = c3, curA = null
* we let curA = B, then continue scan, we can see then curB = null, we let it rescan from A,
* so now, curA = b2, curB = a1, they are at the same distance from c1
**/


public class Problem160_Intersection_of_Two_Linked_Lists {
    public ListNode getIntersectionNode(ListNode A, ListNode B) {
        if(A == null || B == null)
            return null;
        ListNode curA = A;
        ListNode curB = B;
        while(curA != curB){
            curA = curA == null? B: curA.next;
            curB = curB == null? A: curB.next;
        }
        return curA;
    }
}
