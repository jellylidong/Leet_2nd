import java.util.*;

/*Given an integer array of size n, 
 * find all elements that appear more than ⌊ n/3 ⌋ times. 
 * The algorithm should run in linear time and in O(1) space.
*/

/*my analysis
 * based on find major element that more than n/2 times
 * when the major elements is defined as appears more than n/k times
 * there should be at most k-1 major elements
 * define n1, n2, n3, n4....nk-1
 * and there count c1, c2, c3, c4, ...ck-1
 * traversal all the numbers:
 * for n in nums:
 *  if n = n1: c1++
 *  else if n == n2: c2++
 *  ...
 *  else if n == nk-1: ck-1++
 *  else if c1 == 0: n1 = n, c1++
 *  else if c2 == 0: n2 = n, c2++
 *  ...
 *  else if ck-1 == 0: nk-1 = n, ck-1++
 *  else: c1,c2...ck-1--
 *  then count all the time of ni appears and those appears more than n/k are candidates
 *  there can be duplicates in these candidates because for a extreme case,
 *  when all the numbers are 0, because we initially set ni = 0;
 *  so must make sure these candidates are distinct*/
public class Problem229_Majority_Element_II {
	public List<Integer> majorityElement(int[] nums) {
		List<Integer> res = new ArrayList<>();
		int n1 = 0;
		int n2 = 0;
		int c1 = 0;
		int c2 = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] == n1)
				c1++;
			else if(nums[i] == n2)
				c2++;
			else if(c1 == 0){
				n1 = nums[i];
				c1 ++;
			}
			else if(c2 == 0){
				n2 = nums[i];
				c2++;
			}
			else{
				c1--;
				c2--;
			}
		}
		c1 = 0;
		c2 = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] == n1)
				c1++;
			if(nums[i] == n2)
				c2++;
		}
		if(c1 > nums.length/3)
			res.add(n1);
		if(n1 != n2 && c2 > nums.length)
			res.add(n2);
		return res;
	}
}
