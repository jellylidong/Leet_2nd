
public class TEST {
	public static int maxProfit(int[] p) {
        int k = 2;
        int l = p.length;
        if(l <= 1)
            return 0;
        int[][] profit = new int[k+1][l];
        int res = 0;
        
        for(int i = 1; i <= k; i++){
            int tmpMax = profit[k-1][0] - p[0];
            
            for(int j = 1; j < p.length; j++){
                profit[k][j] = Math.max(profit[k][j-1], p[j]+tmpMax);
                tmpMax = Math.max(tmpMax, profit[k-1][j] - p[j]);
                res = Math.max(res, profit[k][j]);
                //System.out.println(profit[k][j-1] + " " + p[j]+" " +tmpMax + " " +res);
            }
        }
        return res;
    }
    
	public static int maxProfit2(int[] prices) {
		if (prices.length <= 1) return 0;
        else {
            int K = 2; // number of max transation allowed
            int maxProf = 0;
            int[][] f = new int[K+1][prices.length];
            
            for (int kk = 1; kk <= K; kk++) {
                int tmpMax = f[kk-1][0] - prices[0];
                
                for (int ii = 1; ii < prices.length; ii++) {
                    f[kk][ii] = Math.max(f[kk][ii-1], prices[ii] + tmpMax);
                    tmpMax = Math.max(tmpMax, f[kk-1][ii] - prices[ii]);
                    maxProf = Math.max(f[kk][ii], maxProf);
                }
            }
            return maxProf;
        }
	}
    public static void main(String[] args){
    	int[] p = {2,1,2,0,1};
    	System.out.print(maxProfit2(p));
    }
}
