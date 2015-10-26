import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] */

// the only difference from I is
//when do recursing
// I starts from i
// II starts from i+1
public class Problem40_CombinationSumII {
	public List<List<Integer>> lists;
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
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
                combination(nums, i+1, target-nums[i], list);//this is difference
                list.remove(list.size() -1);
            }
        }
        else if(target == 0){
            if(!lists.contains(list)){
                lists.add(new ArrayList<>(list));
            }
        }
    }
}
