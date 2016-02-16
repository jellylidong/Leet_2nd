/**
 * Created by vcoder on 1/19/16.
 */

/*The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K)
 was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer.
If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers)
 upon entering these rooms; other rooms are either empty (0's)
 or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible,
the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health
so that he is able to rescue the princess.

For example, given the dungeon below,
the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups,
even the first room the knight enters and the bottom-right room where the princess is imprisoned.*/

/*my analysis
* !!!!!!!! for DP problems, always think from end to begin first
* most cases, problem is difficult because going forward cause
* undicided branches, while going backward can avoid these braches
*
* in this problem, we are require the hp value should be at least 1 in the final position,
* so we can start from the last point, i.e. the hp[r-1][c-1] = max(1, 1-d[r-1][c-1]
*
* at any point d[i][j],
* if we can get this point from right, the hp[i][j] should be max(1, hp[i][j+1]-d[i][j]
* if we can get this point from bottom, the hp[i][j] should be max(1, hp[i+`][j]-d[i][j]
* so hp[i][j] max(1, min(required hp from right, required hp from botton))
* go this until hp[0][0]
* then we get the least initial hp*/

public class Problem174_DungeonGame {
    public int calculateMinimumHP(int[][] d) {
        int row = d.length;
        if(row == 0)
            return 0;
        int col = d[0].length;
        int[][] curL = new int[row][col];



        int res = Integer.MIN_VALUE;
        int l = 0;
        for(int i = row-1; i >= 0; i--){
            for(int j = col-1; j >= 0; j--){
                if(i == row-1 && j == col-1)
                    curL[row-1][col-1] = Math.max(1, 1-d[row-1][col-1]);
                else if(i == row-1 && j != col-1)
                    curL[i][j] = Math.max(1, curL[i][j+1] - d[i][j]);
                else if(i != row-1 && j == col-1)
                    curL[i][j] = Math.max(1, curL[i+1][j] - d[i][j]);
                else
                    curL[i][j] = Math.max(1, Math.min(curL[i][j+1] - d[i][j], curL[i+1][j] - d[i][j]));
            }
        }
        return curL[0][0];
    }
}
