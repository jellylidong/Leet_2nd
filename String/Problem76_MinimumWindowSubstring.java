import java.util.HashMap;

/*Given a string S and a string T, 
 * find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, 
return the empty string "".

If there are multiple such windows, 
you are guaranteed that there will always be only one unique minimum window in S.*/

/*my analysis
 * 1.a
 * use lo and hi to record the windows's begin and end
 * 1.b
 * put the whole T in to a hashmap name Tmap: 
 * k = T.charAt(i), v = times this char appears in T
 * 
 * 2
 * go through the whole S from hi = 0 to s.length
 * 2.a
 * keep putting S.charAt(i) that also in Tmap 
 * and at the same time, if Smap.get(S[hi]) < Tmap.get(S[i])
 * it means current substring can not cover all chars in T
 * so count++
 * 
 * 2.b
 * when count >= T.length,
 * it means current str = S.substring(lo, hi+1) covers the T but not necessarily the minimus window
 * there can be some redundant chars put into the Smap
 * so we need to check from lo
 * while(S[lo] not in Smap || value in Smap of S[lo] > that in Tamp
 * for 2nd case, we need to reduce the value of S[lo] by 1 in Smap
 * for both case, lo++
 * 
 * 2.c
 * then after lo updating done
 * update the length by min(minL, hi-lo+1)
 * 
 * then go back to 2.a until all chars are checked*/
public class Problem76_MinimumWindowSubstring {
	public String minWindow(String s, String t) {
        int lo = 0; int hi = 0;
        int minStart = 0, minL = Integer.MAX_VALUE;
        int count = 0;
        
        HashMap<Character, Integer> tMap = new HashMap<>();
        HashMap<Character, Integer> sMap = new HashMap<>();
        
        //put t to tMap
        for(int i = 0; i < t.length(); i++){
        	char c = t.charAt(i);
        	if(tMap.containsKey(c))
        		tMap.put(c, tMap.get(c)+1);
        	else{
        		tMap.put(c, 1);
        		sMap.put(c, 0);
        	}
        }
        
        for(; hi < s.length(); hi++){
        	char c = s.charAt(hi);
        	//put
        	if(tMap.containsKey(c)){
        		if(sMap.get(c) < tMap.get(c))
        			count++;//This mean we add a char that can cover more part of T
        		sMap.put(c, sMap.get(c)+1);
        	}
        	//when put enough, remove
        	if(count == t.length()){
        		while(!tMap.containsKey(s.charAt(lo)) || sMap.get(s.charAt(lo)) > tMap.get(s.charAt(lo))){
        			//for 2nd case
        			if(tMap.containsKey(s.charAt(lo)))
        					sMap.put(s.charAt(lo), sMap.get(s.charAt(lo))-1);
        			lo++;
        		}
        		
        		if(hi-lo+1 < minL){
        			minStart = lo;
        			minL = hi-lo+1;
        		}
        		
        		//remove the first word so that we can start next search
        		count--;
        		sMap.put(s.charAt(lo), sMap.get(s.charAt(lo))-1);
        		lo++;
        	}
        }
        
        if(minL == Integer.MAX_VALUE)
        	return "";
        return s.substring(minStart, minStart+minL);
    }
}
