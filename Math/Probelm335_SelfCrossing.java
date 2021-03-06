/**
 * Created by vcoder on 4/13/16.
 */

/*You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.

Example 1:
Given x = [2, 1, 1, 2],
┌───┐
│   │
└───┼──>
    │

Return true (self crossing)
Example 2:
Given x = [1, 2, 3, 4],
┌──────┐
│      │
│
│
└────────────>

Return false (not self crossing)
Example 3:
Given x = [1, 1, 1, 1],
┌───┐
│   │
└───┼>

Return true (self crossing)*/

/*my analysis
* there are three cases that cause crossing
* i cross with i-3
* i cross with i-4
* i cross with i-5
* all other crossing can be transfered as abouve thress cases
* more detials see the notablity note*/

public class Probelm335_SelfCrossing {
    public boolean isSelfCrossing(int[] x) {
        int len = x.length;

        if(len <= 3)
            return false;

        for(int i = 3; i < len; i++){
            if(x[i] >= x[i-2] && x[i-1] <= x[i-3]){
                // System.out.println("1" + " " + i);
                return true;
            }
            if(i >= 4){
                if(x[i-1] == x[i-3] && x[i] + x[i-4] >= x[i-2]){
                    // System.out.println("2" + " " + i);
                    return true;
                }
            }
            if(i >= 5){
                if(x[i] + x[i-4] >= x[i-2] && x[i-1] >= x[i-3] - x[i-5] && x[i-1] <= x[i-3] && x[i-2]>= x[i-4]){
                    // System.out.println("3" + " " + i);
                    return true;
                }
            }
        }

        return false;
    }
}
