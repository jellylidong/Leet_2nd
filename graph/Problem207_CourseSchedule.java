/*There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, 
for example to take course 0 you have to first take course 1, 
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, 
is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, 
and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, 
not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph.
 If a cycle exists, no topological ordering exists and 
 therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) 
on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
Show Company Tags
Show Tags
Show Similar Problems
*/

/*my analysis
 * note that the int[][] pre is N*2 array
 * where int[i][0] is a course and int[i][1] is this course's pre-requirement
 * we can transform the array to graph use arraylist array
 * where graph[i] is contains all pre-required courses of course i
 * 
 * method_1 use DFS
 * 	we use a visited boolean array to recorder if the course is visited
 * if canFinish is true, it means there's no circle in the graph, so every node
 * we see in DFS is un-visited, otherwise, there's a circle in the graph
 * which mean we can not finish the courses
 * 
 * iterate every node in the graph i.e. graph[i]
 * dfs this node: 
 * for(int i = 0; i < num; i++)
 * 	  dfs(graph, visited, i);
 * in the dfs,
 * first judge if this node has been visited, if visited ==> return false
 * else lable this node as visited
 * then iterate all the element of graph[i] one by one
 * for(int j = 0; j < graph[i].size(); i++)
 * 		if(!dfs(graph, visited, graph[i].get(i))
 * 				return false;
 * after dfs from current node done
 * change back this node's visted status visted[i] = false;
 * return true
 * */

/*method 2 use BFS to implement topology sort
 * the steps of topology sort by BFS as following:
 * 1.find all node have 0 in-degree and store them
 * (stack, queue, arrayList ... all are ok, order doesn't matter)
 * 2. iterate there 0-in-degree node, let's assume this node is Node
 * 3. go through all adjacent nodes of Node and reduce the adjacent nodes'
 * in-deree by 1, if then indegree == 0, store this node
 * 4.then go to 2 until the stack is empty
 * 
 * notice that only node with 0 in-degree will be processed
 * so if there is a circle in the graph initially, the circle's node
 * will never be processed, so we can use a counter to count the number of
 * nodes that the stack pops,
 * then return num == counter*/
import java.util.*;

public class Problem207_CourseSchedule {
	 public boolean canFinish_dfs(int num, int[][] pre) {
		 ArrayList[] graph = new ArrayList[num];
		 for(int i = 0; i < num; i++)
			 graph[i] = new ArrayList<Integer>();
		 for(int i = 0; i < pre.length; i++)
			 graph[pre[i][1]].add(pre[i][0]);
		 boolean[] visited = new boolean[num];
		 for(int i = 0; i < num; i++){
			 if(!dfs(graph, visited, i))
				 return false;
		 }
		 return true;
	 }
	 
	 private boolean dfs(ArrayList[] graph, boolean[] visited, int n){
		 if(visited[n])
			 return false;
		 visited[n] = true;
		 for(int i = 0; i < graph[n].size(); i++){
			 if(!dfs(graph, visited, (int)graph[n].get(i)))
				 return false;
		 }
		 return true;
	 }
	 
	 public boolean canFinish_bfs(int num, int[][] pre) {
		 ArrayList[] graph = new ArrayList[num];
		 for(int i = 0; i < num; i++)
			 graph[i] = new ArrayList<Integer>();
		 int[] inDegree = new int[num];
		 for(int i = 0; i < pre.length; i++){
			 inDegree[pre[i][0]]++;
			 graph[pre[i][1]].add(pre[i][0]);
		 }
		 
		 Stack<Integer> stk = new Stack<>();
		 for(int i = 0; i < num; i++){
			 if(inDegree[i] == 0)
				 stk.push(i);
		 }
		 int count = 0;
		 while(!stk.isEmpty()){
			 count++;
			 int n = stk.pop();
			 for(int i = 0; i < graph[n].size(); i++){
				 inDegree[(int)graph[n].get(i)]--;
				 if(inDegree[(int)graph[n].get(i)] == 0)
					 stk.push((int)graph[n].get(i));
			 }
		 }
		 return count == num;
	 }
}
