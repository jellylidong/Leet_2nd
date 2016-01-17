/*You want to build a house on an empty land 
 * which reaches all buildings in the shortest amount of distance. 
 * You can only move up, down, left and right. 
 * You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), 
and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, 
as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. 
If it is not possible to build such house 
according to the above rules, return -1.*/


/*my analysis
 * f
we use a timeVisited array to store the times each node are visited
we use a dist array to store the overall distance of each 
for each 1 node,  we bfs from this node
for the adjacent 0s, we update the dist array by add the distance to current node
i.e. dist[i][j] += d;

thus after all the BFSs are done, what we can get is
dist array stores all the distances summary from each 0 node to all 1 node;
timeVisited array stores the time of each node are visited

so if the timeVisited of this node equals the number of 1 node, it means
this node can be a candidate of what we are looking for
we just need to find the minimum value from the candidate(s)

note meanwhile BFS
we must BFS node-level by node-level, 
i.e.every time we poll a node from the queue, we must go through all its 
neighbors and then 
!!! update the d by d++
then begin the next node-level search
this can make sure very time we update the dist array, correct value are used*/

import java.util.*;

public class Problem317_ShortestDistance_from_All_Buildings {
	public int shortestDistance(int[][] grid) {
        int row = grid.length;
        if(row == 0)
            return -1;
        int col = grid[0].length;
        int[][] timeVisited = new int[row][col];
        int[][] dist = new int[row][col];
//        boolean[][] visited = new boolean[row][col];
        
        //count the number of 1 node
        int count = 0;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(grid[r][c] == 1){
                    
                    count++;
                    
                    Queue<pos> q = new LinkedList<>();
                    int d = 0;
                    boolean[][] visited = new boolean[row][col];
                    q.offer(new pos(r, c));
                    
                    while(!q.isEmpty()){
                        int size = q.size();
                        
                        //node-level BFS 
                        for(int i = 0; i < size; i++){
                            pos cur = q.poll();
                            int x = cur.x;
                            int y = cur.y;
                            dist[x][y] += d;
                            timeVisited[x][y]++;
                            
                            if(x > 0 && grid[x-1][y] == 0 && !visited[x-1][y]){
                                visited[x-1][y] = true;
                                q.offer(new pos(x-1, y));
                            }
                            if(y > 0 && grid[x][y-1] == 0 && !visited[x][y-1]){
                                visited[x][y-1] = true;
                                q.offer(new pos(x, y-1));
                            }
                            if(x < row-1 && grid[x+1][y] == 0 && !visited[x+1][y]){
                                visited[x+1][y] = true;
                                q.offer(new pos(x+1, y));
                            }
                            if(y < col-1 && grid[x][y+1] == 0 && !visited[x][y+1]){
                                visited[x][y+1] = true;
                                q.offer(new pos(x, y+1));
                            }
                        }
                        d++;   
                    }
                }
            }
        }
        
        int res = Integer.MAX_VALUE;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
            	//timeVisited[r][c] == count mean this node is visited count times
            	//i.e this node can be accessed by all 1 node
                if(grid[r][c] == 0 && timeVisited[r][c] == count)
                    res = Math.min(res, dist[r][c]);
            }
        }
        
        return res == Integer.MAX_VALUE? -1: res;
    }
    
    public class pos{
        int x;
        int y;
        pos(int xx, int yy){
            x = xx;
            y = yy;
        }
    }
}
