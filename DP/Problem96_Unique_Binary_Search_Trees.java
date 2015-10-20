public class Problem96_Unique_Binary_Search_Trees {
	public static int numTrees(int n) {
        if(n <= 0)
            return n;
        int[] res = new int[n+1];
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                res[i] += res[j-1] * res[i-j];
            	System.out.println(j+" " +res[i]);
            }
        }
        return res[n];
    }
	
	public static void main(String[] args){
		System.out.println(numTrees(3));
	}
}
