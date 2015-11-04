import java.util.*;

/*Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].*/

/*my analysis
 * split the whole problem into 3 parts
 * (lower, nums[0]), (nums[0], nums[length-1]) (nums[length-1], upper)
 * be careful when the nums[]'s length is 0 or 1
 * or cases nums[0] == lower == uppper
 * e.g. [-1], -1, -1 */
public class Problem163_Missing_Ranges {
	public List<String> missingRanges(int[] nums, int lower, int upper){
		List<String> res = new ArrayList<>();
		if(nums.length == 0){
			if(lower != upper)
				res.add(Integer.toString(lower) + "->" + Integer.toString(upper));
			else
				res.add(Integer.toString(lower));
			return res;
		}
		
		if(nums[0] > lower){
			int lo = lower;
			int hi = nums[0] - 1;
			if(lo != hi)
				res.add(Integer.toString(lo) + "->" + Integer.toString(hi));
			else
				res.add(Integer.toString(lo));
		}
		
		for(int i = 1; i < nums.length; i++){
			if(nums[i] - nums[i-1] > 1){
				int lo = nums[i-1] + 1;
				int hi = nums[i] - 1;
				if(lo != hi)
					res.add(Integer.toString(lo) + "->" + Integer.toString(hi));
				else
					res.add(Integer.toString(lo));
			}
		}
		
		if(upper > nums[nums.length-1]){
			int lo = nums[nums.length-1] + 1;
			int hi = upper;
			if(lo != hi)
				res.add(Integer.toString(lo) + "->" + Integer.toString(hi));
			else
				res.add(Integer.toString(hi));
		}
		
		return res;
	}
}
