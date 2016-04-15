/*A city's skyline is the outer contour of the silhouette 
 * formed by all the buildings in that city when viewed from a distance. 
 * Now suppose you are given the locations and height of all the buildings 
 * as shown on a cityscape photo (Figure A), write a program 
 * to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
The geometric information of each building is represented 
by a triplet of integers [Li, Ri, Hi], where Li and Ri are 
the x coordinates of the left and right edge of the ith building, 
respectively, and Hi is its height. It is guaranteed 
that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. 
You may assume all buildings are perfect rectangles grounded on 
an absolutely flat surface at height 0.

For instance, the dimensions of all buildings 
in Figure A are recorded as: [ [2 9 10], [3 7 15], 
[5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) 
in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] 
that uniquely defines a skyline. A key point is t
he left endpoint of a horizontal line segment. 
Note that the last key point, where the rightmost building ends, 
is merely used to mark the termination of the skyline, a
nd always has zero height. 
Also, the ground in between any two adjacent buildings 
should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[
 [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline.
 For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 
 is not acceptable; the three lines of height 5 should be merged into one in the final output as such:
  [...[2 3], [4 5], [12 7], ...]
*/

/*my analysis
 * let's start at an example
 * given two points in the form of [x1, x2, h]:
 * p1 = [0, 2, 3] and p2 = [1, 3, 1]
 * separate the point as the following format for convenience [x1,h1], [x2, 0]
 * so we have 
 * l1 = [0, 3], [2, 0], 
 * l2 = [1, 1], [3, 0]
 * to make the outline of them, we need to go through theses four points 
 * in order of x, i.e. smaller x first.
 * 
 * we use h1 to record the height of l1, h2 the same
 * h1 and h2 are only updated when the list they belong to are used
 * and maxH to trace current max height
 * 1: [0,3]:
 * h1 = 3, h2 = 0, maxH = 3, so res = {[0,2]}
 * 2: [1,1]
 * h1 = 3, h2 = 1, maxH = 3, since maxH is not changed, 
 * we don't need to put this point to the results
 * 3: [2,0]
 * h1 = 0, h2 = 1, maxH = 1, maxH changed, so res={[0,2], [2, 1]}
 * 4: [3, 0]
 * h1 = 0, h2 = 0, maxH = 0, maxH changed, so res={0,2], [2, 1], [3, 0]}
 * 
 * we can merge every tow lists until all only one list left,
 * then we get the final result
 * this is how and why we use divide and conquer
 *  
 * 
 * besides, int mid = s + (e-s)/2 will finally get stable at two equal numbers
 * */

import java.util.*;
public class Problem218_TheSkylineProblem {
	 public List<int[]> getSkyline(int[][] b) {
	        if(b.length == 0)
	            return new ArrayList<int[]>();
	        return merge(b, 0, b.length-1);
	    }
		
		public List<int[]> merge(int[][] b, int s, int e){
			
			if(s < e){
				int m = s + (e-s)/2;
				List<int[]> l1 = merge(b, s, m);
				List<int[]> l2 = merge(b, m+1, e);
				return merge(l1, l2);
			}
			else{
				List<int[]> res = new LinkedList<>();
				res.add(new int[] {b[s][0], b[s][2]});
				res.add(new int[] {b[s][1], 0});
				return res;
			}
		}
		
		public List<int[]> merge(List<int[]> l1, List<int[]> l2){
			List<int[]> res = new LinkedList<>();
			
			int h1 = 0;
			int h2 = 0;
			int preH = 0;
			
			while(l1.size() > 0 && l2.size() > 0){
				int x = 0;
				int h = 0;
				
				if(l1.get(0)[0] < l2.get(0)[0]){
					x = l1.get(0)[0];
					h1 = l1.get(0)[1];
					h = Math.max(h1, h2);
					l1.remove(0);
				}
				else if(l2.get(0)[0] < l1.get(0)[0]){
					x = l2.get(0)[0];
					h2 = l2.get(0)[1];
					h = Math.max(h1, h2);
					l2.remove(0);
				}
				else{
					x = l1.get(0)[0];
					h1 = l1.get(0)[1];
					h2 = l2.get(0)[1];
					h = Math.max(h1, h2);
					l1.remove(0);
					l2.remove(0);
				}
				if(res.size() == 0 || h != preH){//max h is changed, so we need to add new node to res
					res.add(new int[] {x, h});
	    			preH = h;
				}
			}
			res.addAll(l1);
			res.addAll(l2);
			return res;
		}
}
