
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
            right[i] = x;
            //height[i] = 0;// initial value is 0, don't need actually
        }
        
        for(int i = 0; i < y; i++){
            int curLeft = 0;
            int curRight= x;
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
                else {right[j] = x; curRight = j;}
            }
            for(int j = 0; j < x; j++){
                max = Math.max(max, (right[j] - left[j]) * height[j]);
            }
        }
        return max;
    }
}
