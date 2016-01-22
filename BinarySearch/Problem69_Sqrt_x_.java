/**
 * Created by vcoder on 1/21/16.
 */

/*Implement int sqrt(int x).*/

/*my analysis
* note that sqrt(x) <= x/2
* so we can start from 1 to x/2
* also we can compare mid with x/mid
* if mid > x/mid then mid is to big, so hi = mid-1
* else, note
* now we knwo mid can be mid <= x/mid
* so first we need to try if (mid+1) > x/(mid+1)
* if yes, it means mid = x/mid, so mid is the target value
* else it means mid < x/mid, so lo = mid+1*/

public class Problem69_Sqrt_x_ {
    public int mySqrt(int x) {
        if(x == 0)
            return 0;
        int lo = 1;
        int hi = x/2;
        while(lo < hi){
            int m = lo + (hi-lo)/2;
            if(m > x/m)
                hi = m-1;
            else if((m+1) > x/(m+1))
                return m;
            else
                lo = m+1;
        }
        return lo;

        //another solution, similar but not concise
        // long xx = (long)x;
        // long upper = xx/2;
        // long res = 0;
        // while(upper > 1){
        //     int i = 1;
        //     while(i <= upper && (long)(res+i)*(res+i) < xx)
        //         i = i<< 1;
        //     upper = i;
        //     res += (i >> 1);
        // }
        // return ((res+1)*(res+1)) > x? (int)res: (int)(res+1);
    }
}
