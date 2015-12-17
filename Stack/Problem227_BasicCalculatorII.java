import java.util.Stack;

/*Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, 
+, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.*/

/*my analysis
 * since * and / have higher priority over + and -
 * so we need to know the right-side number when we get * or /
 * the solution is we intially set sign = '+'
 * 1.then we update num = 10*num + s.charAt(i)-'0'
 * after the num update done, we begin to operate the num according to sign
 * initially sign is '+'
 * 2.if sign is '+' or '-': push the num with it's sign
 *   if sign is '*' or '/' : push(s.pop()/ or * num) 
 *   then update sign
 * then we begin to update num again(step 1)
 * notice that when num update done, the current sign is just the one before current num
 * so we can do step2
 * 
 * a trick in the code:
 * after the last num update done,
 * since all chars in string s have been check so this num will not 
 * be able to calculated, so we add a "+0" to the end of s,
 * this won't change results but helps avoid this case*/

public class Problem227_BasicCalculatorII {
	public int calculate(String s) {
		Stack<Integer> stack = new Stack<>();
		int num = 0;
		int res = 0;
		char sign = '+';
		
		s += "+0";
		for(int i=0; i < s.length(); i++){
			char c = s.charAt(i);
			if(Character.isDigit(c))
				num = 10*num + c-'0';
			else if(c != ' '){
				if(sign == '+')
					stack.push(num);
				else if(sign == '-')
					stack.push(-num);
				else if(sign == '*')
					stack.push(stack.pop() * num);
				else if(sign == '/')
					stack.push(stack.pop() / num); // pay attention to the order of divider
				
				sign = c;
				num = 0;
			}
		}
		
		for(int n: stack)
			res += n;
		return res;
	}
}
