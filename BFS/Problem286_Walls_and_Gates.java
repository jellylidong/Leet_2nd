/*You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. 
We use the value 231 - 1 = 2147483647 to represent INF 
as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. 
If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4*/


/*my analysis
 * 1.first put all gate(i.e. 0) into a queue
 * 
 * 2.then poll the queue one by one
 * for each poll
 * if the neighbors of polled node is a INF,
 * just update it with node's val +1
 * and then put this updated node into the queue
 * 
 * do the above step 2 repeatedly until the queue is empty*/

import java.util.*;
public class Problem286_Walls_and_Gates {
	public void wallsAndGates(int[][] rooms) {
        final int INF = 2147483647;
        // int[] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<pos> q = new LinkedList<>();
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                if(rooms[i][j] == 0)
                    q.offer(new pos(i, j));
            }
        }
        while(!q.isEmpty()){
            pos cur = q.poll();
            if(cur.i > 0 && rooms[cur.i-1][cur.j] == INF){
                rooms[cur.i-1][cur.j] = rooms[cur.i][cur.j] + 1;
                q.offer(new pos(cur.i-1, cur.j));
            }
                
            if(cur.i < rooms.length-1 && rooms[cur.i+1][cur.j] == INF){
                rooms[cur.i+1][cur.j] = rooms[cur.i][cur.j] + 1;
                q.offer(new pos(cur.i+1, cur.j));
            }
            
            if(cur.j > 0 && rooms[cur.i][cur.j-1] == INF){
                rooms[cur.i][cur.j-1] = rooms[cur.i][cur.j] + 1;
                q.offer(new pos(cur.i, cur.j-1));
            }
            
            if(cur.j < rooms[0].length-1 && rooms[cur.i][cur.j+1] == INF){
                rooms[cur.i][cur.j+1] = rooms[cur.i][cur.j] + 1;
                q.offer(new pos(cur.i, cur.j+1));
            }
        }
    }
    
   
    
    public class pos{
        int i;
        int j;
        pos(int xx, int yy){
            i = xx;
            j = yy;
        }
    }
}
