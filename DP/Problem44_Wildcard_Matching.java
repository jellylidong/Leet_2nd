///*Implement wildcard pattern matching with support for '?' and '*'.
//
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).
//
//The matching should cover the entire input string (not partial).
//
//The function prototype should be:
//bool isMatch(const char *s, const char *p)
//
//Some examples:
//isMatch("aa","a") → false
//isMatch("aa","aa") → true
//isMatch("aaa","aa") → false
//isMatch("aa", "*") → true
//isMatch("aa", "a*") → true
//isMatch("ab", "?*") → true
//isMatch("aab", "c*a*b") → false*/
//
///*my analysis
//*
//* method 2 useing dynamic programming
//* match[i][j] mean that s.substring(i, s.length) matches p.substring(j, p.length()) or not
//* so we need to go backward from the end to beginning, finally return match[0][0]*/
//public class Problem44_Wildcard_Matching {
//	public boolean isMatch(String s, String p) {
//        int ls = s.length();
//        int lp = p.length();
//        int is = 0;
//        int ip = 0;
//        int preis = is;
//        int preip = ip;
//        boolean hasStar = false;
//        while(is < ls){
//            if(ip < lp)
//                if(p.charAt(ip) == '?' || p.charAt(ip) == s.charAt(is)){
//                    is++;
//                    ip++;
//                    continue;
//                }
//
//            if(ip < lp && p.charAt(ip) == '*'){
//                hasStar = true;
//                preip = ip;
//                preis = is;
//                ip++;
//                continue;
//            }
//
//            if(preip < lp && hasStar){ // preip is used to avoid TLE, without it, id ip >= lp, dead loop will happen
//                ip = preip+1;
//                preis++;
//                is = preis;
//                continue;
//            }
//
//            return false;
//        }
//
//        while(ip < lp && p.charAt(ip) == '*')
//            ip++;
//
//        return ip == lp;
//    }
//
//    public boolean isMatch_DP(String s, String p) {
//        boolean[][] match = new boolean[s.length()+1][p.length()+1];
//        match[s.length()][p.length()] = true; // end of the string is empty string, so the two substring matches
//
//        //if the end of p is *, it can match all the empty substring of s
//        int k = p.length()-1;
//        while(p >= 0 && p.charAt(k) public boolean isMatch(String s, String p) {== '*'){
//            match[s.length()][k] = true;
//            k--;
//        }
//        for(int i = s.length()-1; i >= 0 ; i--){
//            for(int j = p.length()-1; j >= 0; j--){
//                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
//                    match[i][j] = match[i+1][j+1];
//                else if(p.charAT(j) == '*')
//                    match[i][j] = match[i+1][j] || match[i][j+1];
//                else
//                    match[i][j] = false;
//            }
//        }
//        return match[0][0];
//    }
//}
