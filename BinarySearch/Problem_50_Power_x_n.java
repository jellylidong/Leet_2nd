/**
 * Created by vcoder on 1/21/16.
 */

/*Implement pow(x, n).
*/

/*my analysis
* the tricky part of this problem is if n < 0
* we need to transfrom x to 1/x
* also when n is odd, we should return x*power(x, n-1)*/

public class Problem_50_Power_x_n {
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n < 0)
            x = 1/x;
        if(n%2 == 0)
            return myPow(x*x, n/2);
        else
            return x*myPow(x, n-1);
    }
}
