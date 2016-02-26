/**
 * Created by vcoder on 2/17/16.
 */

/*'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false*/

/*my analysis
* match[i][j] means s.substring(i, len) matched p.substring(j, len)
* more details see the code
* */

public class Problem44_WildcardMatching {

    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()] = true; //empty str match empty str

        //considering that p can end with many *s
        //since * can stand for any sequences, so we just need the first one
        int k = p.length()-1;
        while(k >= 0 && p.charAt(k) == '*'){
            match[s.length()][k] = true;
            k--;
        }

        for(int i = s.length()-1; i >= 0; i--){
            //here j can be j = k==p.length()-1?k:k+1
            //it start from the first * of the end of p
            //this can save some time
            for(int j = p.length()-1; j >= 0; j--){
                //only one char match
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                    match[i][j] = match[i+1][j+1];

                else if(p.charAt(j) == '*')
                    //match[i+1][i] means * matches the substring that includes ith char
                    //match[i][j+1] means * stand for an empty string
                    match[i][j] = match[i+1][j] || match[i][j+1];
                else
                    match[i][j] = false;
            }
        }
        return match[0][0];
    }
}
