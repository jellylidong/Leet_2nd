
public class Problem264_Ugly_Number_II {
	public int nthUglyNumber(int n) {
        if(n <= 0)
            return -1;
        int[] res = new int[n+1];
        res[1] = 1;
        int id2 = 1;
        int id3 = 1;
        int id5 = 1;
        for(int i = 2; i <= n; i++){
            res[i] = Math.min(Math.min(2*res[id2], 3*res[id3]), 5*res[id5]);
            id2 += 2*res[id2] == res[i]? 1: 0;
            id3 += 3*res[id3] == res[i]? 1: 0;
            id5 += 5*res[id5] == res[i]? 1: 0;
        }
        return res[n];
    }
}
