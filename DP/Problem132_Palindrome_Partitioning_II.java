/*Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.*/

/*my analysis
method 1
* isP[i][j] indicate the substring s(i, j) is palindrome, both i and j are inclusive
* cut[i] indicate the min number of cuts of substring s(i. end)
*
*
* method 2
* use symmetry
* for a pal, the length can be odd or even
* see the details in the code*/
public class Problem132_Palindrome_Partitioning_II {
	 public int minCut(String s) {
	        int l = s.length();
	        if(l <= 1)
	            return 0;
	        int[] cut = new int[l];
	        boolean[][] isP = new boolean[l][l];
	        
	        for(int i = l-1; i >= 0; i--){
	            cut[i] = l-i-1;
	            for(int j = i; j < l; j++){
	                if( (s.charAt(i) == s.charAt(j)) && (j-i <= 1 || isP[i+1][j-1]) ){
	                    isP[i][j] = true;
	                    if(j == l-1) cut[i] = 0;
	                    else cut[i] = Math.min(cut[i], 1+cut[j+1]);
	                }
	            }
	        }
	        
        return cut[0];
    }

    public int minCut_method2(String s) {
        int l = s.length();
        if(l == 0)
            return 0;
        //cut[i] min cut of string end withs.charAt(i)
        int[] cut = new int[l];
        for(int i = 0; i < l; i++)
            cut[i] = i;
        //i is the mid index or the left mid index of even pal
        for(int i = 0; i < l; i++){
            //pal len is odd
            for(int j=0; i-j >= 0 && i+j < l && s.charAt(i-j) == s.charAt(i+j); j++)
                cut[i+j] = Math.min(cut[i+j], i-j-1 >= 0? 1+cut[i-j-1]: 0);

            //pal len is even
            for(int j=0; i-j >= 0 && i+1+j < l && s.charAt(i-j) == s.charAt(i+j+1); j++)
                cut[i+j+1] = Math.min(cut[i+j+1], i-j-1 >= 0? 1+cut[i-j-1]: 0);

            //note that the index should >= 0
        }
        return cut[l-1];
    }
}
