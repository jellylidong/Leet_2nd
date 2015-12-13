/*Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]*/
public class Probelm59_Spiral_Matrix_II {
	public int[][] generateMatrix(int n) {
        int val = 1;
        int left = 0;
        int right = n-1;
        int up = 0;
        int down = n-1;
        int[][] res = new int[n][n];

        while(val <= n*n){
            for(int i = left; val <= n*n && i <= right; i++){
                res[up][i] = val;
                val++;
            }
            up++;

            for(int i = up; val <= n*n && i <= down; i++){
                res[i][right] = val;
                val++;
            }
            right--;

            for(int i = right; val <= n*n && i >= left; i--){
                res[down][i] = val;
                val++;
            }
            down--;

            for(int i = down; val <= n*n && i >= up; i--){
                res[i][left] = val;
                val++;
            }
            left++;
        }
        return res;
    }
}
//randomly input to test git
//NOT WORKING, TRY AGAIN
//AGAIN
