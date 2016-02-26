/*A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.*/

/*my analysis
* let's see a special e.g 201
* if go from left to right
* 2: 1
* 20: still 1 because sigle 0 can not be decoded
* 201: still 1 because 01 can not be decoded, the only solution is 20_1
* this makes the judgement hard to implemnet
* so instead, we go from right to left
* if we meed a 0, we just skip it*/
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
