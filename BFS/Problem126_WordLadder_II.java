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

/*method 2
similar to method 1, we need to generate a graph that has key =  some word and val =  a list of children words
to build the graph:
* use two sets, one set is used to go forward, i.e we generate all possible children words
* from this set, the other one is used to go backward, i.e we generate possible parents words
* from this set, once the two set have common words, the graph is build up for the shortest path
* from begin word to end word*/

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

	public List<List<String>> findLadders_method_2(String begin, String end, Set<String> dict) {
		HashSet<String> s1 = new HashSet<>();
		HashSet<String> s2 = new HashSet<>();

		//forward set has begin word intially
		//backward set has end word initially
		s1.add(begin);
		s2.add(end);

		HashMap<String, ArrayList<String>> graph = new HashMap<>();
		buildMap(s1, s2, dict, graph, false);

		//ans the is finally answer
		//sol store each solution, i.e the shortest path
		List<List<String>> ans = new ArrayList<>();
		ArrayList<String> sol = new ArrayList<>();
		sol.add(begin); // each sol must begi with begin word

		genList(graph, begin, end, ans, sol);
		return ans;
	}

	public boolean buildMap(HashSet<String> s1, HashSet<String> s2, Set<String> dict, HashMap<String, ArrayList<String>> graph, boolean flip){
		if(s1.isEmpty())
			return false;

		//no mater forward or backward, we need to seach if one set's element is in the other
		//that is the total time complexity is size1 * size2
		//? the less difference the two sizes has, the faster they will run
		//without this part, we can still get correct results but a little slower
		if(s1.size() > s2.size())
			return buildMap(s2, s1, dict, graph, !flip);

		//we don't need to search these word any more once these word are visited
		dict.removeAll(s1);
		dict.removeAll(s2);

		//done is used to indicate that two sets are crossed
		boolean done = false;
		//newS is used to store the newly generated children or parents
		HashSet<String> newS = new HashSet<>();
		for(String str: s1){
			char[] cs = str.toCharArray();
			for(int i = 0; i < cs.length; i++){
				char pre = cs[i];
				for(char c = 'a'; c <= 'z'; c++){
					cs[i] = c;
					String newStr = new String(cs);

					//flip is used to indicate we are going forward or backward
					//if flip == false, we are going forward,
					//so the key is the original str, and val is the newly generated newStr
					//vice versa
					String key = flip? newStr: str;
					String val = flip? str: newStr;

					ArrayList<String> list = graph.containsKey(key) ? graph.get(key) : new ArrayList<String>();
					if(s2.contains(newStr)){//check if the two set crossed
						done = true;
						list.add(val);
						graph.put(key, list);
					}

					if(!done && dict.contains(newStr)){
						newS.add(newStr);
						list.add(val);
						graph.put(key, list);
					}
				}
				cs[i] = pre;
			}
		}

		return done || buildMap(s2, newS, dict, graph, !flip);
	}

	public void genList(HashMap<String, ArrayList<String>> graph, String begin, String end, List<List<String>> ans, ArrayList<String> sol){
		if(begin.equals(end)){
			ans.add(new ArrayList<String>(sol));
		}
		else if(graph.containsKey(begin)){
			for(String str: graph.get(begin)){
				sol.add(str);
				genList(graph, str, end, ans, sol);
				sol.remove(sol.size()-1);
			}
		}
	}
}
