
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
}
