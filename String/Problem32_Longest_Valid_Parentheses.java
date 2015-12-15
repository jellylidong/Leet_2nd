import java.util.Stack;

/*Given a string containing just the characters '(' and ')', 
 * find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", 
which has length = 4.*/

/*my analysis*/

/*method_1 stack, On space and On time
 *go through each char from beginning to end
 *if this char s.charAt(i) is ) and can be paried with the top of stack
 *s.pop()
 *else
 *first update max:
 *	if stack is empty, it means all parentheses are valid
 *		so max = Math.max(max, i)
 *	if not empty
 *		max = Math.max(max, i-1.stack.peek())
 *second push this unpaired s.charAt(i)*/

/*method_2 DP， use an array ll to keep track of the length end with charAt(i). i.e ll[i]
 * if charAt(i) == ')':
 * 		if charAt(i-1) == '(':   ll[i] = ll[i-2]+2
 * 		if charAt(i-1) == ')':
 * 			if s.chaAt(i-1-ll[i-1]) == '(':  ll[i] = ll[i-1]+2 + ll[i-1 -ll[i-1] - 1] 
 * else
 * 	ll[i] = 0 because parenthese start with '(' can not be valid
 * */
public class Problem32_Longest_Valid_Parentheses {
	public int longestValidParentheses_1(String s) {
		Stack<Integer> st = new Stack<>();
		int max = 0;
		s += "*"; //in case "()"
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == ')' && !st.isEmpty() && s.charAt(st.peek()) == '(')
				st.pop();
			else{
//				if(st.isEmpty())
//					max = Math.max(max, i);
//				else
//					max = Math.max(max, i-1-st.peek());
				max = Math.max(max, (st.isEmpty()? i: i-1-st.peek()));
				st.push(i);
			}
		}
		return max;
	}
	
	public int longestValidParentheses_2(String s) {
		int max = 0;
		int[] ll = new int[s.length()];
		for(int i = 1; i < s.length(); i++){
			if(s.charAt(i) == ')'){
				if(s.charAt(i-1) == '(')
					ll[i] = i-2>=0? ll[i-2]+2: 2;
				else if(s.charAt(i) == ')' && i-1-ll[i-1]>=0 && s.charAt(i-1-ll[i-1]) == '(')
					ll[i] = ll[i-1] + 2 + (i-1-ll[i-1]-1>=0? ll[i-1-ll[i-1]-1]: 0);
			}
			max = Math.max(max, ll[i]);
		}
		return max;
	}
}
