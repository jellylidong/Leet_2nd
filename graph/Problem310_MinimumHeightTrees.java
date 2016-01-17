/*For a undirected graph with tree characteristics, 
 * we can choose any node as the root.
 *  The result graph is then a rooted tree. 
 *  Among all possible rooted trees, those with 
 *  minimum height are called minimum height trees (MHTs). 
 *  Given such a graph, write a function to f
 *  ind all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled 
from 0 to n - 1. You will be given the number n 
and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] 
and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Hint:

How many MHTs can a graph have at most?
Note:

(1) According to the definition of tree on Wikipedia: 
“a tree is an undirected graph in which any two vertices are 
connected by exactly one path. In other words, 
any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges 
on the longest downward path between the root and a leaf.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.*/

/*my analysis
 * to build a tree from graph, we only need to pick any node of the graph as root,
 * to make the height of tree as small as possible, notice that for any path of the
 * graph, for example we have path a-b-c-d-e-f, we can only the the minimum height if
 * we choose the middle node(s) as root.
 * So for the whole graph, the root of MHT must be the middle node of the longest path
 * so the results can be 1 or 2
 * 
 * so we can remove node from the out most layer,
 * layer by layer until we can't remove anymore,
 * i.e. the number of remain nodes are 2 or 1
 * 
 * to remove the out most layer, first we find all nodes that has only one edges and store them
 * into a list, then n-list.size(), 
 * then for each node of the list, remove the node from all node that have connection with them
 * 
 * so we need to build a bi-directional graph
 **/

import java.util.*;
public class Problem310_MinimumHeightTrees {
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        ArrayList<HashSet<Integer>> graph = new ArrayList<>();
        if(n == 1){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            return list;
        }
        for(int i = 0; i < n; i++)
            graph.add(new HashSet<Integer>());
        for(int[] e: edges){
            int v1 = e[0];
            int v2 = e[1];
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        ArrayList<Integer> ones = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(graph.get(i).size() == 1)
                ones.add(i);
        }
        while(n > 2){
            n -= ones.size();
            ArrayList<Integer> newOnes = new ArrayList<>();
            for(Integer i: ones){
                for(Integer j: graph.get(i)){
                    graph.get(j).remove(i);
                    if(graph.get(j).size() == 1)
                        newOnes.add(j);
                }
            }
            ones = newOnes;
        }
        return ones;
    }
}
