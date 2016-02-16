/*Given a string that contains only digits 0-9 and a target value, 
 * return all possibilities to add binary operators (not unary) +, -, or * 
 * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []*/


/*my analysis
 * 
 * method 1 backtracking
 * 
 * from the index we take i(1,2,3 ...) digits as the first number,
 * then add operator +,-* and from i+1, we take (1,2,3..) digits
 * as the the second number ... and so forth
 * meanwhile, we need to record current value curV 
 * and precious added number preV, 
 * for the curV, when i == num.length, if curV == target,
 * it means  we get a result, so we need to add it
 * for the preNum, it will be used when we add a * to the expreesion
 * new curV would be curV-preV+preNum*newNum,  and new preNum would be newNum
 * 
 * considering that we the intermediate results can be larger than integer, so we use long
 * for curV, newNum, preNum*/
import java.util.*;

public class Problem282_ExpressionAddOperators {
	List<String> res;
	public List<String> addOperators(String num, int target) {
		res = new ArrayList<>();
		if(num.length() == 0)
			return res;
		helper(num, "", target, 0, 0, 0);
		return res;
	}
	
	private void helper(String num, String curS, int target, long curV, int curP, long preNum){
		if(curP == num.length()){
			if(curV == target)
				res.add(curS);
			return;
		}
		
		for(int i = curP; i < num.length(); i++){
			//for number that begins with 0 but not only 0 (e.g. 0123)
			//it's a invalid format, so skip it
			if(i != curP && num.charAt(curP) == '0')
				break;
			
			long newNum = Long.parseLong(num.substring(curP, i+1));
			
			//for the first number
			if(curP == 0){
				helper(num, curS+newNum, target, newNum, i+1, newNum);
			}
			else{
				helper(num, curS+"+"+newNum, target, curV+newNum, i+1, newNum);
				helper(num, curS+"-"+newNum, target, curV-newNum, i+1, -newNum);
				helper(num, curS+"*"+newNum, target, curV-preNum+preNum*newNum, i+1, preNum*newNum);
			}
		}
	}
}
