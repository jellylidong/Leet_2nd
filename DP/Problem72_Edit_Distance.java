
/*Given two words word1 and word2,
find the minimum number of steps required to convert word1 to word2.
(each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character*/


/*my analysis
* res[i][j] means edit distance of w1.substring(i, len) and w2.substring(j, len)
* basic idea is to
* find the edit distance of insert, delete and replace
* the find the min of the three*/
public class Problem72_Edit_Distance {
	public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        
        int[][] res = new int[l1+1][l2+1];
        
        for(int i = 0; i <= l1; i++)
            res[i][0] = i;
        for(int i = 0; i <= l2; i++)
            res[0][i] = i;
        
        for(int i = 1; i <= l1; i++){
            for(int j = 1; j <= l2; j++){
                int insert = res[i][j-1] + 1;
                int delete  = res[i-1][j] + 1;
                int replace= res[i-1][j-1] + (word1.charAt(i-1) == word2.charAt(j-1)? 0: 1);
                res[i][j] = Math.min(insert, Math.min(delete, replace));
            }
        }
        return res[l1][l2];
    }
}
