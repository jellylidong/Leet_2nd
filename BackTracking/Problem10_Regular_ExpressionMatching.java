/**
 * Created by jellyld on 2/3/2016.
 */

/*Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true*/

/*my analysis
* we can use a 2d array to record the match
* match[i][j] mean s.substring(0, i) matches p.substring(0, j) or not
* i, j  are the end of each string, not included
* first we need to initial the array
* match[0][0] = true because empty string matches empty string
* match[i][0] = false, because non-empty string doesn't match empty string
*
* match[0][i] = (i > 0) && p.charAt(i) == '*' && match[0][i-2]
* here we need i>0  because match[0][1] = false and the first char of p can not be a '*'
* then for an empty string(s.substring(0,0)), match[0][i] can be true only if the p.charAt(i) == '*" and
* at the position of the previous two chars, also matches, that is to say, here the * matched zero of preceding element
*
* for other match[i][j]
* when p[j-1] != '*'
* match[i][j] is true only when match[i-1][j-1] is true and current char s[i-1] == p[j-1] or p[j-1] == '.'
*
* when p[j-1] == '*', there can be two cases match[i][j] is true:
* case 1: '*' matches zero of precedding elements, i.e match[i][j-2] is true
* case 2: '*' matches more than one precedding elements, that is
* ????
* (s[i-1] == p[j-2] || p[j-2] == '.') && match[i-1][j] (s.substring(0, i-1) matches p.substring(0, j))
* */

public class Problem10_Regular_ExpressionMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[s.length()+1][p.length()+1];
        match[0][0] = true;
        for(int i = 0; i < s.length(); i++)
            match[i+1][0] = false;
        for(int i = 0; i < p.length(); i++)
            match[0][i+1] = i+1 > 1 && p.charAt(i) == '*' && match[0][i-1];

        //i, j  are the end of each string, not included
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= p.length(); j++){
                if(p.charAt(j-1) != '*') //only the end char can match
                    match[i][j] = match[i-1][j-1] && (p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1));
                else
                    //1. * stands for 0 preceding
                    match[i][j] = match[i][j-2] ||
                            //2. * stands for >= 1 precedings
                            //explain:
                            //if match[i-1][j] == true, assume that by now * stands for n precedings
                            //note that the position of [i-1][j] for * is j-1, for char of s is i-2
                            //now comes the i-1 th char, if we need it matches,
                            //i.e we need * stands for n+1 preceding,
                            // the i-1 th char should == *'s preceding
                            //so we must have p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1))
                            (match[i-1][j] && (p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)));
            }
        }
        return match[s.length()][p.length()];
    }
}
