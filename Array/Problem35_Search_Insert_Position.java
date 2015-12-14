/*Given a sorted array and a target value, 
 * return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0*/

/*my analysis
 * very straightforward*/
public class Problem35_Search_Insert_Position {
	public int searchInsert(int[] nums, int target) {
        int i = 0;
        while(i < nums.length && target > nums[i])
            i++;
        return i;
    }
}
