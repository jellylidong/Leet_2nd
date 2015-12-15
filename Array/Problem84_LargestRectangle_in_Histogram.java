import java.util.Stack;

/*Given n non-negative integers representing the histogram's bar height 
 * where the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.*/

/*my analysis
 * use a stack to store the index of heights,
 * iterate heights one by one
 * 1.keep push the index of current height when height[i] >= stack.pop or stack is empty
 * 1.a:
 * if(s.empty || h[i] >= s.peek())
 * 		s.push(i++)
 * 1.b
 * otherwise
 * current height is the top of the stack: h = height[s.pop()]
 * if stack is not empty
 * 	 area = h * (i-s.peek()-1)
 * if stack is empty
 * 	 area = h * i
 * then update maxArea = Math.max(maxArea, area)
 * 
 * After iteration done, check if the stack is empty
 * if not empty, repeat 1.b*/

//https://www.youtube.com/watch?v=ZmnqCZp9bBs

public class Problem84_LargestRectangle_in_Histogram {
	public int largestRectangleArea(int[] height) {
        int maxArea = -1;
        int i = 0;
        Stack<Integer> s = new Stack<>();
        for(; i < height.length;){
        	if(s.empty() || height[i] >= height[s.peek()])
        		s.push(i++);
        	else{
        		int h = height[s.pop()];
        		int area = 0;
        		if(s.empty())
        			area = h*i;
        		else
        			area = h*(i-s.pop()-1);
        		maxArea = Math.max(maxArea, area);
        	}
        }
        while(!s.empty()){
        	int h = height[s.pop()];
    		int area = 0;
    		if(s.empty())
    			area = h*i;
    		else
    			area = h*(i-s.pop()-1);
    		maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
	
//	public static void main(String[] args){
//		int[] nums = {1,2,3,4,5};
//		largestRectangleArea(nums);
//	}
}
