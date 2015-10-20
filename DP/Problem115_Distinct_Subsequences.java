
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
