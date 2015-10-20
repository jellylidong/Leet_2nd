
public class Problem63_Unique_Paths_II {
	public int uniquePathsWithObstacles(int[][] grid) {
        int y = grid.length;
        if(y == 0)
            return 0;
        int x = grid[0].length;
        int[][] res = new int[y][x];
        
        for(int i = 0; i < y; i++){
            for(int j = 0; j < x; j++){
                if(grid[i][j] == 1)
                    res[i][j] = 0;
                else if(i == 0 && j == 0)
                    res[i][j] = 1;
                else if(i == 0 && j != 0)
                    res[i][j] = res[i][j-1];
                else if(i != 0 && j == 0)
                    res[i][j] = res[i-1][j];
                else
                    res[i][j] = res[i-1][j] + res[i][j-1];
            }
        }
        
        return res[y-1][x-1];
    }
}
