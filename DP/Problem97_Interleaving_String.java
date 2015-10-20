
public class Problem97_Interleaving_String {
	public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        
        if(l1 + l2 != s3.length())
            return false;
        
        //res[i][j] means if s1.charAt(i-1) or s2.charAt(j-1) match s3.charAt(i+j-1)
        boolean[][] res = new boolean[l1+1][l2+1];
        res[0][0] = true;
        for(int i = 1; i < l1+1; i++)
            res[i][0] = res[i-1][0] && (s1.charAt(i-1) == s3.charAt(0+i-1));
        for(int i = 1; i < l2+1; i++)
            res[0][i] = res[0][i-1] && (s2.charAt(i-1) == s3.charAt(0+i-1));
        for(int i = 1; i < l1+1; i++){
            for(int j = 1; j < l2+1; j++){
                res[i][j] = (res[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) ||
                            (res[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return res[l1][l2];
    }
}
