import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vcoder on 2/11/16.
 */

/*Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]*/
public class Problem90_SubsetsII {
    List<List<Integer>> ans;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        ans = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        ans.add(new ArrayList<Integer>());
        solve(nums, sol, 0);
        return ans;
    }

    public void solve(int[] nums, List<Integer> sol, int start){
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1])
                continue;
            // System.out.println(i + " " + nums[i]);
            sol.add(nums[i]);
            ans.add(new ArrayList<>(sol));
            solve(nums, sol, i+1); // !!!! note here is i+1, not start+1
            sol.remove(sol.size()-1);
        }
    }
}
