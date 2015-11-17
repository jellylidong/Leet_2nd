/*Given an array of size n, find the majority element. 
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.*/

/*method 1: bit manipulation
 * for the major number, since it apears more than n/2 times
 * if its ith bit is 1, there must be more than n/2 numbers that have ith bit = 1
 * if its ithe bit is 0, there must be less than n/2 numbers that have ith bit = 1
 * in this method we set a mask that has 32 bits (Integer), initial major = 0,
 * initially, mask = 1, then we left shift it 1 bit every time for 32 times,
 * each time we go through the whole array and
 * judge if nums[i]&mask != 0
 * the condition is met means that nums[i]'s nth bit is 1, so count++
 * after going through all the numbers in array, if the count > n/2,
 * it means the major number's nth bit is 1
 * so we let major |= mask
 * then we do the same thing for all the 32 bits and finally return the major value
 * the time complexity is 32O(N), TLE may happen*/

/*method 2 divide and conquer
 * divide the sub array into two part
 * find the numbers appears mostly in their part: left, right,
 * if left == right then return left
 * if not, count the time of these two numbers appear in their own part
 * return the number that appears more times*/

/*moore voting
 * set a count = 0 initially
 * then we go through the array
 * if count == 0
 * 	set the major = nums[i]
 * else
 * if nums[i] == major: count++;
 * else count--
 * this algorithm works because:
 * the major number appers more then n/2 times, so there are always more
 * chances that we meet the same value as current major value
 * from the code, it's easy to understand, but I can not concisely prove it,
 * so remember it
*/
public class Problem169_Majority_Element {
	//bit manipulation
	public int majorityElement_bitManipulation(int[] nums) {
		int major = 0;
		int mask = 1;
		for(int i = 0; i < 32; i++, mask <<= 1){
			int count = 0;
			for(int j = 0; j < nums.length; j++){
				if((nums[j] & mask) != 1){
					count++;
				}
				if(count > nums.length/2){
					major |= mask;
					break;
				}
			}
		}
		return major;
	}
	
	//divide and conquer
	public int majorityElement_divideAndConquer(int[] nums) {
		return major(nums, 0, nums.length-1);
	}
	private int major(int[] nums, int lo, int hi){
		if(lo == hi)
			return nums[lo];
		int mid = lo + (hi-lo)/2;
		int left = major(nums, lo, mid);
		int right = major(nums, mid+1, hi);
		if(left == right)
			return left;
		int nleft = 0;
		int nright = 0;
		for(int i = lo; i <= hi; i++){
			if(nums[i] == left) nleft++;
			if(nums[i] == right) nright++;
		}
		return (nleft >= nright)? left: right;
	}
	
	//moore voting
	public int majorityElement_mooreVoting(int[] nums) {
		int major = 0;
		int count = 0;
		for(int i = 0; i < nums.length; i++){
			if(count == 0){
				major = nums[i];
				count++;
			}
			else if(nums[i] != major){
				count--;
			}
			else
				count++;
		}
		
		//note, we are already told the major number always exist
		//if not, we need to count the times of the major appears to make sure it's real major number
		return major;
	}
	
}
