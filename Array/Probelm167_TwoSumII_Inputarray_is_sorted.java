/*Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2*/
public class Probelm167_TwoSumII_Inputarray_is_sorted {

	/*my analysis
	 * method1  use hash
	 * if(hash.caontains(target - nums[i]
	 * 	res[0] = i;
	 *  res[1] = hash.get(target-nums[i]) + 1
	 *  need extra space and hash computation need extra time
	 *  
	 *  method2 use biary search
	 *  for(int i = 0; i < nums.length; i++)
	 *  	lo = i; hi = nums.length-1;
	 *  	while(lo < hi) ----> binary search
	 *  the time complexity is nlgn, not good
	 *  
	 *  method3 use two pointer
	 *  while(lo < hi)
	 *  	sum = nums[lo] + sum[hi]
	 *  	if(sum < target) lo++;
	 *  	else if(sum > target) hi--
	 *  	else return results*/
	
	public int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		int lo = 0;
		int hi = nums.length-1;
		while(lo < hi){
			int sum = nums[lo] + nums[hi];
			if(sum < target) lo++;
			else if(sum > target)	hi--;
			else break;
		}
		res[0] = lo;
		res[1] = hi;
		return res;
	}
	
		/*public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2]; 
        HashMap<Integer, Integer> hash =  new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            hash.put(nums[i], i);
        }
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(target - nums[i])){
                res[0] = i+1;
                res[1] = hash.get(target - nums[i]) + 1;
                break;
            }
        }
        return res;
    }*/
}
