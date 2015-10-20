
public class Problem221_Maximal_Square {
	public int maximalSquare(char[][] m) {
        if(m.length == 0 || m[0].length == 0)
            return 0;
        int y = m.length;
        int x = m[0].length;
        
        int[][] res = new int[y+1][x+1];
        int max = 0;
        
        for(int i = 1; i <= y; i++){
            for(int j = 1; j <= x; j++){
                if(m[i-1][j-1] == '1'){
                    res[i][j] = Math.min(res[i-1][j-1], Math.min(res[i-1][j], res[i][j-1])) + 1;
                    max = Math.max(max, res[i][j]);
                }
            }
        }
        return max*max;
    }
}
