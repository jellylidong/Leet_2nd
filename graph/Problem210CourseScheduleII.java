/*There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
Show Company Tags
Show Tags
Show Similar Problems
*/


/*the problems are similar to 207
 * just store the topology sort result*/
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem210CourseScheduleII {
	public int[] findOrder(int num, int[][] pre) {
        int count = 0;
        ArrayList[] graph = new ArrayList[num];
        Stack<Integer> stk = new Stack<>();
        int[] in = new int[num];
        
        for(int i = 0; i < num; i++)
            graph[i] = new ArrayList<Integer>();
        for(int i = 0; i < pre.length; i++){
            in[pre[i][0]]++;
            graph[pre[i][1]].add(pre[i][0]);
        }
        for(int i = 0; i < num; i++){
            if(in[i] == 0)
                stk.push(i);
        }
        List<Integer> res = new ArrayList<>();
        while(!stk.isEmpty()){
            int n = stk.pop();
            res.add(n);
            for(int i = 0; i < graph[n].size(); i++){
                in[(int)graph[n].get(i)]--;
                if(in[(int)graph[n].get(i)] == 0)
                    stk.push((int)graph[n].get(i));
            }
        }
        if(res.size() == num){
            int[] resarray = new int[num];
            for(int i = 0; i < num; i++){
                resarray[i] = res.get(i);
            }
            return resarray;
        }
        return new int[num];
            
    }
}
