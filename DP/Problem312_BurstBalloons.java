/**
 * Created by vcoder on 1/19/16.
 */

/*Given n balloons, indexed from 0 to n-1.
Each balloon is painted with a number on it represented by array nums.
You are asked to burst all the balloons.
If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167*/

/*my analysis
* for divide and conquer part, what we need to do is find a position to burst,
* then compute the two sub-part, the problem is,
* initially, if we pick i, we can easily get i-1, i, i+1
* but for the two sub-part, 0..i-1 and i+1..n
* if we pick i-1, the right position would be i+1, because i is already burst
* for sub-sub parts, the left and right of position p can be un-decidable
*
* considering that in dp problems, if going forward is undecidable, we can go backward instead
* so let's start burst from the last one
*
* for the last one, it's left and right must be nums[-1] and nums[length]
* assume we pick position i from (-1, length), then the two sub-part would be
* (-1, i) and (i, length), i.e the following two parts must have these lefts and rights
* we can do this iteratively until left+1 == right, because for tow continuous number,
* we can not pick and number between them so return 0*/

public class Problem312_BurstBalloons {

    public int maxCoins(int[] nums) {
        if(nums.length == 0)
            return 0;
        //extend the original array so that it has two 1 boundaries
        //then we can start from 0 and newArray.length-1 for the last burst position
        int[] ns = new int[nums.length+2];
        for(int i = 0; i < nums.length; i++)
            ns[i+1] = nums[i];
        ns[0] = ns[ns.length-1] = 1;

        //mem[s][e] mean the max value we can get between s and e (both are non-inclusive)
        //note that the length is the ns's length ie original length+2
        int[][] mem = new int[ns.length][ns.length];

        return helper(ns, mem, 0, ns.length-1);

    }

    public int helper(int[] ns, int[][] mem, int s, int e){
        if(s+1 == e)
            return 0;
        if(mem[s][e] != 0)
            return mem[s][e];

        int ans = 0;
        for(int i = s+1; i < e; i++){
            ans = Math.max(ans, ns[s]*ns[i]*ns[e]) + helper(ns, mem, s, i) + helper(ns, mem, i, e);
        }
        mem[s][e] = ans;
        return ans;
    }

}
