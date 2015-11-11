/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time (
ie, you must sell the stock before you buy again).*/

/*method 1 has simple code, but I don't understand why
 * the basic idea is to make a transaction as long as the profit is positive*/

/*method 2 is to traversal all the prices, 
 * every time we 1st find the minimum prices and the update i
 * and continue to  find maximum price and update i
 * until we traversal all the prices
 * note: <= or >=, even equal to previous price,
 * still need to go forward*/
public class Problem122_Best_Time_to_Buy_and_Sell_Stock_II {
	public int maxProfit(int[] prices){
		int res = 0;
		for(int i = 1; i < prices.length; i++){
			if(prices[i] -prices[i-1] > 0)
				res += prices[i] -prices[i-1];
		}
		return res;
	}
	
	public int maxProfit_method2(int[] prices){
		int res = 0;
		int i = 0;
		while(i < prices.length){
			while(i < prices.length && prices[i+1] <= prices[i])
				i++;
			int min = prices[i];
			i++;
			while(i < prices.length && prices[i+1] >= prices[i])
				i++;
			res += i < prices.length? prices[i]-min: 0;
			i++;
		}
		return res;
	}
}
