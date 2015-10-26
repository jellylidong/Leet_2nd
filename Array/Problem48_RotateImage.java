/*You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?*/


/*
 * method to rotate image clockwise
 * first reverse up to down
 * then reverse the symmetry
 * 1 2 3	7 8 9	7 4 1
 * 4 5 6	4 5 6	8 5 2
 * 7 8 9	1 2 3	9 6 3*/

/*method to rotate image anti-clockwise
 * first reverse left to right
 * then reverse the symmetry
 * 1 2 3	3 2 1	3 6 9
 * 4 5 6	6 5 4	2 5 8
 * 7 8 9	9 8 7	1 4 7*/
public class Problem48_RotateImage {
	public void rotate(int[][] nums) {
		int length = nums[0].length;
		int height = nums.length;
		for(int i = 0; i < height/2; i++){
			/*note:
			 *be careful for the height/2
			 *if height == nums.height then we use height/2
			 *if height == nums.height-1, then we can not get correct reults*/
			for(int j = 0; j < length; j++){
				int tmp = nums[i][j];
				nums[i][j] = nums[height-1-i][j];
				nums[height-1-i][j] = tmp;
			}
		}
		
		for(int i = 0; i < length; i++){
			for(int j = 0; j <= i; j++){
				int tmp = nums[i][j];
				nums[i][j] = nums[j][i];
				nums[j][i] = tmp;
			}
		}
	}
}
