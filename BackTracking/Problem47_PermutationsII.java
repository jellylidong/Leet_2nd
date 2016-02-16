import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vcoder on 2/11/16.
 */
/*Given a collection of numbers that might contain duplicates,
return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].*/

/*my analysis
* try each position for each number is feasible but very low efficiency
* especially when there are dups, checking it also need more time
*
* instead we build the permutation by inserting new coming number into all possible
* positions of previous results*/

public class Problem47_PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        HashSet<ArrayList<Integer>> hash = new HashSet<>();
        ans.add(new ArrayList<Integer>());
        int size = 1;
        for(int i = 0; i < nums.length; i++){
            int count = 0;
            for(int j = 0; j < size; j++){
                List<Integer> list = ans.remove();

                for(int k = 0; k <= list.size(); k++){
                    list.add(k, nums[i]);
                    if(hash.add(new ArrayList<>(list))) {
                        ans.add(new ArrayList<>(list));
                        count++;
                    }
                    list.remove(k);
                }
            }
            size = count;
        }

        return ans;
    }
}
