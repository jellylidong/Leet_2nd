/**
 * Created by vcoder on 2/15/16.
 */

/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit.
You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time
(ie, you must sell the stock before you buy again).*/

/*my analysis
* if k >= len/2
* we can simply make a transaction if p[i]-p[i-1] > 0
* i.e. prof += p[i]-p[i-1]
*
* if k < len/2:
* prof[i][j] means the profit of ith transaction up to time j
* more details see the code
* */

public class Problem188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public int maxProfit(int k, int[] p) {
        int res = 0;

        if(k >= p.length){
            for(int i = 1; i < p.length; i++){
                if(p[i] > p[i-1])
                    res += p[i]-p[i-1];
            }
            return res;
        }

        int[][] prof = new int[k+1][p.length];
        for(int i = 1; i <= k; i++){
            //tmp's initial value means first we buy a stock of day 0
            int tmp = prof[i-1][0]-p[0];
            for(int j = 1; j < p.length; j++){
                prof[i][j] = Math.max(prof[i][j-1], p[j]+tmp);

                //this mean we make ith transaction (or not) based on the (i-1)th  transaction
                tmp = Math.max(tmp, prof[i-1][j-1]-p[j]);
            }
        }
        return prof[k][p.length-1];
    }
}
