import java.util.*;

/*Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.*/

//the method is straightforward
//some tricks: for iteration of array
//if judge nums[i]-nums[i-1] is complex
//use nums[i+1]-nums[i] may make it easier

public class Problem228_Summary_Ranges {
	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<>();
		for(int i = 0; i < nums.length; i++){
			int lo = nums[i];
			while(i+1 < nums.length && nums[i+1] - nums[i] == 1)
				i++;
			int hi = nums[i];
			if(lo == hi)
				res.add(lo + "");
			else
				res.add(lo + "->" + hi);
		}
		return res;
	}
}
