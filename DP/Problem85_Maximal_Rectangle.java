/*Given a 2D binary matrix filled with 0's and 1's, 
 * find the largest rectangle containing all ones and return its area.

Hide Tags Array Hash Table Stack Dynamic Programming
Show Similar Problems
*/

/*my analysis
 * use 3 arrays to store the left, right and height
 * h[j] is height till row i at col j at m[i][j]
 * l[j] is where the left start at m[i][j]
 * r[j] is where the right end at m[i][j]
 * all three are inclusive
 * 
 * h[i] = h[j]+1 if m[i][j] == '1' else h[j] = 0;
 * 
 * l[j] = Math.max(l[j], curL) when m[i][j] == '1'
 * else l[j]=0 and curL = j+1;
 * since when m[i][j] == '0', the h[j] is already set to 0
 * we don't need to worry about setting l[j] = 0 would influence the max area
 * instead, set l[j] = 0 is just like initial it before the loop and it will used for next row
 * as for curL, since m[i][j] == '0', we can infer that curL is at least j+1
 * 
 * similarly for r[j]
 * note that we need to go from x to 0 (backward) to update r[j]
 * r[j] = Math.max(r[j], curR) when m[i][j] == '1'
 * else
 * r[j] = x-1, curR = j-1
 * reason is similar to l[j]'s
 *  * */
public class Problem85_Maximal_Rectangle {
	public int maximalRectangle(char[][] m) {
        if(m.length == 0 || m[0].length == 0)
            return 0;
        int y = m.length;
        int x = m[0].length;
        
        int[] left = new int[x];
        int[] right= new int[x];
        int[] height=new int[x];
        int max = 0;
        for(int i = 0; i < x; i++){
            //left[i] = 0;// initial value is 0, don't need actually
            right[i] = x-1;
            //height[i] = 0;// initial value is 0, don't need actually
        }
        
        for(int i = 0; i < y; i++){
            int curLeft = 0;
            int curRight= x-1;
            for(int j = 0; j < x; j++){
                if(m[i][j] == '1')  height[j]++;
                else    height[j] = 0;
            }
            for(int j = 0; j < x; j++){
                if(m[i][j] == '1')  left[j] = Math.max(left[j], curLeft);
                else {left[j] = 0; curLeft = j+1;} // note how to update curLeft and curRight
            }
            for(int j = x-1; j >= 0; j--){// note: compute riht[] from right to left
                if(m[i][j] == '1')  right[j] = Math.min(right[j], curRight);
                else {right[j] = x-1; curRight = j-1;}
            }
            for(int j = 0; j < x; j++){
                max = Math.max(max, (right[j] - left[j]+1) * height[j]);
            }
        }
        return max;
    }
}
