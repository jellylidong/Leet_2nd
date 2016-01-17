/*A group of two or more people wants to meet 
 * and minimize the total travel distance. 
 * You are given a 2D grid of values 0 or 1, 
 * where each 1 marks the home of someone in the group. 
 * The distance is calculated using Manhattan Distance, 
 * where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, 
as the total travel distance of 2+2+2=6 is minimal. 
So return 6.

Hint:

Try to solve it in one dimension first. 
How can this solution apply to the two dimension case?*/


/*my analysis
 * note: this problem is quite different from 
 * the 317 ShortestDistance_from_All_Buildings, in 317, our target is only able to
 * a 0 node, but in this problem, the target node can be both o and 1 node
 * 
 * first let's start from a 1D array,
 * for a sorted one-D array start from i to j(inclusive),
 * the position p that make sum(|p-pi|) minimum is the the median
 * of this array, because median make the minimum overlap of the summary
 * 
 * if we have two position p1 and p2
 * any position p between p1 and p2 can make the same summary distance,
 * i.e. |p-pi|+|p-pj| is a constant, it's always pj-pi
 * 
 * for a 2D array,  we find the media of is and media of js separately
 * then add them up*/

import java.util.*;
public class Problem296_BestMeetingPoint {
	public int minTotalDistance(int[][] grid) {
        ArrayList<Integer> I = new ArrayList<>();
        ArrayList<Integer> J = new ArrayList<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    I.add(i);
                    J.add(j);
                }
            }
        }
        
        return getMin(I) + getMin(J);
   }
   
   private int getMin(ArrayList<Integer> list){
       int i = 0;
       int j = list.size()-1;
       int res = 0;
       
       Collections.sort(list);
       
       while(i < j){
           res += list.get(j) - list.get(i);
           j--;
           i++;
       }
       return res;
   }
}
