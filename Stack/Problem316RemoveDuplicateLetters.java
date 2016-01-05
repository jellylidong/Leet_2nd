import java.util.Stack;

/*Given a string which contains only lowercase letters, 
 * remove duplicate letters so that every letter appear once and only once. 
 * You must make sure your result is the smallest in lexicographical order
 *  among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"*/

/*my analysis
 * we use a stack to store the results
 * use a cntInStr array to store the count of each char
 * use a isInStack array to store if the char is in stack
 * go through each char is String
 * 	if stack is empty, just push it and isInstack[c] = true
 *  else if current char is already in stack, skip it,
 *  else while :
 *  	1.stack is not empty
 *  	2.current char < stack.peek()
 *  	3.cntInStr[stack.peek()] > 0 
 *  	(which mean this peek value can still be pushed to stack from later chars)
 *  lable this peek value to not in stack and pop it
 *  after while loop done, push current char and label it to is in stack
 *  
 *  https://leetcode.com/discuss/73869/4ms-c-solution-use-return-string-as-a-stack
 *  	*/
public class Problem316RemoveDuplicateLetters {
	public String removeDuplicateLetters(String s) {
		Stack<Character> stk = new Stack<>();
		boolean[] isInStack = new boolean[26];
		int[] cntInStr = new int[26];
		
		//initial the count
		for(int i = 0; i < s.length(); i++)
			cntInStr[s.charAt(i) - 'a']++;
		for(int i = 0; i <s.length(); i++){
			char c = s.charAt(i);
			cntInStr[c-'a']--;
			if(isInStack[c-'a'])
				continue;
			else if(stk.isEmpty()){
				stk.push(c);
				isInStack[c-'a'] = true;
			}
			else{
				while(!stk.isEmpty() && c < stk.peek() && cntInStr[stk.peek() - 'a'] > 0){
					isInStack[stk.peek() - 'a'] = false;
					stk.pop();
				}
				stk.push(c);
				isInStack[c-'a'] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stk.isEmpty())
			sb.append(stk.pop());
		return sb.reverse().toString();
	}
}
