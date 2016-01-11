/*There is a new alien language which uses the latin alphabet. 
 * However, the order among letters are unknown to you. 
 * You receive a list of words from the dictionary, 
 * where words are sorted lexicographically by the rules of this new language. D
 * erive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, r
eturn any one of them is fine.*/

/*for problems involve graph, the best way to judge if there's a 
 * circle in it and if we can get a sorted order of the nodes from
 * the graph is to use topology sort
 * there are two ways to implement topology sort
 * 1.DFS
 * We need to use a visited array to record if current node has been visited
 * if yes, it means there is a circle in it, so we can not get a sorted 
 * list from the graph
 * otherwise, we continue DFS the children of curent node
 * the fake code is like
 * for(curnode in gaph){
 * 		if(!dfs(curnode)
 * 			return sth
 * }
 * dfs(curnode){
 * 	if(curnode is visited)
 * 		return false;
 * 	for(node in curnode's children list)
 * 		dfs(node);
 * 	return true;
 * }
 * 
 * there are some important small point need to the noticed in this problem
 * see the code for details
 * 
 * 2.BFS
 * We need to use an indegree array to record how many node gose in current node
 * go through the indegree array and push all node has 0 indegree to the stack
 * the keep pop stack util it's empty
 * every pop, we go through the children list of the poped node
 * and reduce the children node's indegree by one
 * if any children node's indegree get 0 after reduction, push this node to stack
 * we also need to counter to count how many time the stack pop
 * it can be seen that if there's a circle in the graph, the circle can never be push to the stack
 * because none of the node of the circle has 0 indegree
 * the fake code is like:
 * 
 * while stack is not empty{
 * 	curnode = stack.pop()
 * 	count++;
 * 	for(node in curnode's children list)
 * 		indegree[node]--;
 * 		if(indegree[node] == 0)
 * 			stk.push(node)
 * }
 * if(count == graph.size())
 * ...
 * else
 * ...
 * 	*/

import java.util.*;

public class Problem269_AlienDictionary {
	public String alienOrder(String[] words) {
        HashSet<Character> charSet = new HashSet<>();
        HashMap<Character, ArrayList<Character>> graph = new HashMap<>();
        genSet(charSet, words);
        initGraph(charSet, graph);
        buildGraph(words, charSet, graph);
        // System.out.println(graph);
        //String ans = topoSort_DFS(graph);
        String ans = topoSort_BFS(graph);
        return ans;
    }
    
    private void genSet(HashSet<Character> charSet, String[] words){
        for(int i = 0; i < words.length; i++){
            for(char c: words[i].toCharArray()){
                charSet.add(c);
            }
        }
    }
    
    private void initGraph(HashSet<Character> charSet, HashMap<Character, ArrayList<Character>> graph){
        for(Character c: charSet){
            graph.put(c, new ArrayList<Character>());
        }
    }
    
    private void buildGraph(String[] words, HashSet<Character> charSet, HashMap<Character, ArrayList<Character>> graph){
        for(int i = 0; i < words.length; i++){
            for(int j = i+1; j < words.length; j++){
                for(int k = 0; k < Math.min(words[i].length(), words[j].length()); k++){
                    char ci = words[i].charAt(k);
                    char cj = words[j].charAt(k);
                    if(ci != cj){
                        charSet.remove(ci);
                        charSet.remove(cj);
                        
                        if(!graph.get(ci).contains(cj))
                            graph.get(ci).add(cj);
                        //once find two chars are different
                        //we can know that word[i] is in front of word[j] 
                        //is because ci is in front of cj, for later chars, we can not know their order
                        //so break once find two different chars
                        break;
                    }
                }
            }
        }
    }
    
    private String topoSort_DFS(HashMap<Character, ArrayList<Character>> graph){
        StringBuilder ans = new StringBuilder();
        HashMap<Character, Boolean> visited = new HashMap<>();
        HashMap<Character, Boolean> alreadyPut = new HashMap<>();
        for(Character c: graph.keySet()){
            visited.put(c, false);
            alreadyPut.put(c, false);
        }
        for(Character c: graph.keySet()){
            if(!dfs(c, visited, alreadyPut, graph, ans))
                return "";
        }
        return ans.reverse().toString();
    }
    
    private boolean dfs(Character c, HashMap<Character, Boolean> visited, HashMap<Character, Boolean> alreadyPut, HashMap<Character, ArrayList<Character>> graph, StringBuilder ans){
        if(visited.get(c))
            return false;
        visited.put(c, true);
        for(Character cc: graph.get(c)){
            //we need to check if current char has already been put to 
        	//string builder because we may have case like
        	//a->(b,c), b->(e)
        	//where dfs from a, b will be put to stringbuilder
        	//after dfs(a) is done, we dfs from b
        	//obviously, if a put b again
        	//the answer would be wrong
        	if(!alreadyPut.get(cc))
                if(!dfs(cc, visited, alreadyPut, graph, ans))
                    return false;
        }
        visited.put(c, false);
        if(!alreadyPut.get(c)){
            alreadyPut.put(c, true);
            ans.append(c);
        }
        return true;
    }
    
    private String topoSort_BFS(HashMap<Character, ArrayList<Character>> graph){
        HashMap<Character, Integer> in = new HashMap<>();
        for(Character c: graph.keySet())
            in.put(c, 0);
        
        //very easy to make mistake when building the indegree array
        //remember that the indegree of a node is
        //how many parent node it has
        //not how many children
        for(Character c: graph.keySet()){
            for(Character cc: graph.get(c)){
                in.put(cc, in.get(cc) + 1);
            }
        }
        
        Stack<Character> stk = new Stack<>();
        for(Character c: in.keySet()){
            if(in.get(c) == 0)
                stk.push(c);
        }
        // System.out.println(in);
        StringBuilder ans = new StringBuilder();
        int count = 0;
        while(!stk.isEmpty()){
            Character c = stk.pop();
            ans.append(c);
            count++;
            for(Character cc: graph.get(c)){
                in.put(cc, in.get(cc)-1);
                if(in.get(cc) == 0)
                    stk.push(cc);
            }
        }
        // System.out.println(ans.toString());
        if(count == graph.size())
            return ans.toString();
        return "";
    }
}
