import java.util.*;

/*Given a string containing only digits, 
 * restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)*/

/*my analysis
 * 
 * method_1 recursion
 * if 1st: s(i, j) is valid, update current string, 
 * then we continue to check 2nd: s(j, k), then  s(k, l) and so forth
 * after the (n+1)th check done,
 *  recover the string to its original , 
 *  and update current hi boundary, continue with next check 
 *  e.g originally str = "";
 *  fist check s(0, x), if it's valid, str += s.substring(0, x) + "."
 *  then begin to 2nd check s(x, x+y)
 *  after 2nd check done, str -= s.subrting(0,x) + "."
 *  and x++
 *  begin 2nd check s(x+1, x+1+y) and so forth
 *  
 *  meanwhile, we use a count to record how many dot we have added, 
 *  if count = 4 and the str.length == s.length() + 4
 *  it means we restore a valid IP, so add it to the list
 * */

/*mehod_2
 * notice that the IP can always be composed by
 * [0, x) + [x, x+y) + [x+y, x+y+z) + [x+y+z, l)
 * x, y ,z >= 1 and <= 3
 * x+y+z < l
 * so we can simply use three iterations*/
public class Problem93_RestoreIPAddresses {
	List<String> list;
    public List<String> restoreIpAddresses_1(String s) {
    	list = new ArrayList<>();
    	int l = s.length();
    	if(l > 12)
    		return list;
    	gen(0, 1, s, "", 0);
    	return list;
    }
    
    public void gen(int lo, int hi, String s, String pre, int count){
    	if(count == 4 && pre.length() == s.length() + 4)
    		list.add(pre.substring(0, pre.length()-1)); // remove the last dot
    	else{
    		while(hi <= s.length() && Integer.parseInt(s.substring(lo, hi)) <= 255){
    			int l = hi-lo;
    			String tmp = s.substring(lo, hi);
    			
    			//for case like "00100" which can generate str start with "0" like "010", which is invalid
    			if(tmp.length() > 1 && tmp.charAt(0) == '0'){
    				lo++;
    				hi=lo+1;
    				continue;
    			}
    			pre +=  tmp + ".";
    			gen(hi, hi+1, s, pre, count+1);
    			pre = pre.substring(0, pre.length() -l -1);
    			hi++;
    		}
    	}
    }
    
    public List<String> restoreIpAddresses_2(String s) {
        list = new ArrayList<>();
        int l = s.length();
        if(l > 12)
            return list;
        for(int x = 1; x <= 3; x++){
            for(int y = 1; y <= 3; y++){
                for(int z = 1; z <=3; z++){
                	//make sure all in the boundary
                    if(x > l || x+y > l || x+y+z > l)
                        break;
                    String s1 = s.substring(0, x);
                    String s2 = s.substring(x, x+y);
                    String s3 = s.substring(x+y, x+y+z);
                    String s4 = s.substring(x+y+z, l);
                    // System.out.println(x+" "+y +"  "+z);
                    // System.out.println(s1+" " + s2 + " " + s3 + " " + s4);
                    
                    //all strings are not empty, not start with 0, not over 255
                    //to simplify the code, these three judge can be write in one function
                    if(empty(s1) || empty(s2) || empty(s3) || empty(s4) ||
                       startWith0(s1) || startWith0(s2) || startWith0(s3) || startWith0(s4)||
                       over255(s1) || over255(s2) || over255(s3) || over255(s4)) 
                       continue;
                    else
                        list.add(s1 + "." + s2 + "." + s3 + "."+ s4);
                }
            }
            
        }
        return list;
    }
    
    public boolean over255(String s){
        return Integer.parseInt(s) > 255;
    }
    
    public boolean startWith0(String s){
        return s.length() > 1 && s.charAt(0) == '0';
    }
    
    public boolean empty(String s){
        return s.length() == 0;
    }
}
