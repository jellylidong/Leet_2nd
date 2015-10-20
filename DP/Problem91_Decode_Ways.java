
public class Problem91_Decode_Ways {
	public int numDecodings(String s) {
        int l = s.length();
        if(l == 0)
            return 0;
        
        int[] res = new int[l+1];
        res[l] = 1;
        res[l-1] = s.charAt(l-1) == '0'? 0: res[l];
        
        for(int i = l-2; i >= 0; i--){
            if(s.charAt(i) == '0')
                continue;
            res[i] = (Integer.parseInt(s.substring(i, i+2)) <= 26)? res[i+1] +res[i+2]: res[i+1];
        }
        return res[0];
    }
	
	public int numDecodings_type_again(String s){
		int l = s.length();
		if(l == 0)
			return 0;
		int[] res = new int[l+1];
		res[l] = 1;
		res[l-2] = s.charAt(l-1) == '0'? 0: 1;
		for(int i = l-2; i >= 0; i--){
			if(s.charAt(i) == '0')	continue;
			res[i] = (Integer.parseInt(s.substring(i, i+2)) <= 26)? res[i] = res[i+1] + res[i+2]: res[i+1];
		}
		return res[0];
	}
}
