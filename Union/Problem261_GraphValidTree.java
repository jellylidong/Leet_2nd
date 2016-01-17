import java.util.Arrays;

/*Given n nodes labeled from 0 to n - 1 and a 
 * list of undirected edges (each edge is a pair of nodes), 
 * write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], 
return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], 
return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], 
what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: 
“a tree is an undirected graph in which any two vertices 
are connected by exactly one path. In other words, 
any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will 
appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.*/

/*my analysis
 * 
 * topology sort can not solve circle like
 *       0
 *   |/_   _\|
 *   1------>2
 *   
 *   5
[[0,1],[0,2],[1,2],[2,3],[2,4]]

use union-find
use an array to store the farthest connected node of i so far
if currently we don't get any further node of node i, then find(i) = i
i.e so far the farthest node i can connect to is itself
for example if we have [a, b], [b, c]
first we check [a, b]
we can get find(a) = a, find(b) = b;
then we can get numa[a] = b so far;

then [b,c]
since we can see so far b can connect to c
so find(b) = b, find(c) = c
then we get num[b] = c so far

if each [v1, v2]
if we get find(v1) = find(v2) it mean the above  0->1, 0->2, 1-> 2 happens
so return wrong

other wise notice that to make tree from n nodes,
there will need exactly n-1 edges
so return n-1 == edges.length


num[a] = d
*/
public class Problem261_GraphValidTree {
	 public boolean validTree(int n, int[][] edges) {
		 int[] next = new int[n];
				 Arrays.fill(next, -1);
		 for(int i = 0; i < edges.length; i++){
			 int x = find(edges[i][0], next);
			 int y = find(edges[i][1], next);
			 
			 if(x == y)
				 return false;
			 next[x] = y;
		 }
		 return n-1 == edges.length;
	 }
	 
	 private int find(int i, int[] next){
		 if(next[i] == -1)
			 return i;
		 return find(next[i], next);
	 }
}
