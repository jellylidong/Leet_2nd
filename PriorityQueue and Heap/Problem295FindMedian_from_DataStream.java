import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by vcoder on 1/21/16.
 */

/*Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value.
So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3)
findMedian() -> 2*/

/*my analysis
* for any sorted array, if it's length is even,
* we can split it into two equal part [0..i] and [i+1, len-1]
* we can see that median = (nums[i] + nums[i+1})/2
* i.e the max number of part1 and min number of part 2
* similarly for odd length array
* if we let part1 have more element, so part1's max number is the median
*
* we can see that what we need in order to get the median is only the max and min of
* two part in the array, so we can use two priority queue
* one q is minQ, which smaller number goes first
* the other is maxQ, which bigger number goes first
* so minQ store the latter part, and maxQ stores the former part
*
* also, considering that there can be odd length, always put the mid number to the former part
* i.e. maxQ
* */

public class Problem295FindMedian_from_DataStream {
    Queue<Long> minQ = new PriorityQueue<>();
    Queue<Long> maxQ = new PriorityQueue<>(1000, Collections.reverseOrder());
    public void addNum(int num) {
        maxQ.offer((long)num);
        //after maxQ get a new number
        //it should poll it's max number and put it to the
        //minQ so that the two Queues have the same number of nums
        //but it can also happens when for example
        //maxQ.size() = 3 after add a new number and minQ has 2 numbers before offer
        //if we poll a number from maxQ and put it to the minQ
        //then maxQ.size() = 2, minQ.size() = 2
        //since we always put the median to the former part,
        //i.e.maxQ, so we need to undo this
        //just poll a number from minQ and put it to the maxQ
        minQ.offer(maxQ.poll());
        if(maxQ.size() < minQ.size())
            maxQ.offer(minQ.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(maxQ.size() == minQ.size())
            return (maxQ.peek() + minQ.peek())/2.0;
        else
            return maxQ.peek();
    }
}
