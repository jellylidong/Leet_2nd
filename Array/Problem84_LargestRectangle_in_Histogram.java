import java.util.Stack;

/*Given n non-negative integers representing the histogram's bar height 
 * where the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.*/
public class Problem84_LargestRectangle_in_Histogram {
	public static int largestRectangleArea(int[] nums) {
		int maxA = 0;
		int i = 0;
		Stack<Integer> s = new Stack<>();
		while(i < nums.length){
			if(s.empty() || nums[i] >= nums[s.peek()]){
				s.push(i++);
			}
			else{
				int tp = s.pop();
				int area = nums[tp] * (s.empty()? i : i-1-s.peek());
				maxA = Math.max(maxA, area);
				System.out.println(maxA);
			}
		}
		while(!s.empty()){
			int tp = s.pop();
			int area = nums[tp] * (s.empty()? i : i-1-s.peek());
			maxA = Math.max(maxA, area);
			System.out.println(maxA);
		}
		
		return maxA;
	}
	
	public static void main(String[] args){
		int[] nums = {1,2,3,4,5};
		largestRectangleArea(nums);
	}
}
