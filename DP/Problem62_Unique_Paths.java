
public class Problem62_Unique_Paths {
	public int uniquePaths_DP(int m, int n) {
        int[][] res = new int[m+1][n+1];
        res[1][1] = 1;
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(i != 1 && j != 1)
                    res[i][j] = res[i-1][j] + res[i][j-1];
            }
        }
        return res[m][n];
    }
	
	public int uniquePaths_CNK(int m, int n) {
        int N = m+n-2; // total steps
        int K = n-1; // steps that go down
        double res = 1.0; // then the result would be C(N, K), i.e choose K step to go down from all steps
        for(int i = 1; i <= K; i++)
            res = res*(N-K+i)/i;
        return (int)res;
    }
}
