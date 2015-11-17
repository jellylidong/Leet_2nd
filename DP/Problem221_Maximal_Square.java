/*Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0*/

/*method 1
 * res[i][j] is the length of square that the low-right corner is
 * at m[i-1][j-1]
 * when going through all the number of m
 * first, we need to make sure the number m[i-1][j-1] is able to make a square,
 * the condition is m[i-1][j-1] == 1
 * second, we need to make sure this m[i-1][j-1] is able to combine with previous
 * results to make a new square
 * three results need to be considered:
 * res[i-1][j-1], res[i-1][j], res[i][j-1]
 * because these three results and res[i][j] are all neighborhoods
 * !!! only the results has minimum length is able to combine with m[i-1][j-1 to make res[i][j]]
 * proof: if we choose a non-minimum to combine to make res[i][j]
 * 	res[i-1][j-1] = a, res[i-1][j] = b, res[i][j-1] = c
 * 	assume a < b < c and we choose b
 * 	so res[i][j] = b+1
 * 	this means our length with low-right corner m[i-1][j-1] = b+1
 * 	but for res[i-1][j], it only has length = a, so the maximum length it can provide is a+1, which is  < b+1
 * 	and can not make a new square and this point, so the minimum length for res[i][j] = a+1
 * so res[i][j] = min(res[i-1][j-1], res[i-1][j], res[i][j-1)
 * */

/*method 2
 * a modified method 1 that the res from [0][0]
 * */

/*method 3 an optimization from method 1
 * notice that
 * res[i][j] = min(res[i-1][j-1], res[i-1][j], res[i][j-1)
 * we only need to store two rows of data instead
 * first row is res[i-1[x] in method 1, we use pre[x] instead
 * second row is res[i][x] in method 1, we use cur[x] instead
 * thus cur[x] = min(cur[x-1], pre[x], pre[x-1])
 * the space complexity is optimized to O(n)*/
public class Problem221_Maximal_Square {
	public int maximalSquare(char[][] m) {
        if(m.length == 0 || m[0].length == 0)
            return 0;
        int y = m.length;
        int x = m[0].length;
        
        int[][] res = new int[y+1][x+1];
        int max = 0;
        
        for(int i = 1; i <= y; i++){
            for(int j = 1; j <= x; j++){
                if(m[i-1][j-1] == '1'){
                    res[i][j] = Math.min(res[i-1][j-1], Math.min(res[i-1][j], res[i][j-1])) + 1;
                    max = Math.max(max, res[i][j]);
                }
            }
        }
        return max*max;
    }
}
