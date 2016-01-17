import java.util.Arrays;

/*Given n nodes labeled from 0 to n - 1 and 
 * a list of undirected edges (each edge is a pair of nodes), 
 * write a function to find the number of 
 * connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, 
[0, 1] is the same as [1, 0] 
and thus will not appear together in edges.*/

/*my anaylsis
 * this problem is very similar to the number of island
 * the only difference is in Num of Island, we are given a
 * 2d array, we have to transform it into an one D array py
 * p = i*n + j to store  the union, 
 * in this problem, we are given only the connection edges
 * so we can build the union based on the edges directly,
 * i.e. for edges [p1, p2]
 * union.add(p1)
 * union.add(p2)
 * if not union.isCnted(p1, p2)
 * 	connect(p1, p2)
 * 
 * note that when we are given n nodes, but if the given edges
 * don't have node i, then we would get results without i,
 * which is obviously wrong, so we need to add all node to the union before
 * doing above add and connect steps
 * 
 * */
public class Problem323NumberofConnectedComponentsinanUndirectedGraph {
	public int countComponents(int n, int[][] edges) {
        Union union = new Union(n);
        for(int i = 0; i < n; i++)
            union.add(i);
        for(int[] e: edges){
            union.add(e[0]);
            union.add(e[1]);
            if(!union.isCnted(e[0], e[1]))
                union.connect(e[0], e[1]);
        }
        return union.size();
    }
    
    private class Union{
        int count;
        int[] root;
        int[] sz;
        
        Union(int n){
            count = 0;
            root = new int[n+1];
            //set all initial value as -1 so that
            //when we get a node with -1 root value
            //we can know that this node has not been
            //added to the union
            Arrays.fill(root, -1);
            sz = new int[n+1];
        }
        
        // private int getId(int p){
            
        // }
        
        private void add(int p){
            if(root[p] == -1){
                root[p] = p;
                sz[p] = 1;
                count++;
            }
        }
        private int root(int p){
            while(p != root[p]){
                root[p] = root[root[p]];
                p = root[p];
            }
            return p;
        }
        private boolean isCnted(int p1, int p2){
            return root(p1) == root(p2);
        }
        private void connect(int p1, int p2){
            int r1 = root(p1);
            int r2 = root(p2);
            if(r1 > r2){
                root[r2] = root[r1];
                sz[r1] += sz[r2];
            }
            else{
                root[r1] = root[r2];
                sz[r1] = sz[r1];
            }
            count--;
        }
        private int size(){
            return count;
        }
    }
}
