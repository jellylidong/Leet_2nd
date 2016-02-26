/*Given a string S and a string T,
count the number of distinct subsequences of T in S.

A subsequence of a string is a new string
which is formed from the original string by deleting some (can be none) of
the characters without disturbing the relative positions of the remaining characters.
(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.*/

/*my analysis
* res[j][i] mean the number of sub-sequences of T.substring(0, i) in S.substring(0, j)
* for res[j][0], there's only one solution, that is to delete all char in T
*
* for other res[j][i],
* case 1: S.charAt(j-1) != T.charAt(i-1)
* what we need to do is to ignore the S.charAT(j-1)
* so res[j][i] = 1 * res[j-1][i]
*
* case 2: S.charAt(j-1) == T.charAt(i-1)
* we can also ignore S.charAt(j-1) like case 1,
* so part of res[j][i] would be res[j-1][i]
* we can also ignore both S.charAt(j-1) and T.charAt(i-1) because they are the same
* so the other part wound be res[j-1][i-1]
* so res[j][i] = res[j-1][i] + res[j-1][i-1]
* */
public class Problem115_Distinct_Subsequences {
	public int numDistinct(String s, String t) {
        int ls = s.length();
        int lt = t.length();
        if(ls == 0 && lt == 0)
            return 1;
        if(ls == 0 && lt != 0)
            return 0;
        int[][] res = new int[ls+1][lt+1];
        for(int i = 0; i <= ls; i++)
            res[i][0] = 1;
            
        
        for(int i = 1; i <= lt; i++){
            for(int j = 1; j <= ls; j++){
                if(s.charAt(j-1) == t.charAt(i-1))
                    res[j][i] = res[j-1][i] + res[j-1][i-1];
                else
                    res[j][i] = res[j-1][i];
            }
        }
        return res[ls][lt];
        
    }
	
	public int numDistinct_typeagaein(String s, String t){
		int ls = s.length();
		int lt = t.length();
		if(ls == 0 && lt != 0)
			return 0;
		if(ls == 1 && lt == 0)
			return 1;
		
		int[][] res = new int[ls][lt];
		
		for(int i = 1; i <= lt; i++){
			for(int j = 1; j <= ls; j++){
				if(s.charAt(j-1) == s.charAt(i-1))
					res[j][i] = res[j-1][i] + res[j-1][i-1];
				else
					res[j][i] = res[j-1][i];
			}
		}
		
		return res[ls][lt];
	}
}
