import java.util.Stack;

/*Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.*/

/*my analysis
 * updata num = 10*num + s.charAt(i)-'0'
 * */
public class Problem224_BasicCalculator {
	public int calculate(String s) {
		int num = 0;
		int sign = 1;
		int res = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(Character.isDigit(c)){
				num = 10*num + c-'0';
			}
			else if(c == '+'){
				res += sign*num;
				num = 0;
				sign = 1;
			}
			else if(c == '-'){
				res += sign*num;
				num = 0;
				sign = -1;
			}
			/*( can only happen after + or -, so num has alreay been set to 0*/
			else if(c == '('){
				stack.push(res);
				stack.push(sign);
				res = 0;
				sign = 1;
			}
			else if(c == ')'){
				res += sign*num;
				res *= stack.pop();
				res += stack.pop();
				num = 0;
			}
		}
		res += sign*num;
		return res;
	}
}
