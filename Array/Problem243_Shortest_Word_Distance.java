import java.util.*;

public class Problem243_Shortest_Word_Distance {
	public int shortestDistance(String[] words, String word1, String word2) {
        List<Integer> list =  new ArrayList<Integer>();
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1))
                list.add(i);
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < list.size(); i++){
            int lo = 0;
            int hi = 0;
            if(i > 0)
                lo = list.get(i-1) + 1;
            else
                lo = 0;
            if(i < list.size()-1)
                hi = list.get(i+1) - 1;
            else
                hi = words.length - 1;
                
            for(int j = lo; j < list.get(i); j++){
                if(words[j].equals(word2))
                    res = Math.min(res, Math.abs(j - list.get(i)));
            }
            
            for(int j = list.get(i) + 1; j <= hi; j++){
                if(words[j].equals(word2))
                    res = Math.min(res, Math.abs(j - list.get(i)));
            }
        }
        return res;
    }
	
	public int shortestDistance_method2(String[] words, String word1, String word2){
		int p1 = -1; 
		int p2 = -1;
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < words.length; i++){
			if(words[i].equals(word1))
				p1 = i;
			if(words[i].equals(word2))
				p2 = i;
			if(p1 != -1 && p2 != -1)
				res = Math.min(res, Math.abs(p1 - p2));
		}
		return res;
	}
}
