/*Say you have an array for which the ith element 
 * is the price of a given stock on day i.

Design an algorithm to find the maximum profit. 
You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time 
(ie, you must sell the stock before you buy again).*/

/*method 1
 * O(n) space, O(n) time
 * profit[k][ii] is the profit after kth transaction until iith day (exclusive)
 * profit[k][ii] = max(profit[k][ii-1], price[ii] - price[jj] + max(profit[k-1][jj])
 * jj is in range of [0, ii)
 * = max(profit[k][ii-1], price[ii] + max(profit[k-1][jj] - price[jj])
 * profit[k][0] = 0 because no prices, no profit
 * profit[0][x] = 0 because no transaction no profit*/
public class Problem123_BestTime_to_Buy_and_SelStockIII {
	public int maxProfit(int[] price) {
		if(price.length == 0)
			return 0;
		int K = 2;
		int[][] profit = new int[K+1][price.length];
		int maxProf = 0;
		
		for(int kk = 1; kk <= K; kk++){
			int tmpMax = profit[kk][0] - price[0];
			for(int jj = 1; jj < price.length; jj++){
				profit[kk][jj] = Math.max(profit[kk][jj-1], price[jj] + tmpMax);
				tmpMax = Math.max(tmpMax, profit[kk][jj] - price[jj]);
				maxProf = Math.max(maxProf, profit[kk][jj]);
			}
		}
		return maxProf;
	}
	
	
}
