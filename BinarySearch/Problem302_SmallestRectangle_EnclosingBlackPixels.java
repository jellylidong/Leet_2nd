/**
 * Created by vcoder on 1/21/16.
 */

/*An image is represented by a binary matrix
with 0 as a white pixel and 1 as a black pixel.
The black pixels are connected, i.e., there is only one black region.
Pixels are connected horizontally and vertically.
Given the location (x, y) of one of the black pixels,
return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.*/


/*my analysis
* note: this problem requires us to find the are of square that can enclose the 1s,
* not the ares of 0 square around 1s
*
* if all the 1's are connected, project the whole graph to x or y
* we can get 0110
* or
* 1
* 1
* 1
*
* so what we need to do is to find the most left and right boundary
* and most up and bottom boundary*/

public class Problem302_SmallestRectangle_EnclosingBlackPixels {
    public int minArea(char[][] image, int x, int y) {
        int left = y;
        int right = y;
        int up = x;
        int down = x;
        for(int i = 0; i < image.length; i++){
            for(int j = left; j >= 0; j--){
                if(image[i][j] == '1'){
                    left = Math.min(j, left);
                }
            }
        }
        for(int i = 0; i < image.length; i++){
            for(int j = right; j < image[0].length; j++){
                if(image[i][j] == '1'){
                    right = Math.max(right, j);
                }
            }
        }

        for(int i = 0; i < image[0].length; i++){
            for(int j = up; j >= 0; j--){
                if(image[j][i] == '1'){
                    up = Math.min(up, j);
                }
            }
        }

        for(int i = 0; i < image[0].length; i++){
            for(int j = down; j < image.length; j++){
                if(image[j][i] == '1'){
                    down = Math.max(down, j);
                }
            }
        }
        // System.out.println(up + " " + down + " " + left + " " + down);
        return (down-up+1)*(right-left+1);
    }
}
