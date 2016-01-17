import java.util.*;

/*Given two words (beginWord and endWord), 
 * and a dictionary's word list, 
 * find all shortest transformation sequence(s) 
 * from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.*/

/*my analysis
 *BFS to build a graph util find endWord
	at the same time record the level of each node
	fs until the level to find all the pathes*/
public class Problem126_WordLadder_II {
	 public List<List<String>> findLadders(String begin, String end, Set<String> dict) {
	        
	        
	        HashMap<String, ArrayList<String>> graph = new HashMap<>();
	        HashMap<String, Integer> level = new HashMap<>();
	        
	        List<List<String>> ans = new ArrayList<>();
	        ArrayList<String> sol = new ArrayList<>();
	        
	        bfs(dict, graph, level, begin, end);
	        dfs(graph, level, ans, sol, begin, end);
	        
	        return ans;
	        
	    }
	    
	    private void bfs(Set<String> dict, HashMap<String, ArrayList<String>> graph, 
	                     HashMap<String, Integer> level, String begin, String end){
	        Queue<String> q = new LinkedList<>();
	        q.offer(begin);
	        level.put(begin, 0);
	        // graph.put(begin, getNeighbors(begin, dict));
	        graph.put(begin, new ArrayList<String>());
	        boolean found = false;
	        while(!q.isEmpty()){
	            int size = q.size();
	            for(int i = 0; i < size; i++){
	                String cur = q.poll();
	                
	                ArrayList<String> nbs = getNeighbors(cur, dict);
	                int l = level.get(cur);
	                for(String nb: nbs){
	                    graph.get(cur).add(nb);
	                    //make sure the node has not been visited
	                    if(!level.containsKey(nb)){
	                    	//if we need to add this node to the graph
	                    	//first initial its children list
	                    	//its children will be added 
	                    	//if this node is added to queue and retrieved 
	                    	//in the next level bfs
	                        graph.put(nb, new ArrayList<String>());
	                        level.put(nb, l+1);
	                        
	                        if(nb.equals(end))
	                            found = true;
	                        else
	                            q.offer(nb);
	                    }
	                }
	            }
	            //if the endWord is found
	            //we don't need to do BFS anymore
	            //so end it
	            //note: by now the graph includes the endWord
	            //which is in the last level of the graph
	            if(found)
	                break;
	        }
	        
	    }
	    
	    private ArrayList<String> getNeighbors(String cur, Set<String> dict){
	        ArrayList<String> list = new ArrayList<>();
	        char[] cc = cur.toCharArray();
	        for(int i = 0; i < cc.length; i++){
	            char pre = cc[i]; //store the previous value so that we can recover it latter
	            for(char c = 'a'; c <= 'z'; c++){
	                if(c == pre)
	                    continue;
	                cc[i] = c;
	                String newStr = new String(cc);
	                if(dict.contains(newStr))
	                    list.add(newStr);
	            }
	            cc[i] = pre;
	        }
	        return list;
	    }
	    
	    private void dfs(HashMap<String, ArrayList<String>> graph, 
	                     HashMap<String, Integer> level,
	                     List<List<String>> ans,
	                     ArrayList<String> sol,
	                     String cur, String end){
	        sol.add(cur);//add and remove the cur after current dfs done
	        if(end.equals(cur)){
	            ans.add(new ArrayList<String>(sol));
	        }
	        else{
	            for(String nb: graph.get(cur)){
	                if(level.get(cur) + 1 == level.get(nb))
	                    dfs(graph, level, ans, sol, nb, end);
	            }
	        }
	        sol.remove(sol.size()-1);                
	    }
}
