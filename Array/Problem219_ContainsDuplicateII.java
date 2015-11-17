import java.util.*;

/*Given an array of integers and an integer k, 
 * find out whether there are two distinct indices i and j 
 * in the array such that nums[i] = nums[j] 
 * and the difference between i and j is at most k.*/

/*method 1
 * straightforward, store <nums[i], i> to hash map
 * if hashmap contain num[i], compare j and the value of this number in hashmap
 * if j-v <= k return true
 * or update v, v = j */

/*method 2 use a hashset
 * just store k value to the this set
 * when the size of set > k, remove num[i-k-1]
 * and add nums[i]
 * note that for set. add() will return false if this value is already exist in 
 * the set, so we should return true*/
public class Problem219_ContainsDuplicateII {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(!hash.containsKey(nums[i]))
                hash.put(nums[i], i);
            else if(i - hash.get(nums[i]) <= k)
                return true;
            else
                hash.put(nums[i], i);
        }
        return false;
    }
	
	public boolean containsNearbyDuplicate_method2(int[] nums, int k) {
		HashSet<Integer> hash = new HashSet<>();
		for(int i = 0; i < nums.length; i++){
			if(i > k)
				hash.remove(nums[i-k-1]);
			if(!hash.add(nums[i]))
				return true;
		}
		return false;
	}
}
