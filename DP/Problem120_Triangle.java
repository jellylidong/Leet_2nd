/*Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.*/

/*my analysis
note for number at positon i
its neighbors are i and i+1 in next row

* method 1: from top to bottom
*
* method 2: from botom to top: easier
* */

import java.util.*;
public class Problem120_Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0 || triangle.get(0).size() == 0)
            return 0;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = triangle.get(0);
        List<Integer> pre = triangle.get(0);
        res.add(cur);
        
        for(int i = 1; i < triangle.size(); i++){
            cur = triangle.get(i);
            pre = res.get(i-1);
            List<Integer> list = new ArrayList<>(cur);
            int tmp = Integer.MAX_VALUE;
            for(int j = 0; j < cur.size()-1; j++){
                if(j > 0)
                    tmp = list.get(j);
                int res1 = Math.min(tmp, cur.get(j) + pre.get(j));
                int res2 = cur.get(j+1) + pre.get(j);
                list.set(j, res1);
                list.set(j+1,res2);
            }
            res.add(list);
        }
        int min = Integer.MAX_VALUE;
        for(int n: res.get(triangle.size()-1))
            min = Math.min(min, n);
        return min;
    }
	
	public int minimumTotal_bottom_up_opttimized(List<List<Integer>> triangle) {
        if(triangle.size() == 0 || triangle.get(0).size() == 0)
            return 0;
        int size = triangle.size();
        for(int i = size-2; i >= 0; i--){
                List<Integer> tmp = triangle.get(i);
            for(int j = 0; j < tmp.size(); j++){
                int n1 = triangle.get(i+1).get(j);
                int n2 = triangle.get(i+1).get(j+1);
                tmp.set(j, Math.min(n1, n2) + tmp.get(j));
            }
        }
        return triangle.get(0).get(0);
    }
}
