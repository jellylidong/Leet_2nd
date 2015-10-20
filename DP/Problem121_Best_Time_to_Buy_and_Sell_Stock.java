
public class Problem121_Best_Time_to_Buy_and_Sell_Stock {
	public int maxProfit_v1(int[] prices) {
        if(prices.length == 0)
            return 0;
        int curMin = prices[0];
        int curMax = prices[0];
        int maxId = 0;
        int minId = 0;
        int profit = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= curMax){
                curMax = prices[i];
                maxId = i;
            }
            if(prices[i] <= curMin){
                curMin = prices[i];
                minId = i;
            }
            if(minId > maxId){
                maxId = minId;
                curMax = curMin;
            }
            profit = Math.max(profit, curMax-curMin);
        }
        return profit;
    }
	
	public int maxProfit_v2(int[] prices) {
        if(prices.length == 0)
            return 0;
        int minPrice = prices[0];
        int maxProf  = 0;
        for(int i = 0; i < prices.length; i++){
            minPrice = Math.min(minPrice, prices[i]);
            maxProf  = Math.max(maxProf, prices[i]-minPrice); //minPirce 's Id is always <= i
        }
        return maxProf;
    }
}
