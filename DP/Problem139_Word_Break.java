import java.util.*;
public class Problem139_Word_Break {
	public boolean wordBreak(String s, Set<String> dict) {
        int l = s.length();
        boolean[] res = new boolean[l+1];
        //res[i] is the if s.substring(0, i) is in dict
        res[0] = true;
        for(int i = 1; i <= l; i++){
            for(int j = 0; j < i; j++){
                res[i] = res[j] && dict.contains(s.substring(j, i));
                if(res[i])
                    break;
            }
        }
        return res[l];
    }
	
	public boolean wordBreak_optimized(String s, Set<String> dict) {
        LinkedList<Integer> starts = new LinkedList<>();
        starts.add(0);
        for(int end = 1; end <= s.length(); end++){
            boolean found = false;
            for(int start: starts){
                if(dict.contains(s.substring(start, end))){
                    found = true;
                    break;
                }
            }
            if(found)
                starts.addFirst(end);
        }
        return starts.get(0) == s.length();
    }
}
