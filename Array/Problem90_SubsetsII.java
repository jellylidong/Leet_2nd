import java.util.*;

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

/*note: for method 2
 * the size of result list must be get before loop the count
 * because we are updating the res in the loop,
 * if get size in the loop, the loop will never end!!!!!!!*/
public class Problem90_SubsetsII {
	//the time is terrible long!
	List<List<Integer>> res;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        res.add(new ArrayList(list));
        subsetsWithDup(nums, 0, list);
        return res;
    }

    public void subsetsWithDup(int[] nums, int start, List<Integer> list){
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            if(!res.contains(list))
                res.add(new ArrayList(list));
            subsetsWithDup(nums, i+1, list);
            list.remove(list.size() - 1);
        }
    }

    //first add an empty set
    //then for every iteration
    //if dup plicate happens
    //count the number of dups and iterate from 0 to #of dups
    //in this iterations, every time we add a number
    //and then go through the already stored sets in the results
    //copy each of them and add the dups to them
    //then add the new sets to the result list
    public List<List<Integer>> subsetsWithDup_method2(int[] nums) {
    	res = new ArrayList<>();
    	res.add(new ArrayList<Integer>());
    	for(int i = 0; i < nums.length;){
    		int count = 0;
    		while(count + i < nums.length && nums[i] == nums[i+count])
    			count++;
    		List<Integer> dup = new ArrayList<>();
    		int size = res.size();
    		for(int j = 0; j < count; j++){
    			dup.add(nums[i]);
    			for(int k = 0; k < size; k++){
    				List<Integer> tmp = new ArrayList(res.get(k));
    				tmp.addAll(dup);
    				res.add(new ArrayList(tmp));
    			}
    		}
    		i += count;
    	}
    	return res;
    }
}
