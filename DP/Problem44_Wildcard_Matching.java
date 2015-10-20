
public class Problem44_Wildcard_Matching {
	public boolean isMatch(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        int is = 0;
        int ip = 0;
        int preis = is;
        int preip = ip;
        boolean hasStar = false;
        while(is < ls){
            if(ip < lp)
                if(p.charAt(ip) == '?' || p.charAt(ip) == s.charAt(is)){
                    is++;
                    ip++;
                    continue;
                }
            
            if(ip < lp && p.charAt(ip) == '*'){
                hasStar = true;
                preip = ip;
                preis = is;
                ip++;
                continue;
            }
            
            if(preip < lp && hasStar){ // preip is used to avoid TLE, without it, id ip >= lp, dead loop will happen
                ip = preip+1;
                preis++;
                is = preis;
                continue;
            }
            
            return false;
        }
        
        while(ip < lp && p.charAt(ip) == '*')
            ip++;
        
        return ip == lp;
    }
}
