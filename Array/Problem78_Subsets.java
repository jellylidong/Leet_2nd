import java.util.*;
/*Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]*/

/*backtracking*/ // ??? time complexity
/*bit manipulation*/

public class Problem78_Subsets {
	public List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        res.add(new ArrayList<Integer>(list));
        subsets(nums, 0, list);
        return res;
    }
    
    public void subsets(int[] nums, int start, List<Integer> list){
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            res.add(new ArrayList<Integer>(list));
            subsets(nums, i+1, list);
            list.remove(list.size() - 1);
        }
    }
    
    public List<List<Integer>> subsets_bitManipulation(int[] nums) {
    	res = new ArrayList<>();
    	Arrays.sort(nums);
    	List<List<Integer>> res = new ArrayList<>();
    	for(int i = 0; i < Math.pow(2, nums.length); i++){
    		List<Integer> list =  new ArrayList<>();
    		for(int j = 0; j < nums.length; j++){
    			if(((1 << j) & i) != 0) //???
    				list.add(nums[j]);
    		}
    		res.add(list);
    	}
    	return res;
    }
    
}
