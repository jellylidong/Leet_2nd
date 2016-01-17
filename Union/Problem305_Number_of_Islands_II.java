/*A 2d grid map of m rows and n columns is initially filled with water.
 *  We may perform an addLand operation which turns the water 
 *  at position (row, col) into a land. Given a list of positions to operate, 
 *  count the number of islands after each addLand operation. 
 *  An island is surrounded by water and is formed by 
 *  connecting adjacent lands horizontally or vertically. 
 *  You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. 
(Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0

We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), 
where k is the length of the positions?*/

/*my analysis
 * we use a union class to implement union find
 * in the union
 * for any grid[i][j], its index in union is i*m + j
 * where m is row length
 * id[p] is the index p's root node value
 * sz[p] is the number of nodes that current node connected*/

import java.util.*;

public class Problem305_Number_of_Islands_II {
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
    	ArrayList<Integer> ans = new ArrayList<>();
    	Union union = new Union(m, n);
    	for(int[] p: positions){
    		int i = p[0];
    		int j = p[1];
    		int p1 = union.index(i, j);
    		union.add(i, j);
    		for(int[] d: dir){
    			int i2 = i+d[0];
    			int j2 = j+d[1];
    			int p2 = union.getId(i2, j2);
    			//if p2 a valid position, i.e it has been add 1
    			//must add 1, but I don't understand why
    			if(p2 > 0 && !union.isCnted(p1, p2)){
    				union.connect(p1, p2);
    			}
    		}
    		ans.add(union.size());
    	}
    	return ans;
    }
    
    private class Union{
    	int m;
    	int n;
    	int count;
    	int[] id;
    	int[] sz;
    	Union(int m, int n){
    		this.m = m;
    		this.n = n;
    		count = 0;
    		id = new int[m*n+1];
    		sz = new int[m*n+1];
    	}
    	
    	public int size(){
    		return count;
    	}
    	
    	public int index(int i, int j){
    		return i*n + j + 1; 
    		// row number * length of col + col mumber + 1
    		//since 
    	}
    	
    	public void add(int i, int j){
    		int p = index(i ,j);
    		id[p] = p;
    		sz[p] = 1;
    		count++;
    	}
    	public int root(int p){
    		while(p != id[p]){
    // 			p = id[p];
    			id[p] = id[id[p]];
    			p = id[p];
    		}
    		return p;
    	}
    	
    	public boolean isCnted(int p1, int p2){
    		return root(p1) == root(p2);
    	}
    	
    	public int getId(int i, int j){
    		if(i >= 0 && i < m && j >= 0 && j < n)
    			return id[index(i, j)];
    		return 0; // when ths position is invalid return 0
    	}
    	
    	public void connect(int p1, int p2){
    		int r1 = root(p1);
    		int r2 = root(p2);
    		if(r1 > r2){
    			id[r2] = r1;
    			sz[r1] += sz[r2];
    		}
    		else{
    			id[r1] = r2;
    			sz[r2] += sz[r1];
    		}
    		count--;
    	}
    }
}
