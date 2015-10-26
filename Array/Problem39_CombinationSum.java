import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] */


public class Problem39_CombinationSum {
//the solution is straightforward
	public List<List<Integer>> lists;
	
	public List<List<Integer>> combination(int[] nums, int target){
		Arrays.sort(nums);
		
		lists = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		
		combination(nums, 0, target, list);
		
		return lists;
	}
	
	public void combination(int[] nums, int start, int target, List<Integer> list){
		if(target > 0){
			for(int i = start; i < nums.length; i++){
				list.add(nums[i]);
				combination(nums, i, target-nums[i], list);
				list.remove(list.size() - 1);
			}
		}
		else if(target == 0){
			if(!lists.contains(list))
				lists.add(new ArrayList<>(list));
		}
	}
}
