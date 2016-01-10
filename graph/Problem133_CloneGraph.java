import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/*Clone an undirected graph. Each node in the graph contains 
 * a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , 
as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, 
and therefore contains three parts as separated by #.

First node is labeled as 0. 
Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. 
Connect node 1 to node 2.
Third node is labeled as 2. 
Connect node 2 to node 2 (itself), 
thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
Show Company Tags
Hide Tags*/

/*my analysis
 * method 1 non-recursion, use worse space and time than recursion
 * use two stacks one for original graph and the other for cloned graph
 * these two stack do the completely push and pop
 * 
 * use a counter and a hash map to record when a new node in the graph is visited
 * hashmap.put(node, counter++)
 * at the same time, every time we update the hashmap, we use an arraylist
 * list.add(new node(n.label)) so that when we need to add this node to
 * some other nodes' neighbors, we can easily get it by
 * n_new.neighbors.add(list.get(hashmap.get(n_org.neightbors.get(i)))*/

/*method 2 use recursion
 * we need to use a global hashmap to record 
 * if the node we are currently visiting has been added to the graph
 * if yes, we just need to return the node from hashmap
 * otherwise, we need to create a new node a add it to the hashmap
 * also update this new node's neighbors by
 * n_new,neighbors.add(clone(n_org.neighbor.get(i))*/
public class Problem133_CloneGraph {
	public UndirectedGraphNode cloneGraph_nonRecursion(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, Integer> visited = new HashMap<>();
        ArrayList<UndirectedGraphNode> list = new ArrayList<>();
        int count = 0;
        if(node == null)
            return null;
            
        Stack<UndirectedGraphNode> stk_org = new Stack<>();
        Stack<UndirectedGraphNode> stk_new = new Stack<>();
        
        stk_org.push(node);
        visited.put(node, 0);
        count++;
        stk_new.push(new UndirectedGraphNode(node.label));
        list.add(stk_new.peek());
        
        while(!stk_org.isEmpty()){
            UndirectedGraphNode cur_org = stk_org.pop();
            UndirectedGraphNode cur_new = stk_new.pop();
            
            for(int i = 0; i < cur_org.neighbors.size(); i++){
                UndirectedGraphNode n_org = cur_org.neighbors.get(i);
                
                //if this neighbor has not been visited
                //we need to generate new node for n_new's neighbor
                //also list.add this new generated node
                if(!visited.containsKey(n_org)){
                    stk_org.push(n_org);
                    visited.put(n_org, count++);
                    UndirectedGraphNode n_new = new UndirectedGraphNode(n_org.label);
                    cur_new.neighbors.add(n_new);
                    stk_new.push(n_new);
                    list.add(n_new);
                }
                else{
                	//if visited, just net this neighbor node from the list
                	int id = visited.get(n_org);
                	cur_new.neighbors.add(list.get(id));
                }
                
            }
        }
        
        return list.get(0);
    }
	
	HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
	public UndirectedGraphNode cloneGraph_recursion (UndirectedGraphNode node) {
		if(node == null)
			return null;
		if(map.containsKey(node.label))
			return map.get(node.label);
		UndirectedGraphNode n_new = new UndirectedGraphNode(node.label);
		for(UndirectedGraphNode n: node.neighbors)
			n_new.neighbors.add(cloneGraph_recursion(n));
		
		return n_new;
	}
}
