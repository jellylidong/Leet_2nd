import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vcoder on 2/11/16.
 */

/*Given a collection of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order.
(ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8,
A solution set is:
[1, 7]
[1, 2, 5]
[2, 6]
[1, 1, 6] */
public class Problem40CombinationSum_II {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] nums, int t) {
        Arrays.sort(nums);

        ans = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();

        solve(nums, t, sol, 0);

        return ans;
    }

    public void solve(int[] nums, int t, List<Integer> sol, int start){
        //if check t == 0 first
        //it's possible that t == 0 and start == nums.length
        //if we don't check t == 0  first we will miss this sol
        if(t == 0 )
            ans.add(new ArrayList<>(sol));
        if(t < 0 || start == nums.length)
            return;
        else{

            for(int i = start; i < nums.length; i++){
                //this is very import to skip the dup entry
                //for dfs in the same level, if current num == previous num, skip it
                //so that we won't have to check dup before the ans.add()
                if(i > start && nums[i] == nums[i-1])
                    continue;
                sol.add(nums[i]);
                solve(nums, t-nums[i], sol, i+1);
                sol.remove(sol.size()-1);
            }

        }

    }
}
