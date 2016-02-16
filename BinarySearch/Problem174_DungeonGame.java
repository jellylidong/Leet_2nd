///*The demons had captured the princess (P) and imprisoned her
// * in the bottom-right corner of a dungeon.
// * The dungeon consists of M x N rooms laid out in a 2D grid.
// *  Our valiant knight (K) was initially positioned in the top-left room
// *  and must fight his way through the dungeon to rescue the princess.
//
//The knight has an initial health point represented by a positive integer.
//If at any point his health point drops to 0 or below, he dies immediately.
//
//Some of the rooms are guarded by demons, so the knight loses health (negative integers)
//upon entering these rooms; other rooms are either empty (0's) or
//contain magic orbs that increase the knight's health (positive integers).
//
//In order to reach the princess as quickly as possible,
//the knight decides to move only rightward or downward in each step.
//
//
//Write a function to determine the knight's minimum initial health
// so that he is able to rescue the princess.
//
//For example, given the dungeon below, the initial health of the knight must be at least
//7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
//
//-2 (K)	-3	3
//-5	-10	1
//10	30	-5 (P)
//*/
//
///*my anaylsis
//* obviously, this is more like a DP problems, always think backward first
//*
//* in this problem, we are reuqired to find the minimum initial hp
//* so we need to gurantee that each step the hp is  >= 1
//* considering that there are both positive and negative positions,
//* it's hard to make sure our current >= 1 status is caused by the initial hp
// * or just the positive postion, or both
// *
// * but when considering backward, the final value should be >= 1,
// * so if we know after get here from the left or up, the hp is 1,
// * what is the previous minimum hp should be?
// * if we are from up:
// * up hp should be Math.max(1, 1-d[n-2][m-1])
// * if we are from left,
// * left hp should be max(1, 1-d[n-1][m-2])
// *
// * so if we want to the minimum hp for position (i,j)
// * we already know the the min hp of(i-1, j) and (i, j-1)
// * then the min hp[i][i] = min(hp[i-1][j], hp[i][j-1])
// * */
//public class Problem174_DungeonGame {
//    public int calculateMinimumHP(int[][] d) {
//        int x = d.length;
//        if(x == 0)
//            return 0;
//        int y = d[0].length;
//        int[][] hp = new int[x][y];
//
//        for(int i = x-1; i >= 0; i--){
//            for(int j = y-1; j >= 0; j--){
//                if(i == x-1 && j == y-1)
//                    hp[x-1][y-1] = 1;
//                else if(i == x-1)
//                    hp[i][j] = Math.max(1, hp[i][j-1]-d[i][j]);
//                else if(j == y-1)
//                    hp[i][j] = Math.max(1, hp[i-1][j]-d[i][j]);
//                else
//                    hp = Math.min(Math.max(1, hp[i][j-1]-d[i][j]), Math.max(1, hp[i-1][j]-d[i][j])  );
//            }
//        }
//        return hp[0][0];
//    }
//}
