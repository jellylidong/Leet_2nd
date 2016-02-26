
public class Problem70_Climbing_Stairs {
	public int climbStairs_mem_O_n(int n) {
        if(n <= 0)
            return 0;
        int[] res = new int[n+1];
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i <= n; i++)
            res[i] = res[i-1] + res[i-2];
        return res[n];
    }

    //optimized based method 1,
    //like fib array, we only need to keep record the previous two numbers
	public int climbStairs_mem_O1(int n) {
        if(n <= 0)
            return 0;
        int pre1 = 1;
        int pre2 = 0;
        int res = 0;
        for(int i = 1; i <= n; i++){
            res = pre1 + pre2;
            pre2 = pre1;
            pre1 = res;
        }
        return res;
    }
}
